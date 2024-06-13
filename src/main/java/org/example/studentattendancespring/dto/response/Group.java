package org.example.studentattendancespring.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.example.studentattendancespring.entity.GroupEntity;


@Getter
@Setter
public class Group {
    private Long id;
    private  String groupName;

    public static Group toModel(GroupEntity group){
        Group model = new Group();
        model.setId(group.getId());
        model.setGroupName(group.getName());
        return model;
    }

}
