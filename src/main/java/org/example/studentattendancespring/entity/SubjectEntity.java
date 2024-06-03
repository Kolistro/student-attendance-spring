package org.example.studentattendancespring.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

// предмет(название предмета)
@Getter
@Setter
@Entity
@Table(name = "subject")
public class SubjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String subjectName;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
    private List<LessonEntity> lessons;


}
