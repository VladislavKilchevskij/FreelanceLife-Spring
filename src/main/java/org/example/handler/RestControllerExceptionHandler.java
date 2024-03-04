package org.example.handler;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.example.exception.AlreadyExistsException;
import org.example.exception.NoSuchDataException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static java.util.stream.Collectors.*;

@RestControllerAdvice
public class RestControllerExceptionHandler {

    @ExceptionHandler(value = {NoSuchDataException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public String resourceNotFoundHandler(RuntimeException exception) {
        return exception.getMessage();
    }

    @ExceptionHandler(value = {ConstraintViolationException.class, AlreadyExistsException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public String badRequestHandler(RuntimeException exception) {
        if (exception instanceof ConstraintViolationException ex)
            return ex.getConstraintViolations()
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(joining("\n"));
        else return exception.getMessage();
    }
}