package fr.romaindu35.compression;

import org.tukaani.xz.LZMA2Options;
import org.tukaani.xz.XZInputStream;
import org.tukaani.xz.XZOutputStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * XZ/LZMA2 compression implementation using the Tukaani XZ library.
 * Provides excellent compression ratio (20-30% better than GZIP) with good decompression speed.
 * Ideal for minimizing storage costs on S3 while maintaining fast API response times.
 */
public class XZCompressor implements Compressor {

    private static final int BUFFER_SIZE = 8192;
    private final int compressionLevel;

    /**
     * Creates an XZ compressor with maximum compression level (9).
     * Best for minimizing file size at the cost of compression time.
     */
    public XZCompressor() {
        this(LZMA2Options.PRESET_MAX);
    }

    /**
     * Creates an XZ compressor with the specified compression level.
     *
     * @param compressionLevel Compression level (0-9), where 9 is maximum compression
     */
    public XZCompressor(int compressionLevel) {
        if (compressionLevel < LZMA2Options.PRESET_MIN || compressionLevel > LZMA2Options.PRESET_MAX) {
            throw new IllegalArgumentException(
                    "Compression level must be between " + LZMA2Options.PRESET_MIN +
                    " and " + LZMA2Options.PRESET_MAX);
        }
        this.compressionLevel = compressionLevel;
    }

    @Override
    public byte[] compress(byte[] data) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        XZOutputStream xzOutputStream = null;
        try {
            LZMA2Options options = new LZMA2Options(compressionLevel);
            xzOutputStream = new XZOutputStream(outputStream, options);
            xzOutputStream.write(data);
            xzOutputStream.finish();
            return outputStream.toByteArray();
        } finally {
            if (xzOutputStream != null) {
                try {
                    xzOutputStream.close();
                } catch (IOException ignored) {
                }
            }
        }
    }

    @Override
    public byte[] decompress(byte[] data) throws IOException {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
        XZInputStream xzInputStream = null;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            xzInputStream = new XZInputStream(inputStream);
            byte[] buffer = new byte[BUFFER_SIZE];
            int len;
            while ((len = xzInputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, len);
            }
            return outputStream.toByteArray();
        } finally {
            if (xzInputStream != null) {
                try {
                    xzInputStream.close();
                } catch (IOException ignored) {
                }
            }
        }
    }

    @Override
    public String getFileExtension() {
        return "xz";
    }
}
