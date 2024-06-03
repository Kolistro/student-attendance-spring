package org.example.studentattendancespring.model.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.studentattendancespring.entity.TeacherEntity;

@Getter
@Setter
public class Teacher {
    private String lastName;
    private String firstName;
    private String middleName;

    public static Teacher toModel(TeacherEntity teacher) {
        Teacher model = new Teacher();
        model.setLastName(teacher.getLastName());
        model.setFirstName(teacher.getFirstName());
        model.setMiddleName(teacher.getMiddleName());
        return model;
    }

}
