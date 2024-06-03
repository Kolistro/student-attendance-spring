package org.example.studentattendancespring.controller;

import org.example.studentattendancespring.entity.LessonEntity;
import org.example.studentattendancespring.model.dto.Lesson;
import org.example.studentattendancespring.model.dto.LessonWithoutAttendance;
import org.example.studentattendancespring.model.response.CommonResponse;
import org.example.studentattendancespring.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/lesson")
public class LessonController {
    private final LessonService lessonService;

    @Autowired
    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @PostMapping
    public ResponseEntity<CommonResponse<Lesson>> addLesson(@RequestBody LessonEntity lesson) {
        CommonResponse<Lesson> commonResponse;
        HttpStatus status;
        try {
            status = HttpStatus.OK;
            commonResponse = new CommonResponse<>(lessonService.addLesson(lesson), status.value());
            return ResponseEntity.ok(commonResponse);
        }catch (Exception e){
            status = HttpStatus.BAD_REQUEST;
            return ResponseEntity.badRequest().body(new CommonResponse<>(status.value(), e.getMessage()));
        }
    }

    @PutMapping
    public ResponseEntity<CommonResponse<Lesson>> editLesson(@RequestBody LessonEntity lesson) {
        CommonResponse<Lesson> commonResponse;
        HttpStatus status;
        try {
            status = HttpStatus.OK;
            commonResponse = new CommonResponse<>(lessonService.editLesson(lesson), status.value());
            return ResponseEntity.ok(commonResponse);
        }catch (Exception e){
            status = HttpStatus.BAD_REQUEST;
            return ResponseEntity.badRequest().body(new CommonResponse<>(status.value(), e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse<Lesson>> getLesson(@PathVariable Long id) {
        CommonResponse<Lesson> commonResponse;
        HttpStatus status;
        try {
            status = HttpStatus.OK;
            commonResponse = new CommonResponse<>(lessonService.getLesson(id), status.value());
            return ResponseEntity.ok(commonResponse);
        }catch (Exception e){
            status = HttpStatus.BAD_REQUEST;
            return ResponseEntity.badRequest().body(new CommonResponse<>(status.value(), e.getMessage()));
        }
    }

    @GetMapping("/lessons")
    public ResponseEntity<CommonResponse<List<LessonWithoutAttendance>>> getLessonsByGroup(
            @RequestParam(required = false) Timestamp startDate,
            @RequestParam(required = false) Timestamp endDate,
            @RequestParam Long groupId) {
        CommonResponse<List<LessonWithoutAttendance>> commonResponse;
        HttpStatus status;
        try {
            status = HttpStatus.OK;
            commonResponse = new CommonResponse<>(lessonService
                    .getLessonsByGroup(startDate, endDate, groupId), status.value());
            return ResponseEntity.ok(commonResponse);
        }catch (Exception e){
            status = HttpStatus.BAD_REQUEST;
            return ResponseEntity.badRequest().body(new CommonResponse<>(status.value(), e.getMessage()));
        }
    }

    @GetMapping("/lessons")
    public ResponseEntity<CommonResponse<List<LessonWithoutAttendance>>> getLessonsByTeacher(
            @RequestParam(required = false) Timestamp startDate,
            @RequestParam(required = false) Timestamp endDate,
            @RequestParam Long teacherId) {
        CommonResponse<List<LessonWithoutAttendance>> commonResponse;
        HttpStatus status;
        try {
            status = HttpStatus.OK;
            commonResponse = new CommonResponse<>(lessonService
                    .getLessonsByTeacherId(startDate, endDate, teacherId), status.value());
            return ResponseEntity.ok(commonResponse);
        }catch (Exception e){
            status = HttpStatus.BAD_REQUEST;
            return ResponseEntity.badRequest().body(new CommonResponse<>(status.value(), e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponse<Long>> deleteLesson(@PathVariable Long id) {
        CommonResponse<Long> commonResponse;
        HttpStatus status;
        try {
            status = HttpStatus.OK;
            commonResponse = new CommonResponse<>(lessonService.deleteLesson(id), status.value());
            return ResponseEntity.ok(commonResponse);
        }catch (Exception e){
            status = HttpStatus.BAD_REQUEST;
            return ResponseEntity.badRequest().body(new CommonResponse<>(status.value(), e.getMessage()));
        }
    }
}
