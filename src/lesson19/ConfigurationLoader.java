package lesson19;

import java.io.File;

public interface ConfigurationLoader<T> {
    T load(File file);
}
