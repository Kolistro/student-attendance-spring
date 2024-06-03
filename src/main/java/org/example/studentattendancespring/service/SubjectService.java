package org.example.studentattendancespring.service;

import org.example.studentattendancespring.entity.StudentEntity;
import org.example.studentattendancespring.entity.SubjectEntity;
import org.example.studentattendancespring.entity.TeacherEntity;
import org.example.studentattendancespring.exception.SubjectNotFoundException;
import org.example.studentattendancespring.exception.SubjectWithSameNameAlreadyExistsException;
import org.example.studentattendancespring.exception.TeacherNotFoundException;
import org.example.studentattendancespring.model.dto.Student;
import org.example.studentattendancespring.model.dto.Subject;
import org.example.studentattendancespring.model.dto.Teacher;
import org.example.studentattendancespring.repository.SubjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubjectService {
    private final SubjectRepo subjectRepo;

    @Autowired
    public SubjectService(SubjectRepo subjectRepo) {
        this.subjectRepo = subjectRepo;
    }

    public Subject addSubject(SubjectEntity subject) {
        if (subjectRepo.findByName(subject.getSubjectName())!=null){
            throw new SubjectWithSameNameAlreadyExistsException("Такой предмет уже существует!");
        }
        subjectRepo.save(subject);
        return Subject.toModel(subject);
    }

    public Subject editSubject(SubjectEntity subject) {
        SubjectEntity subjectEntity = subjectRepo.findById(subject.getId())
                .orElseThrow(()-> new SubjectNotFoundException("Предмет не найден!"));
        subjectEntity.setSubjectName(subject.getSubjectName());
        subjectRepo.save(subjectEntity);
        return Subject.toModel(subject);
    }

    public Subject getSubject(Long id) {
        SubjectEntity subject = subjectRepo.findById(id)
                .orElseThrow(()-> new SubjectNotFoundException("Предмет не найден!"));
        return Subject.toModel(subject);
    }

    public List<Subject> getAllSubjects() {
        List<SubjectEntity> subjectEntities = subjectRepo.findAll();
        return subjectEntities.stream()
                .map(Subject::toModel)
                .collect(Collectors.toList());
    }

    public Long deleteSubject(Long id) {
        subjectRepo.deleteById(id);
        return id;
    }

}
