package lesson20;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Path storagePath = Paths.get("storage");

        FileStorage fileStorage = new FileStorage(storagePath);

        // Добавление в хранилище
        Path fileStore = Paths.get("file3");
        fileStorage.put("namespace1", "needed_file.txt", fileStore);

        // Получение
        Path file = fileStorage.get("namespace1", "needed_file.txt");

    }
}
