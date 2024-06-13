package org.example.studentattendancespring.controller;

import org.example.studentattendancespring.entity.SubjectEntity;
import org.example.studentattendancespring.dto.response.Subject;
import org.example.studentattendancespring.dto.response.CommonResponse;
import org.example.studentattendancespring.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/subject", consumes = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class SubjectController {
    private final SubjectService subjectService;

    @Autowired
    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @PostMapping
    public ResponseEntity<CommonResponse<Subject>> addSubject(@RequestBody SubjectEntity subject) {
        CommonResponse<Subject> commonResponse;
        HttpStatus status;
        try {
            status = HttpStatus.OK;
            commonResponse = new CommonResponse<>(subjectService.addSubject(subject), status.value());
            return ResponseEntity.ok(commonResponse);
        }catch (Exception e){
            status = HttpStatus.BAD_REQUEST;
            return ResponseEntity.badRequest().body(new CommonResponse<>(status.value(), e.getMessage()));
        }
    }

    @PutMapping
    public ResponseEntity<CommonResponse<Subject>> editSubject(@RequestBody SubjectEntity subject) {
        CommonResponse<Subject> commonResponse;
        HttpStatus status;
        try {
            status = HttpStatus.OK;
            commonResponse = new CommonResponse<>(subjectService.editSubject(subject), status.value());
            return ResponseEntity.ok(commonResponse);
        }catch (Exception e){
            status = HttpStatus.BAD_REQUEST;
            return ResponseEntity.badRequest().body(new CommonResponse<>(status.value(), e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse<Subject>> getSubject(@PathVariable Long id) {
        CommonResponse<Subject> commonResponse;
        HttpStatus status;
        try {
            status = HttpStatus.OK;
            commonResponse = new CommonResponse<>(subjectService.getSubject(id), status.value());
            return ResponseEntity.ok(commonResponse);
        }catch (Exception e){
            status = HttpStatus.BAD_REQUEST;
            return ResponseEntity.badRequest().body(new CommonResponse<>(status.value(), e.getMessage()));
        }
    }

    @GetMapping("/subjects")
    public ResponseEntity<CommonResponse<List<Subject>>> getAllSubjects() {
        CommonResponse<List<Subject>> commonResponse;
        HttpStatus status;
        try {
            status = HttpStatus.OK;
            commonResponse = new CommonResponse<>(subjectService.getAllSubjects(), status.value());
            return ResponseEntity.ok(commonResponse);
        }catch (Exception e){
            status = HttpStatus.BAD_REQUEST;
            return ResponseEntity.badRequest().body(new CommonResponse<>(status.value(), e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponse<Long>> deleteSubject(@PathVariable Long id) {
        CommonResponse<Long> commonResponse;
        HttpStatus status;
        try {
            status = HttpStatus.OK;
            commonResponse = new CommonResponse<>(subjectService.deleteSubject(id), status.value());
            return ResponseEntity.ok(commonResponse);
        }catch (Exception e){
            status = HttpStatus.BAD_REQUEST;
            return ResponseEntity.badRequest().body(new CommonResponse<>(status.value(), e.getMessage()));
        }
    }
}
