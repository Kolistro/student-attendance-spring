package org.example.studentattendancespring.model.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.studentattendancespring.entity.AttendanceEntity;
import org.example.studentattendancespring.entity.StudentEntity;

import java.util.Map;

@Getter
@Setter
public class Attendance {
    private Map<StudentEntity, Boolean> students;
    private LessonWithoutAttendance lesson;

    public static Attendance toModel(AttendanceEntity attendance){
        Attendance model = new Attendance();
        model.setStudents(attendance.getStudents());
        model.setLesson(LessonWithoutAttendance.toModel(attendance.getLesson()));
        return model;
    }
}
