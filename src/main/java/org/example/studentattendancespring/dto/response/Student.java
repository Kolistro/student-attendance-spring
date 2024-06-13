package org.example.studentattendancespring.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.example.studentattendancespring.entity.StudentEntity;

@Getter
@Setter
public class Student {
    private Long id;
    private String lastName;
    private String firstName;
    private String middleName;
    private String status;
    private Long idGroup;

    public static Student toModel(StudentEntity student){
        Student model = new Student();
        model.setId(student.getId());
        model.setLastName(student.getLastName());
        model.setFirstName(student.getFirstName());
        model.setMiddleName(student.getMiddleName());
        model.setStatus(student.getStatus());
        model.setIdGroup(student.getGroup().getId());
        return model;
    }

}
