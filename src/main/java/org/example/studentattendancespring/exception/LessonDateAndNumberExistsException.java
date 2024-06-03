package org.example.studentattendancespring.exception;

public class LessonDateAndNumberExistsException extends RuntimeException{
    public LessonDateAndNumberExistsException(String message) {
        super(message);
    }
}
