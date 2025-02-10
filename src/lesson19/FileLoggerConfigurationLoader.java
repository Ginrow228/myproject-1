package lesson19;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FileLoggerConfigurationLoader implements ConfigurationLoader<FileLoggerConfiguration> {

    @Override
    public FileLoggerConfiguration load(File configurationfile) {
        Properties properties = new Properties();

        try (InputStream inStream = new FileInputStream(configurationfile)) {
            properties.load(inStream);
        } catch (IOException e){
            throw new RuntimeException(e);
        }

        String stringLevel = properties.getProperty("Level", "INFO");
        String pattern = properties.getProperty("Pattern", "[%TIME%][%LEVEL%] Message: [%MESSAGE%]");
        String path = properties.getProperty("Path", "file.log");
        int size = Integer.parseInt(properties.getProperty("Size", "1024"));

        File file = new File(path);

        LoggingLevel level = LoggingLevel.valueOf(stringLevel);

        return new FileLoggerConfiguration(level, pattern, file, size);
    }
}
