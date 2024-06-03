package org.example.studentattendancespring.service;

import org.example.studentattendancespring.entity.LessonEntity;
import org.example.studentattendancespring.exception.*;
import org.example.studentattendancespring.model.dto.Lesson;
import org.example.studentattendancespring.model.dto.LessonWithoutAttendance;
import org.example.studentattendancespring.repository.GroupRepo;
import org.example.studentattendancespring.repository.LessonRepo;
import org.example.studentattendancespring.repository.TeacherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LessonService {

    private final LessonRepo lessonRepo;
    private final GroupRepo groupRepo;
    private final TeacherRepo teacherRepo;

    @Autowired
    public LessonService(LessonRepo lessonRepo, GroupRepo groupRepo, TeacherRepo teacherRepo) {
        this.lessonRepo = lessonRepo;
        this.groupRepo = groupRepo;
        this.teacherRepo = teacherRepo;
    }

    public Lesson addLesson(LessonEntity lesson) {
        if(lessonRepo.findByDateAndLessonNumber(lesson.getDate(), lesson.getLessonNumber()) != null) {
            throw new LessonDateAndNumberExistsException("Дата и номер урока заняты!");
        }
        lessonRepo.save(lesson);
        return Lesson.toModel(lesson);
    }

    public Lesson editLesson(LessonEntity lesson) {
        LessonEntity lessonEntity = lessonRepo.findById(lesson.getId())
                .orElseThrow(() -> new LessonNotFoundException("Урок не найден!"));
        lessonEntity.setDate(lesson.getDate());
        lessonEntity.setLessonNumber(lesson.getLessonNumber());
        lessonRepo.save(lessonEntity);
        return Lesson.toModel(lesson);
    }

    public Lesson getLesson(Long id) {
        LessonEntity lesson = lessonRepo.findById(id)
                .orElseThrow(() -> new LessonNotFoundException("Урок не найден!"));
        return Lesson.toModel(lesson);
    }

    public List<LessonWithoutAttendance> getLessonsByGroup(Timestamp startDate, Timestamp endDate, Long groupId) {
        if(groupRepo.findById(groupId).isEmpty()) {
            throw new GroupNotFoundException("Группа не найдена!");
        }
        if(lessonRepo.findByDateBetween(startDate, endDate) == null) {
            throw new LessonForPeriodNotFound("Лекции в этот промежуток дат не найдены!");
        }
        List<LessonEntity> lessonEntities = lessonRepo.findAllByDateBetweenAndGroupId(startDate, endDate, groupId);
        return lessonEntities.stream()
                .map(LessonWithoutAttendance::toModel)
                .collect(Collectors.toList());
    }

    public List<LessonWithoutAttendance> getLessonsByTeacherId(Timestamp startDate, Timestamp endDate, Long teacherId) {
        if(teacherRepo.findById(teacherId).isEmpty()) {
            throw new TeacherNotFoundException("Преподаватель не найден!");
        }
        if(lessonRepo.findByDateBetween(startDate, endDate) == null) {
            throw new LessonForPeriodNotFound("Лекции в этот промежуток дат не найдены!");
        }
        List<LessonEntity> lessonEntities = lessonRepo.findAllByDateBetweenAndTeacherId(startDate, endDate, teacherId);
        return lessonEntities.stream()
                .map(LessonWithoutAttendance::toModel)
                .collect(Collectors.toList());
    }

    public Long deleteLesson(Long id) {
        lessonRepo.deleteById(id);
        return id;
    }
}
