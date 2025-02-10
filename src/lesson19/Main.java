package lesson19;

import java.io.File;

public class Main {
     public static void main(String[] args) {
//         File file = new File("file.md");
//         FileLoggerConfiguration configuration = new FileLoggerConfiguration(
//                 LoggingLevel.DEBUG,
//                 file,
//                 1000
//         );
//
//         Logger logger = new FileLogger(configuration);
//         logger.debug("hello");
//         logger.info("info message");

         File configuratonFile = new File("log.properties");
         FileLoggerConfigurationLoader loader = new FileLoggerConfigurationLoader();
         FileLoggerConfiguration configuration1 = loader.load(configuratonFile);

         Logger logger1 = new FileLogger(configuration1);

         logger1.info("info message");
         logger1.debug("debug message"); // не выведется

    }
}
