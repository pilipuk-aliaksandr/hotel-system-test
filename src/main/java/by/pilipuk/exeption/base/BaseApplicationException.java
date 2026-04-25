package by.pilipuk.exeption.base;

import by.pilipuk.model.dto.ExceptionContext;
import lombok.Getter;
import org.slf4j.event.Level;

import static java.util.Optional.ofNullable;

@Getter
public class BaseApplicationException extends RuntimeException {

    private static final String parametrizedMessage = "Code: %s, params: [%s]";
    private static final String message = "Code: %s";

    private final String code;
    private final ExceptionContext context;
    private final Level level;

    public BaseApplicationException(
        String code,
        Level level,
        ExceptionContext context,
        Throwable cause
    ) {
        super(enrichMessage(context), cause);
        this.context = context;
        this.code = code;
        this.level = level;
    }

    private static String enrichMessage(
        ExceptionContext context
    ) {
        return ofNullable(context.getParameters())
            .map(parameters -> parametrizedMessage.formatted(context.getCode(), parameters))
            .orElse(message.formatted(context.getCode()));
    }
}
