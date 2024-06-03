package org.example.studentattendancespring.exception;

public class LessonForPeriodNotFound extends RuntimeException {
    public LessonForPeriodNotFound(String message) {
        super(message);
    }
}
