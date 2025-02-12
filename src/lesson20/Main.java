package lesson20;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Path storagePath = Paths.get("storage");
//
//        FileStorage fileStorage = new FileStorage(storagePath);
//
//        // Добавление в хранилище
//        Path fileStore = Paths.get("file3");
//        fileStorage.put("namespace1", "needed_file.txt", fileStore);
//
//        // Получение
//        Path file = fileStorage.get("namespace1", "needed_file.txt");

        FileStorageReader fileStorageReader = new FileStorageReader(storagePath);
        try {
            byte[] file1 = fileStorageReader.read("namespace1", "needed_file.txt");
            System.out.println(new String(file1));
        } catch (RuntimeException e){
            e.printStackTrace();
        }

        System.out.println("===================");

        try {
            int chunkSize = 50;
            List<byte[]> chunks = fileStorageReader.read("namespace1", "needed_file.txt", chunkSize);
            for (byte[] chunk : chunks){
                System.out.println(new String(chunk));
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        }

    }
}
