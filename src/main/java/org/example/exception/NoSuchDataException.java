package org.example.exception;

public class NoSuchDataException extends RuntimeException {

    private final String message;
    public NoSuchDataException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
