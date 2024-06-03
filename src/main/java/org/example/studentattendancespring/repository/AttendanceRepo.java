package org.example.studentattendancespring.repository;

import org.example.studentattendancespring.entity.AttendanceEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepo extends JpaRepository<AttendanceEntity, Long> {
}
