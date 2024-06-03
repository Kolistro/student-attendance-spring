package org.example.studentattendancespring.service;

import org.example.studentattendancespring.entity.AttendanceEntity;
import org.example.studentattendancespring.exception.AttendanceNotFoundException;
import org.example.studentattendancespring.exception.GroupNotFoundException;
import org.example.studentattendancespring.exception.LessonNotFoundException;
import org.example.studentattendancespring.model.dto.Attendance;
import org.example.studentattendancespring.repository.AttendanceRepo;
import org.example.studentattendancespring.repository.GroupRepo;
import org.example.studentattendancespring.repository.LessonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AttendanceService {
    private final AttendanceRepo attendanceRepo;
    private final GroupRepo groupRepo;
    private final LessonRepo lessonRepo;

    @Autowired
    public AttendanceService(AttendanceRepo attendanceRepo, GroupRepo groupRepo, LessonRepo lessonRepo) {
        this.attendanceRepo = attendanceRepo;
        this.groupRepo = groupRepo;
        this.lessonRepo = lessonRepo;
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

    public List<Attendance> getAttendancesByGroupId(Long groupId) {
        if(groupRepo.findById(groupId).isEmpty()) {
            throw new GroupNotFoundException("Группа не найдена!");
        }
        List<AttendanceEntity> attendanceEntities = attendanceRepo.findAllByGroup_Id(groupId);
        return attendanceEntities.stream()
                .map(Attendance::toModel)
                .collect(Collectors.toList());
    }

    public List<Attendance> getAttendancesByLessonId(Long lessonId) {
        if(lessonRepo.findById(lessonId).isEmpty()) {
            throw new LessonNotFoundException("Урок не найден!");
        }
        List<AttendanceEntity> attendanceEntities = attendanceRepo.findAllByLesson_Id(lessonId);
        return attendanceEntities.stream()
                .map(Attendance::toModel)
                .collect(Collectors.toList());
    }

    public Long deleteAttendance(Long id) {
        attendanceRepo.deleteById(id);
        return id;
    }
}
