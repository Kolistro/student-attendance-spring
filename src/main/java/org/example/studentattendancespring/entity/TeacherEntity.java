package org.example.studentattendancespring.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


// преподаватель
@Getter
@Setter
@Entity
@Table(name = "teacher")
public class TeacherEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String lastName;
    @NotBlank
    private String firstName;
    @NotBlank
    private String middleName;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    private List<LessonEntity> lessons;
}
