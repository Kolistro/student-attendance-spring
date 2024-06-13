package org.example.studentattendancespring.dto.response;

import lombok.Data;
import org.example.studentattendancespring.entity.AttendanceStudentMappingEntity;


@Data
public class AttendanceStudentMapping {
    private Long attendanceId;
    private Student student;
    private Boolean isPresent;

    public static AttendanceStudentMapping toModel(AttendanceStudentMappingEntity mapping){
        AttendanceStudentMapping attendanceStudentMapping = new AttendanceStudentMapping();
        attendanceStudentMapping.setAttendanceId(mapping.getAttendance().getId());
        attendanceStudentMapping.setStudent(Student.toModel(mapping.getStudent()));
        attendanceStudentMapping.setIsPresent(mapping.getIsPresent());
        return attendanceStudentMapping;
    }
}
