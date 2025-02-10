package lesson20;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class FileStorage implements ObjectStorage<Path> {

    private final Path storage;

    public FileStorage(Path storage) {
        this.storage = storage;
        if (Files.notExists(storage)){
            try {
                Files.createDirectories(storage);
            } catch (IOException e) {
                throw new RuntimeException("Ошибка при создании директории", e);
            }
        }
    }

    @Override
    public void put(String namespace, String name, Path file) {
        Path directory = storage.resolve(namespace);

        try {
            if(Files.notExists(directory)){
                Files.createDirectories(directory);
            }
            Path filePath = directory.resolve(name);
            if(Files.notExists(file)){
                System.out.println("Файл для сохранения не существует");
                return;
            }

            Files.copy(file, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e){
            throw new RuntimeException("Ошибка при сохранении файла");
        }
    }

    @Override
    public Path get(String namespace, String name) {
        Path filePath = storage.resolve(namespace).resolve(name);

        if(Files.exists(filePath)){
            return filePath;
        } else {
            throw new RuntimeException("Файл не найден");
        }
    }
}
