package lesson19;

import java.io.File;

public class FileLoggerConfiguration implements LoggerConfiguration {

    private static final String DEFAULT_PATTERN = "[%TIME%][%LEVEL%] Сообщение: [%MESSAGE%]";

    private final LoggingLevel level;
    private final String pattern;
    private File file;
    private final int size;

    public FileLoggerConfiguration(LoggingLevel level, String pattern, File file, int size) {
        this.level = level;
        this.pattern = pattern;
        this.file = file;
        this.size = size;
    }

    public FileLoggerConfiguration(LoggingLevel level, File file, int size){
        this(level, DEFAULT_PATTERN, file, size);
    }


    @Override
    public LoggingLevel level() {
        return level;
    }

    @Override
    public String pattern() {
        return pattern;
    }

    public LoggingLevel getLevel() {
        return level;
    }

    public String getPattern() {
        return pattern;
    }

    public File getFile() {
        return file;
    }

    public int getSize() {
        return size;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
