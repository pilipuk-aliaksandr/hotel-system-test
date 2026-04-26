package by.pilipuk.service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import java.time.LocalDateTime;
import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class LoggingService {

    @Before("execution(* by.pilipuk.repository.*Repository.*(..))")
    public void logRepositoryMethods(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getSignature().getDeclaringTypeName();
        Object[] args = joinPoint.getArgs();

        log.info("[Repository] Request to database started: {}.{}({})",
                className,
                methodName,
                Arrays.toString(args));
    }

    @Before("execution(* by.pilipuk.controller.*Controller.*(..))")
    public void logRequest(JoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        String httpMethod = request.getMethod();
        String url = request.getRequestURI();
        String methodName = joinPoint.getSignature().getName();

        log.info("[Controller] Accepted new {} request: {} by url: {} at {}",
                httpMethod,
                methodName,
                url,
                LocalDateTime.now()
        );
    }

    @AfterReturning("execution(* by.pilipuk.controller.*Controller.*(..))")
    public void logResponse(JoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        String httpMethod = request.getMethod();
        String url = request.getRequestURI();
        String methodName = joinPoint.getSignature().getName();

        log.info("[Controller] Finished {} request: {} by url: {} at {}",
                httpMethod,
                methodName,
                url,
                LocalDateTime.now()
        );
    }
}