package org.example.studentattendancespring.repository;

import org.example.studentattendancespring.entity.TeacherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepo extends JpaRepository<TeacherEntity, Long> {
}
