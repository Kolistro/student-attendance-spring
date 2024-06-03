package org.example.studentattendancespring.model.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.studentattendancespring.entity.LessonEntity;

import java.sql.Timestamp;

@Getter
@Setter
public class LessonWithoutAttendance {
    private Timestamp date;
    private int lessonNumber;

    public static LessonWithoutAttendance toModel(LessonEntity lesson){
        LessonWithoutAttendance model = new LessonWithoutAttendance();
        model.setDate(lesson.getDate());
        model.setLessonNumber(lesson.getLessonNumber());
        return model;
    }
}
