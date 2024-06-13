package org.example.studentattendancespring.dto.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.example.studentattendancespring.entity.AttendanceEntity;
import org.example.studentattendancespring.entity.AttendanceStudentMappingEntity;
import org.example.studentattendancespring.entity.StudentEntity;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Data
public class Attendance {
    private Long id;
    private Set<AttendanceStudentMapping> students = new HashSet<AttendanceStudentMapping>();
    private Long lessonId;

    public static Attendance toModel(AttendanceEntity attendance){
        Attendance model = new Attendance();
        model.setId(attendance.getId());
        for(AttendanceStudentMappingEntity mapping: attendance.getStudentMappings()){
            model.addStudentMapping(AttendanceStudentMapping.toModel(mapping));
        }
        model.setLessonId(attendance.getLesson().getId());
        return model;
    }

    public void addStudentMapping(AttendanceStudentMapping mapping) {
        students.add(mapping);
    }
}
