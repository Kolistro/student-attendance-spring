package org.example.studentattendancespring.repository;

import org.example.studentattendancespring.entity.LessonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;

public interface LessonRepo extends JpaRepository<LessonEntity,Long> {
    LessonEntity findByDateAndLessonNumberAndTeacherId(Timestamp date, int lessonNumber, Long teacherId);
    LessonEntity findByDateAndLessonNumberAndGroupId(Timestamp date, int lessonNumber, Long groupId);
    List<LessonEntity> findAllByDateBetweenAndGroupId(Timestamp startDate, Timestamp endDate, Long groupId);
    List<LessonEntity> findAllByDateBetweenAndTeacherId(Timestamp startDate, Timestamp endDate, Long teacherId);
    List<LessonEntity> findByDateBetween(Timestamp startDate, Timestamp endDate);
}
