package org.example.studentattendancespring.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// группа студентов
@Data
@Entity
@Table(name = "group_students")
@JsonIgnoreProperties(ignoreUnknown = true)
public class GroupEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Long id;
    @NotBlank(message = "Название группы не может быть пустой")
    @Column(name = "group_name")
    private  String name;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    private List<StudentEntity> students = new ArrayList<>();

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    private List<AttendanceEntity> attendances;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    private List<LessonEntity> lessons;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GroupEntity group)) return false;
        return Objects.equals(getId(), group.getId()) && Objects.equals(getName(), group.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }
}
