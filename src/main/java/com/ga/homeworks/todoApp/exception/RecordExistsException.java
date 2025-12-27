package com.ga.homeworks.todoApp.exception;

public class RecordExistsException extends RuntimeException {
    public RecordExistsException(String message) {
        super(message);
    }
}
