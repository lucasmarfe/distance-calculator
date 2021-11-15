package com.woodwing.distancecalculator.application.rest;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class HttpExceptionJsonResponse {

    Error error;

    @Value
    @Builder(toBuilder = true)
    public static class Error {
        String code;
        String message;
    }
}
