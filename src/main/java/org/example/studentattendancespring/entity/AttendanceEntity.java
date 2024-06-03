package org.example.studentattendancespring.entity;

import jakarta.persistence.*;
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
    private LessonEntity lesson;

    @ManyToOne
    @JoinColumn(name = "groups_id")
    private GroupEntity group;

    @ElementCollection
    @CollectionTable(name = "attendance_student_mapping",
            joinColumns = @JoinColumn(name = "attendance_id"))
    @MapKeyJoinColumn(name = "student_id")
    @Column(name = "is_present")
    private Map<StudentEntity, Boolean> students;

    public void addStudent(StudentEntity student, boolean isPresent) {
        students.put(student, isPresent);
    }

    public void removeStudent(StudentEntity student) {
        students.remove(student);
    }
}
