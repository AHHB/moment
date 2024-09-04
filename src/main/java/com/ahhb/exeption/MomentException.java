package com.ahhb.exeption;

public class MomentException extends Exception {
    private final String message;

    public MomentException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
