package org.example.studentattendancespring.repository;

import org.example.studentattendancespring.entity.SubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepo extends JpaRepository<SubjectEntity, Long> {
    SubjectEntity findByName(String name);
}
