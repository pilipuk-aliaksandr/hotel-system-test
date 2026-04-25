package by.pilipuk.mapper;

import by.pilipuk.exeption.base.BaseApplicationException;
import by.pilipuk.model.dto.ExceptionContext;
import by.pilipuk.model.dto.ExceptionDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Setter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.util.List;

@Mapper(componentModel = "spring")
@Setter(onMethod_ = @Autowired)
public abstract class ExceptionMapper {

    @Mapping(target = "status", source = "status")
    @Mapping(target = "url", source = "request.requestURI")
    @Mapping(target = "timestamp", expression = "java(java.time.Instant.now())")
    @Mapping(target = "details", expression = "java(java.util.List.of(ex.getContext()))")
    public abstract ExceptionDto toDto(BaseApplicationException ex, HttpServletRequest request, int status);

//    @Mapping(target = "status", source = "status")
//    @Mapping(target = "url", source = "request.requestURI")
//    @Mapping(target = "timestamp", expression = "java(java.time.Instant.now())")
//    @Mapping(target = "details", expression = "java(java.util.List.of(ex.getContext()))")
//    public abstract ExceptionDto toDto(Exception ex, HttpServletRequest request, int status, String code);

    public ExceptionDto toDto(Exception ex, HttpServletRequest request, int status, String code) {
        ExceptionContext context = ExceptionContext.create(code);
        context.setMessage(ex.getMessage());

        return new ExceptionDto(
                status,
                request.getRequestURI(),
                Instant.now(),
                List.of(context)
        );
    }
}
