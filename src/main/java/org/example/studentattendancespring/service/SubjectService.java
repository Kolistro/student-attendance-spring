package org.example.studentattendancespring.service;

import org.example.studentattendancespring.entity.SubjectEntity;
import org.example.studentattendancespring.exception.SubjectNotFoundException;
import org.example.studentattendancespring.exception.SubjectWithSameNameAlreadyExistsException;
import org.example.studentattendancespring.dto.response.Subject;
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

    public Subject addSubject(SubjectEntity subject) throws SubjectWithSameNameAlreadyExistsException{
        if (subjectRepo.findByName(subject.getName())!=null){
            throw new SubjectWithSameNameAlreadyExistsException("Такой предмет уже существует!");
        }
        subjectRepo.save(subject);
        return Subject.toModel(subject);
    }

    public Subject editSubject(SubjectEntity subject) throws SubjectNotFoundException {
        SubjectEntity subjectEntity = subjectRepo.findById(subject.getId())
                .orElseThrow(()-> new SubjectNotFoundException("Предмет не найден!"));
        subjectEntity.setName(subject.getName());
        subjectRepo.save(subjectEntity);
        return Subject.toModel(subject);
    }

    public Subject getSubject(Long id) throws SubjectNotFoundException{
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
