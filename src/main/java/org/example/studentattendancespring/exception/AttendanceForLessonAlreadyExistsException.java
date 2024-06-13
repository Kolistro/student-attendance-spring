package org.example.studentattendancespring.exception;

public class AttendanceForLessonAlreadyExistsException extends RuntimeException{
    public AttendanceForLessonAlreadyExistsException(String message) {
        super(message);
    }
}
