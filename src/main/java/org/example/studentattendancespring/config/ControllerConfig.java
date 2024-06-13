package org.example.studentattendancespring.config;

import org.example.studentattendancespring.controller.*;
import org.example.studentattendancespring.service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ControllerConfig {
    @Bean
    public TeacherController teacherController(TeacherService teacherService) {
        return new TeacherController(teacherService);
    }

    @Bean
    public StudentController studentController(StudentService studentService) {
        return new StudentController(studentService);
    }

    @Bean
    public SubjectController subjectController(SubjectService subjectService) {
        return new SubjectController(subjectService);
    }

    @Bean
    public LessonController lessonController(LessonService lessonService) {
        return new LessonController(lessonService);
    }

    @Bean
    public GroupController groupController(GroupService groupService) {
        return new GroupController(groupService);
    }

    @Bean
    public AttendanceController attendanceController(AttendanceService attendanceService) {
        return new AttendanceController(attendanceService);
    }
}
