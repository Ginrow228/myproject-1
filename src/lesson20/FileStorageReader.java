package lesson20;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileStorageReader implements ObjectStorageReader {

    private final Path storage;

    public FileStorageReader(Path storage) {
        this.storage = storage;
    }

    @Override
    public byte[] read(String namespace, String name) {
        Path filePath = storage.resolve(namespace).resolve(name);

        try {
            return Files.readAllBytes(filePath);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при считывании файла: " + filePath, e);
        }
    }

    @Override
    public List<byte[]> read(String namespace, String name, int chunkSize) {
        Path filePath = storage.resolve(namespace).resolve(name);

        List<byte[]> chunks = new ArrayList<>();
        try (InputStream inputStream = Files.newInputStream(filePath)) {
            byte[] buffer = new byte[chunkSize];
            int readBytes;

            while((readBytes = inputStream.read(buffer)) != -1){
                if(readBytes < chunkSize){
                    byte[] actualChunk = Arrays.copyOfRange(buffer, 0, readBytes);
                    chunks.add(actualChunk);
                } else {
                    byte[] actualChunk = Arrays.copyOf(buffer, buffer.length);
                    chunks.add(actualChunk);
                }
            }
        } catch (IOException e){
            throw new RuntimeException("Ошибка при считывании файла по чанкам", e);
        }
        return chunks;
    }
}
