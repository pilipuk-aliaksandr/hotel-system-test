package by.pilipuk.model.enums;

import org.slf4j.event.Level;

public enum ValidationCode {
    NOT_FOUND_BY_ID(Level.DEBUG, "id");

    private final Level level;
    private final String key;

    ValidationCode(Level level, String key) {
        this.level = level;
        this.key = key;
    }

    public Level getLevel() {
        return level;
    }

    public String getKey() {
        return key;
    }
}
