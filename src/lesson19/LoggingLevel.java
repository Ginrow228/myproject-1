package lesson19;

public enum LoggingLevel {
    INFO(0),
    DEBUG(1);

    private final int level;

    LoggingLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}


