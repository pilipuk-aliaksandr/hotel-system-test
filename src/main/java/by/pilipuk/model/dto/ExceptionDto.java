package by.pilipuk.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ExceptionDto(
        String code,
        List<ExceptionContext> contexts,
        String rootCause
) {
}