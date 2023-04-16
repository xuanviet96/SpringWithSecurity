package com.example.springwithsecurity.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(InternalServerException.class)
    public ResponseEntity<Object> handlerInternalServerException(InternalServerException ex, WebRequest request) {
        //Log error
        ErrorResponse error = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        return new ResponseEntity<>(error, error.getHttpStatus());
    }
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(NotFoundException ex, WebRequest request) {
        String uri = request.getDescription(false);
        String message = ex.getMessage();
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, message, uri);
        return new ResponseEntity<>(apiError, apiError.getHttpStatus());
    }
}
