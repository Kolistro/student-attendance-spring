package org.example.studentattendancespring.model.dto;



import jakarta.persistence.Column;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import org.example.studentattendancespring.entity.AttendanceEntity;
import org.example.studentattendancespring.entity.LessonEntity;

import java.sql.Timestamp;

@Getter
@Setter
public class Lesson {
    private Timestamp date;
    private int lessonNumber;
    private AttendanceEntity attendances;

    public static Lesson toModel(LessonEntity lesson){
        Lesson model = new Lesson();
        model.setDate(lesson.getDate());
        model.setLessonNumber(lesson.getLessonNumber());
        model.setAttendances(lesson.getAttendances());
        return model;
    }


}
