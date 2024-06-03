package org.example.studentattendancespring.service;

import org.example.studentattendancespring.entity.StudentEntity;
import org.example.studentattendancespring.exception.GroupNotFoundException;
import org.example.studentattendancespring.exception.StudentNotFoundException;
import org.example.studentattendancespring.model.dto.Student;
import org.example.studentattendancespring.repository.GroupRepo;
import org.example.studentattendancespring.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepo studentRepo;
    private final GroupRepo groupRepo;

    @Autowired
    public StudentService(StudentRepo studentRepo, GroupRepo groupRepo) {
        this.studentRepo = studentRepo;
        this.groupRepo = groupRepo;
    }

    public Student addStudent(StudentEntity student) {
        studentRepo.save(student);
        return Student.toModel(student);
    }

    public Student editStudent(StudentEntity student) {
        StudentEntity studentEntity = studentRepo.findById(student.getId())
                .orElseThrow(() -> new StudentNotFoundException("Студент не найден!"));
        studentEntity.setFirstName(student.getFirstName());
        studentEntity.setLastName(student.getLastName());
        studentEntity.setMiddleName(student.getMiddleName());
        studentEntity.setStatus(student.getStatus());
        studentEntity.setGroup(student.getGroup());
        studentRepo.save(studentEntity);
        return Student.toModel(student);
    }

    public Student getStudent(Long id) {
        StudentEntity student = studentRepo.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Студент не найден!"));
        return Student.toModel(student);
    }

    public List<Student> getStudentsByGroup(Long idGroup) {
        if (groupRepo.findById(idGroup).isEmpty()) {
            throw new GroupNotFoundException("Группа не найдена!");
        }
        List<StudentEntity> studentEntities = studentRepo.findAllByGroup_Id(idGroup);
        return studentEntities.stream()
                .map(Student::toModel)
                .collect(Collectors.toList());
    }

    public Long deleteStudent(Long id) {
        studentRepo.deleteById(id);
        return id;
    }
}
