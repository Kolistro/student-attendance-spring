package org.example.studentattendancespring.handler;

import org.example.studentattendancespring.exception.TeacherNotFoundException;
import org.example.studentattendancespring.dto.response.Teacher;
import org.example.studentattendancespring.dto.response.CommonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class TeacherControllerExceptionHandler extends ControllerExceptionHandler {
    @ExceptionHandler(TeacherNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<CommonResponse<Teacher>> handleTeacherNotFound(RuntimeException ex) {
        return  ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new CommonResponse<>(HttpStatus.NOT_FOUND.value(), ex.getMessage()));
    }
}
