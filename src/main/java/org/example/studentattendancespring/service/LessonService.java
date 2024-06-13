package org.example.studentattendancespring.service;

import org.example.studentattendancespring.dto.response.Attendance;
import org.example.studentattendancespring.entity.AttendanceEntity;
import org.example.studentattendancespring.entity.LessonEntity;
import org.example.studentattendancespring.exception.*;
import org.example.studentattendancespring.dto.response.Lesson;
import org.example.studentattendancespring.dto.response.LessonWithoutAttendance;
import org.example.studentattendancespring.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LessonService {

    private final LessonRepo lessonRepo;
    private final GroupRepo groupRepo;
    private final TeacherRepo teacherRepo;
    private final AttendanceRepo attendanceRepo;
    private final SubjectRepo subjectRepo;

    @Autowired
    public LessonService(LessonRepo lessonRepo, GroupRepo groupRepo, TeacherRepo teacherRepo,
                         AttendanceRepo attendanceRepo, SubjectRepo subjectRepo) {
        this.lessonRepo = lessonRepo;
        this.groupRepo = groupRepo;
        this.teacherRepo = teacherRepo;
        this.attendanceRepo = attendanceRepo;
        this.subjectRepo = subjectRepo;
    }

    public LessonWithoutAttendance addLesson(LessonEntity lesson) {
        groupRepo.findById(lesson.getGroup().getId())
                .orElseThrow(()->new GroupNotFoundException("Группа не найдена!"));
        teacherRepo.findById(lesson.getTeacher().getId())
                .orElseThrow(()-> new TeacherNotFoundException("Преподаватель не найден!"));
        subjectRepo.findById(lesson.getSubject().getId())
                .orElseThrow(() -> new SubjectNotFoundException("Предмет не найден!"));

        if(lessonRepo.findByDateAndLessonNumberAndTeacherId(
                lesson.getDate(), lesson.getLessonNumber(), lesson.getTeacher().getId()) != null) {
            throw new LessonDateAndNumberExistsException("У преподавателя уже есть пара в это время!");
        }
        if(lessonRepo.findByDateAndLessonNumberAndGroupId(
                lesson.getDate(), lesson.getLessonNumber(), lesson.getGroup().getId()) != null) {
            throw new LessonDateAndNumberExistsException("У группы уже есть пара в это время!");
        }

        lessonRepo.save(lesson);
        return LessonWithoutAttendance.toModel(lesson);
    }

    public LessonWithoutAttendance editLesson(LessonEntity lesson) {
        groupRepo.findById(lesson.getGroup().getId())
                .orElseThrow(()->new GroupNotFoundException("Группа не найдена"));
        teacherRepo.findById(lesson.getTeacher().getId())
                .orElseThrow(()-> new TeacherNotFoundException("Преподаватель не найден"));
        subjectRepo.findById(lesson.getSubject().getId())
                .orElseThrow(() -> new SubjectNotFoundException("Предмет не найден!"));

        LessonEntity lessonEntity = lessonRepo.findById(lesson.getId())
                .orElseThrow(() -> new LessonNotFoundException("Урок не найден!"));
        lessonEntity.setDate(lesson.getDate());
        lessonEntity.setLessonNumber(lesson.getLessonNumber());
        lessonEntity.setTeacher(lesson.getTeacher());
        lessonEntity.setSubject(lesson.getSubject());
        lessonEntity.setGroup(lesson.getGroup());

        lessonRepo.save(lessonEntity);
        return LessonWithoutAttendance.toModel(lesson);
    }

    public Lesson getLesson(Long id) throws LessonNotFoundException{
        LessonEntity lesson = lessonRepo.findById(id)
                .orElseThrow(() -> new LessonNotFoundException("Урок не найден!"));

        AttendanceEntity attendance = attendanceRepo.findByLesson_Id(id)
            .orElseThrow(()-> new AttendanceNotFoundException("Посещаемость для данной лекции не найдена!"));
        Lesson model = Lesson.toModel(lesson);
        model.setAttendances(Attendance.toModel(attendance));
        return model;
    }

    public List<LessonWithoutAttendance> getLessonsByGroup(Timestamp startDate, Timestamp endDate, Long groupId)
    throws GroupNotFoundException, LessonNotFoundException {

        if(groupRepo.findById(groupId).isEmpty()) {
            throw new GroupNotFoundException("Группа не найдена!");
        }
        if(lessonRepo.findByDateBetween(startDate, endDate).isEmpty()) {
            throw new LessonForPeriodNotFound("Лекции в этот промежуток дат не найдены!");
        }
        List<LessonEntity> lessonEntities = lessonRepo.findAllByDateBetweenAndGroupId(startDate, endDate, groupId);
        List<LessonWithoutAttendance> models = new ArrayList<>();
        for(LessonEntity lesson: lessonEntities) {
            models.add(LessonWithoutAttendance.toModel(lesson));
        }
        return models;
    }

    public List<LessonWithoutAttendance> getLessonsByTeacherId(Timestamp startDate, Timestamp endDate, Long teacherId)
    throws TeacherNotFoundException, LessonNotFoundException {
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
