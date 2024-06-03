package org.example.studentattendancespring.handler;

import org.example.studentattendancespring.exception.SubjectNotFoundException;
import org.example.studentattendancespring.exception.SubjectWithSameNameAlreadyExistsException;
import org.example.studentattendancespring.model.dto.Subject;
import org.example.studentattendancespring.model.response.CommonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class SubjectControllerExceptionHandler extends ControllerExceptionHandler {
    @ExceptionHandler(SubjectNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<CommonResponse<Subject>> handleSubjectNotFound(RuntimeException ex) {
        return  ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new CommonResponse<>(HttpStatus.NOT_FOUND.value(), ex.getMessage()));
    }

    @ExceptionHandler(SubjectWithSameNameAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<CommonResponse<Subject>> handleSubjectWithSameNameAlreadyExistsException(RuntimeException ex) {
        return  ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new CommonResponse<>(HttpStatus.CONFLICT.value(), ex.getMessage()));
    }
}
