package lesson19;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileLogger implements Logger {

    private FileLoggerConfiguration configuration;

    public FileLogger(FileLoggerConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public void info(String message) {
        if (configuration.level().getLevel() >= LoggingLevel.INFO.getLevel()) {
            writeToFile(LoggingLevel.INFO, message);
        }
    }

    @Override
    public void debug(String message) {
        if (configuration.level().getLevel() >= LoggingLevel.DEBUG.getLevel()) {
            writeToFile(LoggingLevel.DEBUG, message);
        }
    }

    public void writeToFile(LoggingLevel level, String message) {
        byte[] logMessage = formatMessage(level, message);
        try {
            File fileLog = configuration.getFile();
            System.out.println("текущ. файл до проверки на переполнение : " + fileLog.getAbsolutePath());
            if (fileLog.length() + logMessage.length > configuration.getSize()) {
                fileLog = createNewFile();
            }
            System.out.println("запись в файл : " + fileLog.getAbsolutePath());
            try (BufferedOutputStream buffer = new BufferedOutputStream(
                    new FileOutputStream(fileLog, true))) {
                buffer.write(logMessage);
                buffer.write("\n".getBytes());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] formatMessage(LoggingLevel level, String message) {
        String pattern = configuration.pattern();
        String newMessage = pattern
                .replace("%TIME%", LocalDate.now().format(DateTimeFormatter.ISO_DATE))
                .replace("%LEVEL%", level.name())
                .replace("%MESSAGE%", message);
        return newMessage.getBytes();
    }

    private File createNewFile() {
        File old = configuration.getFile();
        String oldName = old.getName();

        String baseName = oldName.substring(0, oldName.lastIndexOf('.'));
        String extension = oldName.substring(oldName.lastIndexOf("."));

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy-HH.mm.ss.SSS"));
        String newFileName = baseName + "_" + timestamp + extension;
        File newFile = new File(old.getParent(), newFileName);

        int counter = 1;
        while (newFile.exists()) {
            newFileName = baseName + "_" + timestamp + "_" + counter + extension;
            newFile = new File(old.getParent(), newFileName);
            counter++;
        }
        try {
            if (!newFile.exists()) {
                if (!newFile.createNewFile()) {
                    throw new IOException("не удалось создать файл : " + newFile.getAbsolutePath());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("ошибка при создании нового файла", e);
        }
        System.out.println("Создан новый файл : " + newFile.getAbsolutePath());
        configuration = new FileLoggerConfiguration(
                configuration.level(),
                configuration.pattern(),
                newFile,
                configuration.getSize());

        return newFile;
    }
}






