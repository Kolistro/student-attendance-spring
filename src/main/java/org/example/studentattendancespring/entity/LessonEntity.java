package org.example.studentattendancespring.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

// лекция (хранит в себе дату и номер)
@Getter
@Setter
@Entity
@Table(name = "lesson")
public class LessonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @FutureOrPresent(message = "Дата должна быть в будущем или в настоящем времени")
    private Timestamp date;

    @Positive
    @Column(name = "lesson_number")
    private int lessonNumber;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    @NotNull(message = "Необходимо указать преподавателя")
    private TeacherEntity teacher;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    @NotNull(message = "Необходимо указать предмет")
    private SubjectEntity subject;

    @ManyToOne
    @JoinColumn(name = "group_id")
    @NotNull(message = "Необходимо указать группу")
    private GroupEntity group;

    @OneToOne(mappedBy = "lesson", cascade = CascadeType.ALL)
    private AttendanceEntity attendances;


}
