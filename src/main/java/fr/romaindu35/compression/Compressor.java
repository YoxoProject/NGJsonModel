package fr.romaindu35.compression;

import java.io.IOException;

/**
 * Interface for compression/decompression algorithms.
 * Implementations should be thread-safe for concurrent usage.
 */
public interface Compressor {

    /**
     * Compresses the input data.
     *
     * @param data The data to compress
     * @return The compressed data
     * @throws IOException If compression fails
     */
    byte[] compress(byte[] data) throws IOException;

    /**
     * Decompresses the input data.
     *
     * @param data The data to decompress
     * @return The decompressed data
     * @throws IOException If decompression fails
     */
    byte[] decompress(byte[] data) throws IOException;

    /**
     * Returns the file extension for this compression format.
     * For example: "gz" for GZIP, "xz" for XZ, "" for no compression.
     *
     * @return The file extension without the leading dot
     */
    String getFileExtension();
}
