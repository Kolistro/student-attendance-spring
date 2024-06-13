package org.example.studentattendancespring.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.example.studentattendancespring.entity.AttendanceEntity;
import org.example.studentattendancespring.entity.LessonEntity;

import java.sql.Timestamp;

@Getter
@Setter
public class Lesson {
    private Long id;
    private Timestamp date;
    private int lessonNumber;
    private Attendance attendances;

    public static Lesson toModel(LessonEntity lesson){
        Lesson model = new Lesson();
        model.setId(lesson.getId());
        model.setDate(lesson.getDate());
        model.setLessonNumber(lesson.getLessonNumber());
        return model;
    }


}
