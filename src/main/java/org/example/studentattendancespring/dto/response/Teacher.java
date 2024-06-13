package org.example.studentattendancespring.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.example.studentattendancespring.entity.TeacherEntity;

@Getter
@Setter
public class Teacher {
    private Long id;
    private String lastName;
    private String firstName;
    private String middleName;

    public static Teacher toModel(TeacherEntity teacher) {
        Teacher model = new Teacher();
        model.setId(teacher.getId());
        model.setLastName(teacher.getLastName());
        model.setFirstName(teacher.getFirstName());
        model.setMiddleName(teacher.getMiddleName());
        return model;
    }

}
