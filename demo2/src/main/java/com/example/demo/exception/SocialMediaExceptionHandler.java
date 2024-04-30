package com.example.demo.exception;

import com.example.demo.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class SocialMediaExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ErrorResponse getResponseStatusException(ResponseStatusException exception) {
        exception.getStackTrace();
        return new ErrorResponse(exception.getReason());
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse getBadCredentialsException(BadCredentialsException badCredentialsException) {
        badCredentialsException.getStackTrace();
        return new ErrorResponse("Username/Password Incorrect");
    }
}
