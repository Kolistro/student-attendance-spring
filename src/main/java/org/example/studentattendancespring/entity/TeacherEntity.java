package org.example.studentattendancespring.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;


// преподаватель
@Getter
@Setter
@Entity
@Table(name = "teacher")
public class TeacherEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;
    @NotBlank(message = "Фамилия не может быть пустой")
    private String lastName;
    @NotBlank(message = "Имя не может быть пустым")
    private String firstName;
    @NotNull
    private String middleName;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    @Setter(AccessLevel. NONE)
    @Getter(AccessLevel.NONE)
    private List<LessonEntity> lessons;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TeacherEntity teacher)) return false;
        return Objects.equals(getId(), teacher.getId()) && Objects.equals(getLastName(), teacher.getLastName()) && Objects.equals(getFirstName(), teacher.getFirstName()) && Objects.equals(getMiddleName(), teacher.getMiddleName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getLastName(), getFirstName(), getMiddleName());
    }
}
