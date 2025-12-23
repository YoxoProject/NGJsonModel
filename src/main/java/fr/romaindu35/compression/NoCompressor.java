package fr.romaindu35.compression;

/**
 * A no-op compressor that returns data unchanged.
 * Useful for debugging or when compression is not desired.
 */
public class NoCompressor implements Compressor {

    @Override
    public byte[] compress(byte[] data) {
        return data;
    }

    @Override
    public byte[] decompress(byte[] data) {
        return data;
    }

    @Override
    public String getFileExtension() {
        return "";
    }

    @Override
    public String toString() {
        return "None";
    }
}