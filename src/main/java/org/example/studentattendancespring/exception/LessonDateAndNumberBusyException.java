package org.example.studentattendancespring.exception;

public class LessonDateAndNumberBusyException extends RuntimeException{
    public LessonDateAndNumberBusyException(String message) {
        super(message);
    }
}
