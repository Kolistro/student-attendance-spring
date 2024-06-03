package org.example.studentattendancespring.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

// группа студентов
@Getter
@Setter
@Entity
@Table(name = "group_studens")
public class GroupEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Column(name = "group_name")
    private  String name;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private List<StudentEntity> students = new ArrayList<>();

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private List<AttendanceEntity> attendances;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private List<LessonEntity> lessons;

}
