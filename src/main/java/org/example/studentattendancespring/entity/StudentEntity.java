package org.example.studentattendancespring.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;


// студент
@Entity
@Table(name = "student")
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel. NONE)
    private Long id;

    @JsonProperty("last_name")
    @NotBlank(message = "Фамилия не может быть пустой")
    private String lastName;
    @JsonProperty("first_name")
    @NotBlank(message = "Имя не может быть пустым")
    private String firstName;
    @JsonProperty("middle_name")
    @NotNull
    private String middleName;
    @JsonProperty("status")
    @NotBlank
    private String status;

    @Getter
    @ManyToOne
    @JoinColumn(name = "group_students_id")
    private GroupEntity group;

}
