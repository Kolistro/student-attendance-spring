package org.example.studentattendancespring.handler;

import org.example.studentattendancespring.exception.StudentNotFoundException;
import org.example.studentattendancespring.model.dto.Student;
import org.example.studentattendancespring.model.response.CommonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

public class StudentControllerExceptionHandler {
    @ExceptionHandler(StudentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<CommonResponse<Student>> handleStudentNotFound(RuntimeException ex) {
        return  ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new CommonResponse<>(HttpStatus.NOT_FOUND.value(), ex.getMessage()));
    }
}
