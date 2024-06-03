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

    public static Attendance toModel(AttendanceEntity attendance){
        Attendance model = new Attendance();
        model.setStudents(attendance.getStudents());
        return model;
    }
}
