package fr.romaindu35.benchmark;

import fr.romaindu35.compression.Compressor;
import fr.romaindu35.compression.GzipCompressor;
import fr.romaindu35.compression.NoCompressor;
import fr.romaindu35.compression.XZCompressor;
import fr.romaindu35.compression.ZstdCompressor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CompressionBenchmark {

    private static final int CONCURRENT_THREADS = 10;
    private static final int CONCURRENT_ITERATIONS = 50;
    private static final int NUM_ITERATIONS = 100;

    public static void main(String[] args) {
        File dataDir = new File("benchmark_data");
        if (!dataDir.exists() || !dataDir.isDirectory()) {
            System.err.println("Directory 'benchmark_data' not found. Please create it and add JSON files.");
            return;
        }

        File[] files = dataDir.listFiles((dir, name) -> name.toLowerCase().endsWith(".json"));
        if (files == null || files.length == 0) {
            System.err.println("No JSON files found in 'benchmark_data'.");
            return;
        }

        List<Compressor> compressors = new ArrayList<>();
        //compressors.add(new NoCompressor());
        //compressors.add(new GzipCompressor());
        //compressors.add(new XZCompressor()); // Default balanced level*/
        compressors.add(new ZstdCompressor()); // Best ratio (determine from benchmarking)
        /*for (int i = -7; i <= 22; i++) {
            compressors.add(new ZstdCompressor(i));
        }*/

        System.out.println("Found " + files.length + " files. Starting benchmark...");

        try (PrintWriter writer = new PrintWriter(new FileWriter("benchmark_results.csv"))) {
            // CSV Header
            writer.println("File;Compressor;Original Size (bytes);Compressed Size (bytes);Ratio (%);" +
                    "Compression Time (ms);Decompression Time (ms);RAM Delta (MB);Concurrent Decomp Time (ms) (" + CONCURRENT_THREADS + " threads)");

            for (File file : files) {
                System.out.println("Processing " + file.getName() + "...");
                byte[] originalData = Files.readAllBytes(file.toPath());

                for (Compressor compressor : compressors) {
                    runBenchmark(writer, file.getName(), compressor, originalData);
                }
            }

            System.out.println("Benchmark complete. Results saved to benchmark_results.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void runBenchmark(PrintWriter writer, String fileName, Compressor compressor, byte[] originalData) {
        String compressorName = compressor.toString();

        System.out.print("  - Testing " + compressorName + "... ");

        try {
            // Warmup
            compressor.compress(originalData);

            long totalCompTime = 0;
            double totalRamDelta = 0;
            byte[] compressedData = null;

            // 1. Compression Test (Average of 100)
            for (int i = 0; i < NUM_ITERATIONS; i++) {
                System.gc();
                Thread.sleep(10); // Short pause for GC
                long startMem = getUsedMemory();

                long startComp = System.nanoTime();
                compressedData = compressor.compress(originalData); // Re-assigning but it's same result
                long endComp = System.nanoTime();

                long endMem = getUsedMemory();

                totalCompTime += (endComp - startComp);
                double delta = (double) (endMem - startMem) / (1024 * 1024);
                if (delta > 0) totalRamDelta += delta;
            }

            double avgCompTime = (double) TimeUnit.NANOSECONDS.toMillis(totalCompTime) / NUM_ITERATIONS;
            double avgRamDelta = totalRamDelta / NUM_ITERATIONS;

            // 2. Decompression Test (Average of 100)
            long totalDecompTime = 0;
            for (int i = 0; i < NUM_ITERATIONS; i++) {
                long startDecomp = System.nanoTime();
                compressor.decompress(compressedData);
                long endDecomp = System.nanoTime();
                totalDecompTime += (endDecomp - startDecomp);
            }
            double avgDecompTime = (double) TimeUnit.NANOSECONDS.toMillis(totalDecompTime) / NUM_ITERATIONS;

            // 3. Ratio
            double ratio = 100.0 * compressedData.length / originalData.length;

            // 4. Concurrent Decompression Test (Average of 100? Maybe 10 is enough if slow, but let's try 100)
            long totalConcurrentTime = 0;
            // Reducing iterations for concurrent to avoid extreme duration if file is large, 
            // but user asked for 100. Let's do 100 but maybe smaller inner loop if needed. 
            // We keep inner loop 50.
            for (int i = 0; i < NUM_ITERATIONS; i++) {
                totalConcurrentTime += runConcurrentTest(compressor, compressedData);
            }
            double avgConcurrentTime = (double) totalConcurrentTime / NUM_ITERATIONS;

            // Output to CSV
            writer.printf("%s;%s;%d;%d;%.2f;%.2f;%.2f;%.2f;%.2f%n",
                    fileName,
                    compressorName,
                    originalData.length,
                    compressedData.length,
                    ratio,
                    avgCompTime,
                    avgDecompTime,
                    avgRamDelta,
                    avgConcurrentTime
            );
            writer.flush(); // Ensure write

            System.out.println("Done. Ratio: " + String.format("%.2f", ratio) + "%" +
                    " Comp: " + String.format("%.2f", avgCompTime) + "ms" +
                    " Decomp: " + String.format("%.2f", avgDecompTime) + "ms" +
                    " RAM: " + String.format("%.2f", avgRamDelta) + "MB" +
                    " Concurrent: " + String.format("%.2f", avgConcurrentTime) + "ms");

        } catch (Exception e) {
            System.err.println("Failed: " + e.getMessage());
            writer.printf("%s;%s;ERROR;%s;%s;%s;%s;%s;%s%n", fileName, compressorName, e.getMessage(), "", "", "", "", "", "");
        }
    }

    private static long runConcurrentTest(Compressor compressor, byte[] compressedData) {
        ExecutorService executor = Executors.newFixedThreadPool(CONCURRENT_THREADS);
        long start = System.nanoTime();

        try {
            for (int i = 0; i < CONCURRENT_ITERATIONS; i++) {
                executor.submit(() -> {
                    try {
                        compressor.decompress(compressedData);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }
            executor.shutdown();
            executor.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        long end = System.nanoTime();
        return TimeUnit.NANOSECONDS.toMillis(end - start);
    }

    private static long getUsedMemory() {
        Runtime runtime = Runtime.getRuntime();
        return runtime.totalMemory() - runtime.freeMemory();
    }
}