package org.example.studentattendancespring.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

// предмет(название предмета)
@Getter
@Setter
@Entity
@Table(name = "subject")
public class SubjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;
    @NotBlank(message = "Название предмета не может быть пустым")
    private String name;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
    @Setter(AccessLevel. NONE)
    @Getter(AccessLevel.NONE)
    private List<LessonEntity> lessons;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SubjectEntity subject)) return false;
        return Objects.equals(getId(), subject.getId()) && Objects.equals(getName(), subject.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }
}
