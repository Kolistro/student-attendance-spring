package org.example.studentattendancespring.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;


// студент

@Entity
@Table(name = "student")
@Getter
@Setter
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Фамилия не может быть пустой")
    private String lastName;
    @NotBlank(message = "Имя не может быть пустым")
    private String firstName;
    @NotNull
    private String middleName;
    @NotBlank
    private Status status;

    @ManyToOne
    @JoinColumn(name = "groups_id")
    @NotNull(message = "Необходимо указать группу")
    private GroupEntity group;

}
