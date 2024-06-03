package org.example.studentattendancespring.repository;

import org.example.studentattendancespring.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepo extends JpaRepository<StudentEntity, Long> {
    List<StudentEntity> findAllByGroup_Id(Long id);
}
