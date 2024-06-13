package org.example.studentattendancespring.dto.request;

import lombok.Data;

import java.util.Map;

@Data
public class AttendanceDTO {
    private Long idAttendance;
    private Long lessonId;
    private Map<Long, Boolean> studentMap;
}
