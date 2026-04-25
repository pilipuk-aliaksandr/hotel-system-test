package by.pilipuk.controller;

import by.pilipuk.exeption.base.BaseApplicationException;
import by.pilipuk.mapper.ExceptionMapper;
import by.pilipuk.model.dto.ExceptionDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final ExceptionMapper exceptionMapper;

    @ExceptionHandler(BaseApplicationException.class)
    public ResponseEntity<ExceptionDto> handleExpectedApplicationException(BaseApplicationException ex, HttpServletRequest request) {

        switch (ex.getLevel()) {
            case ERROR -> log.error("[ERROR] {}", ex.getMessage(), ex);
            case DEBUG -> log.debug("[DEBUG] {}", ex.getMessage());
            default    -> log.info("[INFO] {}", ex.getMessage());
        }

        HttpStatus status = HttpStatus.BAD_REQUEST;

        ExceptionDto dto = exceptionMapper.toDto(ex, request, Objects.requireNonNull(status).value());

        return new ResponseEntity<>(dto, status);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDto> handleOtherApplicationException(Exception ex, HttpServletRequest request) {
        log.error("[ERROR] {}", ex.getMessage(), ex);

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ExceptionDto dto = exceptionMapper.toDto(ex, request, status.value(), "INTERNAL_SERVER_ERROR");

        return new ResponseEntity<>(dto, status);
    }
}