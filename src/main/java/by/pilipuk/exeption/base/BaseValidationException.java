package by.pilipuk.exeption.base;

import by.pilipuk.model.dto.ExceptionContext;
import org.slf4j.event.Level;

/**
 * Exception thrown when a business validation rule is violated.
 * <p>
 * This exception is used to signal domain-specific validation errors that occur
 * during normal application flow. Business
 * validation errors indicate predictable and expected conditions, such as
 * invalid input data, forbidden operations, or violated domain constraints.
 */
public class BaseValidationException extends BaseApplicationException {

    private static final String CODE = "APPLICATION_VALIDATION_EXCEPTION";

    public BaseValidationException(
        Level level,
        ExceptionContext context
    ) {
        super(CODE, level, context, null);
    }
}
