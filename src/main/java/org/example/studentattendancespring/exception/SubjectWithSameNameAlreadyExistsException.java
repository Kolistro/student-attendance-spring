package org.example.studentattendancespring.exception;

public class SubjectWithSameNameAlreadyExistsException extends RuntimeException{
    public SubjectWithSameNameAlreadyExistsException(String message) {
        super(message);
    }
}
