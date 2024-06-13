package org.example.studentattendancespring.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

// лекция (хранит в себе дату и номер)
@Getter
@Setter
@Entity
@Table(name = "lesson")
public class LessonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel. NONE)
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
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    private AttendanceEntity attendances;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LessonEntity lesson)) return false;
        return getLessonNumber() == lesson.getLessonNumber() && Objects.equals(getId(), lesson.getId()) && Objects.equals(getDate(), lesson.getDate()) && Objects.equals(getTeacher(), lesson.getTeacher()) && Objects.equals(getSubject(), lesson.getSubject()) && Objects.equals(getGroup(), lesson.getGroup());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDate(), getLessonNumber(), getTeacher(), getSubject(), getGroup());
    }
}
