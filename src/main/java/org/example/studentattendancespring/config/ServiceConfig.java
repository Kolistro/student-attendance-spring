package org.example.studentattendancespring.config;

import org.example.studentattendancespring.repository.*;
import org.example.studentattendancespring.service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {
    @Bean
    public TeacherService teacherService(TeacherRepo teacherRepo) {
        return new TeacherService(teacherRepo);
    }

    @Bean
    public SubjectService subjectService(SubjectRepo subjectRepo) {
        return new SubjectService(subjectRepo);
    }

    @Bean
    public StudentService studentService(StudentRepo studentRepo, GroupRepo groupRepo) {
        return new StudentService(studentRepo, groupRepo);
    }

    @Bean
    public LessonService lessonService(LessonRepo lessonRepo, GroupRepo groupRepo, TeacherRepo teacherRepo) {
        return new LessonService(lessonRepo, groupRepo, teacherRepo);
    }

    @Bean
    public GroupService groupService(GroupRepo groupRepo) {
        return new GroupService(groupRepo);
    }

    @Bean
    public AttendanceService attendanceService(AttendanceRepo attendanceRepo, GroupRepo groupRepo, LessonRepo lessonRepo) {
        return new AttendanceService(attendanceRepo, groupRepo, lessonRepo);
    }


}
