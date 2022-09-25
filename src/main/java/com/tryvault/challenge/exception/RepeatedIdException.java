package com.tryvault.challenge.exception;

public class RepeatedIdException extends Exception {
    public RepeatedIdException(String errorMessage) {
        super(errorMessage);
    }
}
