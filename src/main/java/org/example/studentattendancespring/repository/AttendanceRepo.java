package org.example.studentattendancespring.repository;

import org.example.studentattendancespring.entity.AttendanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttendanceRepo extends JpaRepository<AttendanceEntity, Long> {
    List<AttendanceEntity> findAllByGroup_Id(Long id);
    List<AttendanceEntity> findAllByLesson_Id(Long id);
}
