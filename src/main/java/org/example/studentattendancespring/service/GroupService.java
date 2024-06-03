package org.example.studentattendancespring.service;

import org.example.studentattendancespring.entity.GroupEntity;
import org.example.studentattendancespring.exception.GroupNotFoundException;
import org.example.studentattendancespring.exception.GroupWithNameAlreadyExistsException;
import org.example.studentattendancespring.model.dto.Group;
import org.example.studentattendancespring.repository.GroupRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupService {
    private final GroupRepo groupRepo;

    @Autowired
    public GroupService(GroupRepo groupRepo) {
        this.groupRepo = groupRepo;
    }

    public Group addGroup(GroupEntity group) {
        if(groupRepo.findByName(group.getName()) != null){
            throw new GroupWithNameAlreadyExistsException("Группа с таким именем уже существует!");
        }
        groupRepo.save(group);
        return Group.toModel(group);
    }

    public Group editGroup(GroupEntity group) {
        GroupEntity groupEntity = groupRepo.findById(group.getId())
                .orElseThrow(()-> new GroupNotFoundException("Группа не найдена!"));
        groupEntity.setName(group.getName());
        groupRepo.save(groupEntity);
        return Group.toModel(group);
    }

    public Group getGroup(Long id) {
        GroupEntity group = groupRepo.findById(id)
                .orElseThrow(()-> new GroupNotFoundException("Группа не найдена!"));
        return Group.toModel(group);
    }

    public List<Group> getAllGroups() {
        List<GroupEntity> groupEntities = groupRepo.findAll();
        return groupEntities.stream()
                .map(Group::toModel)
                .collect(Collectors.toList());
    }

    public Long deleteGroup(Long id) {
        groupRepo.deleteById(id);
        return id;
    }
}
