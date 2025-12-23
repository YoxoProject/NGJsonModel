package fr.romaindu35.compression;

import com.github.luben.zstd.ZstdInputStream;
import com.github.luben.zstd.ZstdOutputStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Zstandard (zstd) compression implementation.
 * Provides a high compression ratio with very high compression and decompression speeds.
 */
public class ZstdCompressor implements Compressor {

    private static final int BUFFER_SIZE = 8192;
    private final int compressionLevel;

    /**
     * Creates a Zstd compressor with default compression level (19).
     * In fact, file are compressed on script side and we don't need to have speed here.
     * We don't use level 20-22 because they don't have lot of benefit (approx 0.01% ratio gain)
     * For the decompression, compression level doesn't change decompression speed and ram usage.
     * See compression benchmark for more details. (in test)
     */
    public ZstdCompressor() {
        this(19);
    }

    /**
     * Creates a Zstd compressor with the specified compression level.
     *
     * @param compressionLevel Compression level
     */
    public ZstdCompressor(int compressionLevel) {
        this.compressionLevel = compressionLevel;
    }

    @Override
    public byte[] compress(byte[] data) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try (ZstdOutputStream zstdOutputStream = new ZstdOutputStream(outputStream, compressionLevel)) {
            zstdOutputStream.write(data);
            zstdOutputStream.close();
            return outputStream.toByteArray();
        }
    }

    @Override
    public byte[] decompress(byte[] data) throws IOException {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try (ZstdInputStream zstdInputStream = new ZstdInputStream(inputStream)) {
            byte[] buffer = new byte[BUFFER_SIZE];
            int len;
            while ((len = zstdInputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, len);
            }
            return outputStream.toByteArray();
        }
    }

    @Override
    public String getFileExtension() {
        return "zst";
    }

    @Override
    public String toString() {
        return "Zstd (Level " + compressionLevel + ")";
    }
}