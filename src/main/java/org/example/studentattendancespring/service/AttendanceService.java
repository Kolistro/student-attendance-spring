package org.example.studentattendancespring.service;

import jakarta.transaction.Transactional;
import org.example.studentattendancespring.dto.request.AttendanceDTO;
import org.example.studentattendancespring.entity.*;
import org.example.studentattendancespring.exception.*;
import org.example.studentattendancespring.dto.response.Attendance;
import org.example.studentattendancespring.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AttendanceService {
    private final AttendanceRepo attendanceRepo;
    private final GroupRepo groupRepo;
    private final LessonRepo lessonRepo;
    private final StudentRepo studentRepo;
    private final AttendanceStudentMappingRepo mappingRepo;


    @Autowired
    public AttendanceService(AttendanceRepo attendanceRepo, GroupRepo groupRepo,
                             LessonRepo lessonRepo, StudentRepo studentRepo, AttendanceStudentMappingRepo mappingRepo) {
        this.attendanceRepo = attendanceRepo;
        this.groupRepo = groupRepo;
        this.lessonRepo = lessonRepo;
        this.studentRepo = studentRepo;
        this.mappingRepo = mappingRepo;
    }

    public Attendance addAttendance(Long idLesson) throws GroupNotFoundException {
        if(attendanceRepo.findByLesson_Id(idLesson).isPresent()){
            throw new AttendanceForLessonAlreadyExistsException("Посещаемость для данной лекции уже существует!");
        }
        LessonEntity lesson = lessonRepo.findById(idLesson)
                .orElseThrow(() -> new LessonNotFoundException("Лекция не найдена"));

        List<StudentEntity> students = studentRepo.findAllByGroup_Id(lesson.getGroup().getId());

        AttendanceEntity attendanceEntity = new AttendanceEntity();
        attendanceEntity.setLesson(lesson);
        attendanceEntity.setGroup(lesson.getGroup());

        for (StudentEntity student : students) {
            AttendanceStudentMappingEntity mapping = new AttendanceStudentMappingEntity();
            mapping.setAttendance(attendanceEntity);
            mapping.setStudent(student);
            mapping.setIsPresent(false);
            attendanceEntity.addStudentMapping(mapping);
        }
        attendanceRepo.save(attendanceEntity);
        mappingRepo.saveAll(attendanceEntity.getStudentMappings());
        return Attendance.toModel(attendanceEntity);
    }

    public Attendance editAttendance(AttendanceDTO attendance) throws AttendanceNotFoundException{
        AttendanceEntity attendanceEntity = attendanceRepo.findById(attendance.getIdAttendance())
                .orElseThrow(() -> new AttendanceNotFoundException("Посещаемость не найдена!"));
        List<AttendanceStudentMappingEntity> listMapping = mappingRepo.findByAttendanceId(attendance.getIdAttendance());
        if(listMapping.isEmpty()){
            throw new AttendanceStudentMappingNotFoundException("Список посещения не найден!");
        }
        LessonEntity lesson = lessonRepo.findById(attendance.getLessonId())
                .orElseThrow(() -> new LessonNotFoundException("Лекция не найдена"));

        for (Map.Entry<Long, Boolean> entry : attendance.getStudentMap().entrySet()) {
            AttendanceStudentMappingEntity student = mappingRepo
                    .findByAttendanceIdAndStudentId(attendance.getIdAttendance(), entry.getKey())
                    .orElseThrow(()-> new AttendanceStudentMappingNotFoundException("Студент в списке посещаемости не найден!"));
            student.setIsPresent(entry.getValue());
            student.setAttendance(attendanceEntity);
            mappingRepo.save(student);
        }
        attendanceEntity.setLesson(lesson);
        attendanceRepo.save(attendanceEntity);
        return Attendance.toModel(attendanceEntity);
    }

    public Attendance editAttendanceStudent(Long id, Long studentId){
        AttendanceEntity attendance = attendanceRepo.findById(id)
                .orElseThrow(() -> new AttendanceNotFoundException("Посещаемость не найдена"));
        StudentEntity student = studentRepo.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException("Студент не найден"));
        AttendanceStudentMappingEntity mapping = mappingRepo.findByAttendanceIdAndStudentId(id, studentId)
                .orElseThrow(()-> new AttendanceStudentMappingNotFoundException("Студент в списке посещаемости не найден!"));

        attendance.getStudentMappings().remove(mapping);
        mapping.setIsPresent(!mapping.getIsPresent());
        attendance.addStudentMapping(mapping);

        mappingRepo.save(mapping);
        attendanceRepo.save(attendance);
        return Attendance.toModel(attendance);
    }

    public Attendance getAttendance(Long id) throws AttendanceNotFoundException{
        AttendanceEntity attendance = attendanceRepo.findById(id)
                .orElseThrow(() -> new AttendanceNotFoundException("Посещаемость не найдена"));
        return Attendance.toModel(attendance);
    }

    public List<Attendance> getAttendancesByGroupId(Long groupId) throws GroupNotFoundException{
        if(groupRepo.findById(groupId).isEmpty()) {
            throw new GroupNotFoundException("Группа не найдена!");
        }
        List<AttendanceEntity> attendanceEntities = attendanceRepo.findAllByGroup_Id(groupId);
        return attendanceEntities.stream()
                .map(Attendance::toModel)
                .collect(Collectors.toList());
    }

    public Attendance getAttendancesByLessonId(Long lessonId) throws LessonNotFoundException {
        if(lessonRepo.findById(lessonId).isEmpty()) {
            throw new LessonNotFoundException("Урок не найден!");
        }
        AttendanceEntity attendance = attendanceRepo.findByLesson_Id(lessonId)
                .orElseThrow(()->new AttendanceNotFoundException("Посещаемость для данной лекции не найдена!"));
        return Attendance.toModel(attendance);
    }

    @Transactional
    public Long deleteAttendance(Long id) {
        attendanceRepo.findById(id)
                .orElseThrow(()-> new AttendanceNotFoundException("Посещаемость не найдена!"));
        List<AttendanceStudentMappingEntity> list = mappingRepo.findByAttendanceId(id);
        if(list.isEmpty()){
            throw new AttendanceStudentMappingNotFoundException("Список посещаемости не найден!");
        }
        mappingRepo.deleteAll(list);
        //mappingRepo.deleteAllByAttendanceId(id);
        attendanceRepo.deleteById(id);
        return id;
    }
}
