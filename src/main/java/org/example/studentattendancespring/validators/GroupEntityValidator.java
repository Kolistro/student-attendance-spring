package org.example.studentattendancespring.validators;

import org.example.studentattendancespring.entity.GroupEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class GroupEntityValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return GroupEntity.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        GroupEntity group = (GroupEntity) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors,
                "name",
                "NotEmpty.groupEntity.name",
                "Название группы не может быть пустым");
    }
}
