package lesson20;

import java.util.List;

public interface ObjectStorageReader {
    byte[] read(String namespace, String name);
    List<byte[]> read(String namespace, String name, int chunkSize);
}
