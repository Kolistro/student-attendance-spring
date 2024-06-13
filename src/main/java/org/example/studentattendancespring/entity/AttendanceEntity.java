package org.example.studentattendancespring.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.apache.commons.lang3.builder.HashCodeExclude;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

// посещение студентов
@Data
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

    @OneToMany(mappedBy = "attendance", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @HashCodeExclude
    private Set<AttendanceStudentMappingEntity> studentMappings = new HashSet<>();

    public void addStudentMapping(StudentEntity student, Boolean isPresent) {
        AttendanceStudentMappingEntity mapping = new AttendanceStudentMappingEntity();
        mapping.setAttendance(this);
        mapping.setStudent(student);
        mapping.setIsPresent(isPresent);
        studentMappings.add(mapping);
    }

    public void addStudentMapping(AttendanceStudentMappingEntity mapping) {
        mapping.setAttendance(this);
        studentMappings.add(mapping);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AttendanceEntity that)) return false;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getLesson(), that.getLesson()) && Objects.equals(getGroup(), that.getGroup());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getLesson(), getGroup());
    }
}
