package fr.romaindu35.compression;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * GZIP compression implementation using Java's built-in GZIP support.
 * Provides moderate compression with fast decompression speed.
 * No external dependencies required.
 */
public class GzipCompressor implements Compressor {

    private static final int BUFFER_SIZE = 8192;

    @Override
    public byte[] compress(byte[] data) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        GZIPOutputStream gzipOutputStream = null;
        try {
            gzipOutputStream = new GZIPOutputStream(outputStream);
            gzipOutputStream.write(data);
            gzipOutputStream.finish();
            return outputStream.toByteArray();
        } finally {
            if (gzipOutputStream != null) {
                try {
                    gzipOutputStream.close();
                } catch (IOException ignored) {
                }
            }
        }
    }

    @Override
    public byte[] decompress(byte[] data) throws IOException {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
        GZIPInputStream gzipInputStream = null;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            gzipInputStream = new GZIPInputStream(inputStream);
            byte[] buffer = new byte[BUFFER_SIZE];
            int len;
            while ((len = gzipInputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, len);
            }
            return outputStream.toByteArray();
        } finally {
            if (gzipInputStream != null) {
                try {
                    gzipInputStream.close();
                } catch (IOException ignored) {
                }
            }
        }
    }

    @Override
    public String getFileExtension() {
        return "gz";
    }
}
