package org.example.studentattendancespring.exception;

public class SubjectNotFoundException extends RuntimeException{
    public SubjectNotFoundException(String message) {
        super(message);
    }
}
