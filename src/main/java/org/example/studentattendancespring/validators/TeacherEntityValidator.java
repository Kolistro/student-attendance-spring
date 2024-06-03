package org.example.studentattendancespring.validators;

import org.example.studentattendancespring.entity.TeacherEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class TeacherEntityValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return TeacherEntity.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        TeacherEntity teacher = (TeacherEntity) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors,
                "lastName",
                "NotEmpty.teacher.lastName",
                "Фамилия не может быть пустой");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,
                "firstName",
                "NotEmpty.teacher.firstName",
                "Имя не может быть пустым");

        if (teacher.getMiddleName() == null) {
            errors.rejectValue("middleName",
                    "NotNull.teacher.middleName",
                    "Отчество не может быть пустым");
        }
    }
}
