package org.example.studentattendancespring.controller;

import org.example.studentattendancespring.dto.request.AttendanceDTO;
import org.example.studentattendancespring.entity.AttendanceEntity;
import org.example.studentattendancespring.dto.response.Attendance;
import org.example.studentattendancespring.dto.response.CommonResponse;
import org.example.studentattendancespring.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/attendance", consumes = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class AttendanceController {
    private final AttendanceService attendanceService;

    @Autowired
    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @PostMapping
    public ResponseEntity<CommonResponse<Attendance>> addAttendance(@RequestParam Long idLesson) {
        CommonResponse<Attendance> commonResponse;
        HttpStatus status;
        try {
            status = HttpStatus.OK;
            commonResponse = new CommonResponse<>(attendanceService.addAttendance(idLesson), status.value());
            return ResponseEntity.ok(commonResponse);
        }catch (Exception e){
            status = HttpStatus.BAD_REQUEST;
            return ResponseEntity.badRequest().body(new CommonResponse<>(status.value(), e.getMessage()));
        }
    }

    @PutMapping
    public ResponseEntity<CommonResponse<Attendance>> editAttendance(@RequestBody AttendanceDTO attendance) {
        CommonResponse<Attendance> commonResponse;
        HttpStatus status;
        try {
            status = HttpStatus.OK;
            commonResponse = new CommonResponse<>(attendanceService.editAttendance(attendance), status.value());
            return ResponseEntity.ok(commonResponse);
        }catch (Exception e){
            status = HttpStatus.BAD_REQUEST;
            return ResponseEntity.badRequest().body(new CommonResponse<>(status.value(), e.getMessage()));
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CommonResponse<Attendance>> editAttendanceStudent(@PathVariable Long id,@RequestBody Long studentId) {
        CommonResponse<Attendance> commonResponse;
        HttpStatus status;
        try {
            status = HttpStatus.OK;
            commonResponse = new CommonResponse<>(attendanceService.editAttendanceStudent(id, studentId), status.value());
            return ResponseEntity.ok(commonResponse);
        }catch (Exception e){
            status = HttpStatus.BAD_REQUEST;
            return ResponseEntity.badRequest().body(new CommonResponse<>(status.value(), e.getMessage()));
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse<Attendance>> getAttendance(@PathVariable Long id) {
        CommonResponse<Attendance> commonResponse;
        HttpStatus status;
        try {
            status = HttpStatus.OK;
            commonResponse = new CommonResponse<>(attendanceService.getAttendance(id), status.value());
            return ResponseEntity.ok(commonResponse);
        }catch (Exception e){
            status = HttpStatus.BAD_REQUEST;
            return ResponseEntity.badRequest().body(new CommonResponse<>(status.value(), e.getMessage()));
        }
    }

    @GetMapping("attendances/group")
    public ResponseEntity<CommonResponse<List<Attendance>>> getAttendancesByGroupId(@RequestParam Long groupId) {
        CommonResponse<List<Attendance>> commonResponse;
        HttpStatus status;
        try {
            status = HttpStatus.OK;
            commonResponse = new CommonResponse<>(attendanceService.getAttendancesByGroupId(groupId), status.value());
            return ResponseEntity.ok(commonResponse);
        }catch (Exception e){
            status = HttpStatus.BAD_REQUEST;
            return ResponseEntity.badRequest().body(new CommonResponse<>(status.value(), e.getMessage()));
        }
    }

    @GetMapping("attendances/lesson")
    public ResponseEntity<CommonResponse<Attendance>> getAttendancesByLessonId(@RequestParam Long lessonId) {
        CommonResponse<Attendance> commonResponse;
        HttpStatus status;
        try {
            status = HttpStatus.OK;
            commonResponse = new CommonResponse<>(attendanceService.getAttendancesByLessonId(lessonId), status.value());
            return ResponseEntity.ok(commonResponse);
        }catch (Exception e){
            status = HttpStatus.BAD_REQUEST;
            return ResponseEntity.badRequest().body(new CommonResponse<>(status.value(), e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponse<Long>> deleteAttendance(@PathVariable Long id) {
        CommonResponse<Long> commonResponse;
        HttpStatus status;
        try {
            status = HttpStatus.OK;
            commonResponse = new CommonResponse<>(attendanceService.deleteAttendance(id), status.value());
            return ResponseEntity.ok(commonResponse);
        }catch (Exception e){
            status = HttpStatus.BAD_REQUEST;
            return ResponseEntity.badRequest().body(new CommonResponse<>(status.value(), e.getMessage()));
        }
    }
}
