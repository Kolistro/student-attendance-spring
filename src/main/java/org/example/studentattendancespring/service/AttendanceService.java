package org.example.studentattendancespring.service;

import org.example.studentattendancespring.entity.AttendanceEntity;
import org.example.studentattendancespring.exception.AttendanceNotFoundException;
import org.example.studentattendancespring.model.dto.Attendance;
import org.example.studentattendancespring.repository.AttendanceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttendanceService {
    private final AttendanceRepo attendanceRepo;

    @Autowired
    public AttendanceService(AttendanceRepo attendanceRepo) {
        this.attendanceRepo = attendanceRepo;
    }

    public Attendance addAttendance(AttendanceEntity attendance) {
        attendanceRepo.save(attendance);
        return Attendance.toModel(attendance);
    }

    public Attendance editAttendance(AttendanceEntity attendance) {
        AttendanceEntity attendanceEntity = attendanceRepo.findById(attendance.getId())
                .orElseThrow(() -> new AttendanceNotFoundException("Посещаемость не найдена"));
        attendanceEntity.setStudents(attendance.getStudents());
        attendanceRepo.save(attendanceEntity);
        return Attendance.toModel(attendance);
    }

    public Attendance getAttendance(Long id) {
        AttendanceEntity attendance = attendanceRepo.findById(id)
                .orElseThrow(() -> new AttendanceNotFoundException("Посещаемость не найдена"));
        return Attendance.toModel(attendance);
    }

    public Long deleteAttendance(Long id) {
        attendanceRepo.deleteById(id);
        return id;
    }
}
