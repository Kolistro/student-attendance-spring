package org.example.studentattendancespring.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "attendance_student_mapping")
public class AttendanceStudentMappingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "attendance_id")
    private AttendanceEntity attendance;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private StudentEntity student;

    @Column(name = "is_present")
    private Boolean isPresent;
}
