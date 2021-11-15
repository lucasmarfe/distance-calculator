package com.woodwing.distancecalculator.application.rest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.context.request.WebRequest;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
class HttpExceptionHandlerTest {

    @Mock
    private WebRequest request;

    @Test
    void handleExceptionInternal() {
        var exception = new IllegalArgumentException("test-exception", new Exception());
        var handler = new HttpExceptionHandler();
        var response = handler.handleConflict(exception, request);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).isInstanceOf(HttpExceptionJsonResponse.class);
        assertThat(((HttpExceptionJsonResponse) response.getBody()).getError().getMessage())
                .isEqualTo("test-exception");
    }
}
