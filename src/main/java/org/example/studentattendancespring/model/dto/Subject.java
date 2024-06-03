package org.example.studentattendancespring.model.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.studentattendancespring.entity.SubjectEntity;

@Getter
@Setter
public class Subject {
    private String subjectName;

    public static Subject toModel(SubjectEntity subject){
        Subject model = new Subject();
        model.setSubjectName(subject.getName());
        return model;
    }

}
