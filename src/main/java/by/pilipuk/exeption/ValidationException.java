package by.pilipuk.exeption;

import by.pilipuk.exeption.base.BaseValidationException;
import by.pilipuk.model.dto.ExceptionContext;
import by.pilipuk.model.enums.ValidationCode;
import org.slf4j.event.Level;

import java.util.List;

public class ValidationException extends BaseValidationException {

    private ValidationException(
        Level level,
        ExceptionContext context
    ) {
        super(level, context);
    }

    public static ValidationException create(ValidationCode code) {
        return new ValidationException(
            code.getLevel(),
            ExceptionContext.create(code.name())
        );
    }

    public static ValidationException create(
        Level level,
        ExceptionContext context
    ) {
        return new ValidationException(level, context);
    }

    public static ValidationException create(
        ValidationCode code,
        Object param
    ) {
        return new ValidationException(
            code.getLevel(),
            ExceptionContext.create(code.name()).add(code.getKey(), param)
        );
    }

    public static ValidationException create(
        ValidationCode code,
        List<String> params
    ) {
        return new ValidationException(
            code.getLevel(),
            ExceptionContext.create(code.name()).add(code.getKey(), String.join(", ", params))
        );
    }

}
