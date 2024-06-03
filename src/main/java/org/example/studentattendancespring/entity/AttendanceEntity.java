package org.example.studentattendancespring.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

// посещение студентов
@Getter
@Setter
@Entity
@Table(name = "attendance")
public class AttendanceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "lesson_id")
    @NotNull(message = "Укажите занятие")
    private LessonEntity lesson;

    @ManyToOne
    @JoinColumn(name = "groups_id")
    @NotNull(message = "Укажите группу")
    private GroupEntity group;

    @ElementCollection
    @CollectionTable(name = "attendance_student_mapping",
            joinColumns = @JoinColumn(name = "attendance_id"))
    @MapKeyJoinColumn(name = "student_id")
    @Column(name = "is_present")
    @NotEmpty(message = "Необходимо указать студентов")
    private Map<StudentEntity, Boolean> students;

    public void addStudent(StudentEntity student, boolean isPresent) {
        students.put(student, isPresent);
    }

    public void removeStudent(StudentEntity student) {
        students.remove(student);
    }
}
