package org.example.studentattendancespring.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotBlank(message = "Фамилия не может быть пустой")
    private String lastName;
    @NotBlank(message = "Имя не может быть пустым")
    private String firstName;
    @NotNull
    private String middleName;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    private List<LessonEntity> lessons;
}
