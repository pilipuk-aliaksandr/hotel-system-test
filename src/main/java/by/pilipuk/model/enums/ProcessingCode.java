package by.pilipuk.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.slf4j.event.Level;

import static org.slf4j.event.Level.ERROR;

@Getter
@RequiredArgsConstructor
public enum ProcessingCode {

    FAILED_PROCESSING(ERROR);

    private final Level level;
    private String key;

    ProcessingCode(
        Level level,
        String key
    ) {
        this.level = level;
        this.key = key;
    }
}
