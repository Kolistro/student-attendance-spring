package org.example.studentattendancespring.model.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.studentattendancespring.entity.GroupEntity;
import org.example.studentattendancespring.entity.StudentEntity;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Group {
    private  String groupName;

    public static Group toModel(GroupEntity group){
        Group model = new Group();
        model.setGroupName(group.getGroupName());
        return model;
    }

}
