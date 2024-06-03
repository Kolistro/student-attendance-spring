package org.example.studentattendancespring.validators;

import org.example.studentattendancespring.entity.AttendanceEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class AttendanceEntityValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return AttendanceEntity.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        AttendanceEntity attendance = (AttendanceEntity) target;

        ValidationUtils.rejectIfEmpty(errors,
                "lesson",
                "NotEmpty.attendanceEntity.lesson",
                "Укажите занятие");
        ValidationUtils.rejectIfEmpty(errors,
                "group",
                "NotEmpty.attendanceEntity.group",
                "Укажите группу");

        if (attendance.getStudents() == null || attendance.getStudents().isEmpty()) {
            errors.rejectValue(
                    "students",
                    "NotEmpty.attendanceEntity.students",
                    "Необходимо указать студентов");
        }
    }
}
