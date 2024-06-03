package org.example.studentattendancespring.exception;

public class GroupWithNameAlreadyExistsException extends RuntimeException {
    public GroupWithNameAlreadyExistsException(String message) {
        super(message);
    }
}
