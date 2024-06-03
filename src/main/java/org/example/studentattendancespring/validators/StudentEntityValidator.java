package org.example.studentattendancespring.validators;


import org.example.studentattendancespring.entity.StudentEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;

@Component
public class StudentEntityValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return StudentEntity.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        StudentEntity student = (StudentEntity) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors,
                "lastName",
                "NotEmpty.studentEntity.lastName",
                "Фамилия не может быть пустой");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,
                "firstName",
                "NotEmpty.studentEntity.firstName",
                "Имя не может быть пустым");
        ValidationUtils.rejectIfEmpty(errors,
                "group",
                "NotEmpty.lessonEntity.group",
                "Необходимо указать группу");

        if (student.getMiddleName() == null) {
            errors.rejectValue(
                    "middleName",
                    "NotNull.studentEntity.middleName",
                    "Отчество не может быть пустым");
        }

        if (student.getStatus() == null) {
            errors.rejectValue(
                    "status",
                    "NotBlank.studentEntity.status",
                    "Статус не может быть пустым");
        }

        if (student.getGroup() == null) {
            errors.rejectValue(
                    "group",
                    "NotNull.lessonEntity.group",
                    "Необходимо указать группу");
        }

    }
}
