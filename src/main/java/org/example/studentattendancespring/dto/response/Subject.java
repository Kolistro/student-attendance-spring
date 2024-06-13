package org.example.studentattendancespring.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.example.studentattendancespring.entity.SubjectEntity;

@Getter
@Setter
public class Subject {
    private Long id;
    private String subjectName;

    public static Subject toModel(SubjectEntity subject){
        Subject model = new Subject();
        model.setId(subject.getId());
        model.setSubjectName(subject.getName());
        return model;
    }

}
