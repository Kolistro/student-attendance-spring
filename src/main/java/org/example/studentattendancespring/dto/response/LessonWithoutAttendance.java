package org.example.studentattendancespring.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.example.studentattendancespring.entity.LessonEntity;

import java.sql.Timestamp;

@Getter
@Setter
public class LessonWithoutAttendance {
    private Long id;
    private Timestamp date;
    private int lessonNumber;

    public static LessonWithoutAttendance toModel(LessonEntity lesson){
        LessonWithoutAttendance model = new LessonWithoutAttendance();
        model.setId(lesson.getId());
        model.setDate(lesson.getDate());
        model.setLessonNumber(lesson.getLessonNumber());
        return model;
    }
}
