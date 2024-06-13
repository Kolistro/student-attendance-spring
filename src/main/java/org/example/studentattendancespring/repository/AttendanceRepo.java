package org.example.studentattendancespring.repository;

import org.example.studentattendancespring.entity.AttendanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AttendanceRepo extends JpaRepository<AttendanceEntity, Long> {
    List<AttendanceEntity> findAllByGroup_Id(Long id);
    Optional<AttendanceEntity> findByLesson_Id(Long id);
}
