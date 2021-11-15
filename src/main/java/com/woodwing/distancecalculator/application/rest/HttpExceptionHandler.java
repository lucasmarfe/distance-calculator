package com.woodwing.distancecalculator.application.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.UUID;

@Slf4j
@ControllerAdvice
public class HttpExceptionHandler extends ResponseEntityExceptionHandler {

    private static HttpStatus resolveStatusCode(Exception exception) {
        var statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
        if (exception instanceof IllegalArgumentException) {
            statusCode = HttpStatus.BAD_REQUEST;
        }
        return statusCode;
    }

    @ExceptionHandler({
            IllegalArgumentException.class,
            HttpClientErrorException.class,
            ResponseStatusException.class,
            RuntimeException.class
    })
    protected ResponseEntity<Object> handleConflict(Exception exception, WebRequest request) {
        var statusCode = resolveStatusCode(exception);
        return this.handleExceptionInternal(
                exception, null, new HttpHeaders(), statusCode, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception exception,
            @Nullable Object body,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        var uuidString = UUID.randomUUID().toString();
        var error = HttpExceptionJsonResponse.Error.builder().code(uuidString).message(exception.getMessage()).build();
        log.error(String.format("Exception is thrown with UUID %s", uuidString), exception);
        var exceptionResponse = HttpExceptionJsonResponse.builder().error(error).build();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return super.handleExceptionInternal(exception, exceptionResponse, headers, status, request);
    }
}
