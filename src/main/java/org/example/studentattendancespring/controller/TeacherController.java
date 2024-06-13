package org.example.studentattendancespring.controller;

import org.example.studentattendancespring.entity.TeacherEntity;
import org.example.studentattendancespring.dto.response.Teacher;
import org.example.studentattendancespring.dto.response.CommonResponse;
import org.example.studentattendancespring.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/teacher", consumes = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class TeacherController {
    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @PostMapping
    public ResponseEntity<CommonResponse<Teacher>> addTeacher(@RequestBody TeacherEntity teacher) {
        CommonResponse<Teacher> commonResponse;
        HttpStatus status;
        try {
            status = HttpStatus.OK;
            commonResponse = new CommonResponse<>(teacherService.addTeacher(teacher), status.value());
            return ResponseEntity.ok(commonResponse);
        }catch (Exception e){
            status = HttpStatus.BAD_REQUEST;
            return ResponseEntity.badRequest().body(new CommonResponse<>(status.value(), e.getMessage()));
        }
    }

    @PutMapping
    public ResponseEntity<CommonResponse<Teacher>> editTeacher(@RequestBody TeacherEntity teacher) {
        CommonResponse<Teacher> commonResponse;
        HttpStatus status;
        try {
            status = HttpStatus.OK;
            commonResponse = new CommonResponse<>(teacherService.editTeacher(teacher), status.value());
            return ResponseEntity.ok(commonResponse);
        }catch (Exception e){
            status = HttpStatus.BAD_REQUEST;
            return ResponseEntity.badRequest().body(new CommonResponse<>(status.value(), e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse<Teacher>> getTeacher(@PathVariable Long id) {
        CommonResponse<Teacher> commonResponse;
        HttpStatus status;
        try {
            status = HttpStatus.OK;
            commonResponse = new CommonResponse<>(teacherService.getTeacher(id), status.value());
            return ResponseEntity.ok(commonResponse);
        }catch (Exception e){
            status = HttpStatus.BAD_REQUEST;
            return ResponseEntity.badRequest().body(new CommonResponse<>(status.value(), e.getMessage()));
        }
    }

    @GetMapping("/teachers")
    public ResponseEntity<CommonResponse<List<Teacher>>> getAllTeachers() {
        CommonResponse<List<Teacher>> commonResponse;
        HttpStatus status;
        try {
            status = HttpStatus.OK;
            commonResponse = new CommonResponse<>(teacherService.getAllTeachers(), status.value());
            return ResponseEntity.ok(commonResponse);
        }catch (Exception e){
            status = HttpStatus.BAD_REQUEST;
            return ResponseEntity.badRequest().body(new CommonResponse<>(status.value(), e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponse<Long>> deleteTeacher(@PathVariable Long id) {
        CommonResponse<Long> commonResponse;
        HttpStatus status;
        try {
            status = HttpStatus.OK;
            commonResponse = new CommonResponse<>(teacherService.deleteTeacher(id), status.value());
            return ResponseEntity.ok(commonResponse);
        }catch (Exception e){
            status = HttpStatus.BAD_REQUEST;
            return ResponseEntity.badRequest().body(new CommonResponse<>(status.value(), e.getMessage()));
        }
    }
}
