package org.example.studentattendancespring.service;

import org.example.studentattendancespring.entity.TeacherEntity;
import org.example.studentattendancespring.exception.TeacherNotFoundException;
import org.example.studentattendancespring.dto.response.Teacher;
import org.example.studentattendancespring.repository.TeacherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherService {
    private final TeacherRepo teacherRepo;

    @Autowired
    public TeacherService(TeacherRepo teacherRepo) {
        this.teacherRepo = teacherRepo;
    }

    public Teacher addTeacher(TeacherEntity teacher) {
        teacherRepo.save(teacher);
        return Teacher.toModel(teacher);
    }

    public Teacher editTeacher(TeacherEntity teacher) throws TeacherNotFoundException{
        TeacherEntity teacherEntity = teacherRepo.findById(teacher.getId())
                .orElseThrow(()-> new TeacherNotFoundException("Преподаватель не найден!"));
        teacherEntity.setFirstName(teacher.getFirstName());
        teacherEntity.setMiddleName(teacher.getMiddleName());
        teacherEntity.setLastName(teacher.getLastName());
        teacherRepo.save(teacherEntity);
        return Teacher.toModel(teacherEntity);
    }

    public Teacher getTeacher(Long id) throws TeacherNotFoundException{
        TeacherEntity teacher = teacherRepo.findById(id)
                .orElseThrow(()-> new TeacherNotFoundException("Преподаватель не найден!"));
        return Teacher.toModel(teacher);
    }

    public List<Teacher> getAllTeachers() {
        List<TeacherEntity> teacherEntities = teacherRepo.findAll();
        return teacherEntities.stream()
                .map(Teacher::toModel)
                .collect(Collectors.toList());
    }

    public Long deleteTeacher(Long id) {
        teacherRepo.deleteById(id);
        return id;
    }
}
