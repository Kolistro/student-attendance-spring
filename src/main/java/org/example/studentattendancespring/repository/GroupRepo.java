package org.example.studentattendancespring.repository;

import org.example.studentattendancespring.entity.GroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepo extends JpaRepository<GroupEntity, Long> {
    GroupEntity findByName(String name);

}
