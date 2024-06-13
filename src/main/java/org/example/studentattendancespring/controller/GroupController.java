package org.example.studentattendancespring.controller;

import org.example.studentattendancespring.entity.GroupEntity;
import org.example.studentattendancespring.dto.response.Group;
import org.example.studentattendancespring.dto.response.CommonResponse;
import org.example.studentattendancespring.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/group", consumes = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class GroupController {
    @Autowired
    private final GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping
    public ResponseEntity<CommonResponse<GroupEntity>> addGroup(@RequestBody GroupEntity group) {
        CommonResponse<GroupEntity> commonResponse;
        HttpStatus status;
        try {
            status = HttpStatus.OK;
            commonResponse = new CommonResponse<>(groupService.addGroup(group), status.value());
            return ResponseEntity.ok(commonResponse);
        }catch (Exception e){
            status = HttpStatus.BAD_REQUEST;
            return ResponseEntity.badRequest().body(new CommonResponse<>(status.value(), e.getMessage()));
        }
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommonResponse<Group>> editGroup(@RequestBody GroupEntity group) {
        CommonResponse<Group> commonResponse;
        HttpStatus status;
        try {
            status = HttpStatus.OK;
            commonResponse = new CommonResponse<>(groupService.editGroup(group), status.value());
            return ResponseEntity.ok(commonResponse);
        }catch (Exception e){
            status = HttpStatus.BAD_REQUEST;
            return ResponseEntity.badRequest().body(new CommonResponse<>(status.value(), e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse<GroupEntity>> getGroup(@PathVariable Long id) {
        CommonResponse<GroupEntity> commonResponse;
        HttpStatus status;
        try {
            status = HttpStatus.OK;
            commonResponse = new CommonResponse<>(groupService.getGroup(id), status.value());
            return ResponseEntity.ok(commonResponse);
        }catch (Exception e){
            status = HttpStatus.BAD_REQUEST;
            return ResponseEntity.badRequest().body(new CommonResponse<>(status.value(), e.getMessage()));
        }
    }

    @GetMapping("/groups")
    public ResponseEntity<CommonResponse<List<Group>>> getAllGroups() {
        CommonResponse<List<Group>> commonResponse;
        HttpStatus status;
        try {
            status = HttpStatus.OK;
            commonResponse = new CommonResponse<>(groupService.getAllGroups(), status.value());
            return ResponseEntity.ok(commonResponse);
        }catch (Exception e){
            status = HttpStatus.BAD_REQUEST;
            return ResponseEntity.badRequest().body(new CommonResponse<>(status.value(), e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponse<Long>> deleteGroup(@PathVariable Long id) {
        CommonResponse<Long> commonResponse;
        HttpStatus status;
        try {
            status = HttpStatus.OK;
            commonResponse = new CommonResponse<>(groupService.deleteGroup(id), status.value());
            return ResponseEntity.ok(commonResponse);
        }catch (Exception e){
            status = HttpStatus.BAD_REQUEST;
            return ResponseEntity.badRequest().body(new CommonResponse<>(status.value(), e.getMessage()));
        }
    }

}
