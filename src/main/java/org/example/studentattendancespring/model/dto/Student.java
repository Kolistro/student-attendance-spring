package org.example.studentattendancespring.model.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.studentattendancespring.entity.Status;
import org.example.studentattendancespring.entity.StudentEntity;

@Getter
@Setter
public class Student {
    private String lastName;
    private String firstName;
    private String middleName;
    private Status status;
    private Long idGroup;

    public static Student toModel(StudentEntity student){
        Student model = new Student();
        model.setLastName(student.getLastName());
        model.setFirstName(student.getFirstName());
        model.setMiddleName(student.getMiddleName());
        model.setStatus(student.getStatus());
        model.setIdGroup(student.getGroup().getId());
        return model;
    }

}
