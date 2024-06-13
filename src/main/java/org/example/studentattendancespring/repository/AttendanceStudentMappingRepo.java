package org.example.studentattendancespring.repository;

import org.example.studentattendancespring.entity.AttendanceStudentMappingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AttendanceStudentMappingRepo extends JpaRepository<AttendanceStudentMappingEntity, Long> {
    Optional<AttendanceStudentMappingEntity> findByAttendanceIdAndStudentId(Long attendanceId, Long studentId);
    List<AttendanceStudentMappingEntity> findByAttendanceId(Long attendanceId);
    void deleteAllByAttendanceId(Long attendanceId);
    void deleteByAttendanceId(Long attendanceId);
}
