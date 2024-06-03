package org.example.studentattendancespring.handler;

import org.example.studentattendancespring.exception.GroupNotFoundException;
import org.example.studentattendancespring.exception.GroupWithNameAlreadyExistsException;
import org.example.studentattendancespring.model.dto.Group;
import org.example.studentattendancespring.model.response.CommonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GroupControllerExceptionHandler extends ControllerExceptionHandler {
    @ExceptionHandler(GroupNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<CommonResponse<Group>> handleGroupNotFound(RuntimeException ex) {
        return  ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new CommonResponse<>(HttpStatus.NOT_FOUND.value(), ex.getMessage()));
    }

    @ExceptionHandler(GroupWithNameAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<CommonResponse<Group>> handleGroupWithNameAlreadyExists(RuntimeException ex) {
        return  ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new CommonResponse<>(HttpStatus.CONFLICT.value(), ex.getMessage()));
    }
}
