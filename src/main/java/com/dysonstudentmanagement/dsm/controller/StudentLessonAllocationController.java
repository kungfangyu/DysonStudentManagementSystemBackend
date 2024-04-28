package com.dysonstudentmanagement.dsm.controller;

import com.dysonstudentmanagement.dsm.dto.StudentLessonAllocationDto;
import com.dysonstudentmanagement.dsm.entity.studentlessonallocation.StudentLessonAllocationCompositeKey;
import com.dysonstudentmanagement.dsm.service.StudentLessonAllocationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/studentLessonAllocation")
public class StudentLessonAllocationController {

    private StudentLessonAllocationService studentLessonAllocationService;
    @PostMapping
    public ResponseEntity<StudentLessonAllocationDto> createStudentLessonAllocation(@RequestBody StudentLessonAllocationDto studentLessonAllocationDto){
        StudentLessonAllocationDto savedData = studentLessonAllocationService.createStudentLessonAllocation(studentLessonAllocationDto);
        return new ResponseEntity<>(savedData, HttpStatus.CREATED);
    }

    @GetMapping("/by-studentID/{studentID}")
    public ResponseEntity<List<StudentLessonAllocationDto>> getStudentLessonAllocationByStudentID(@PathVariable("studentID") String studentID){
        List<StudentLessonAllocationDto> studentLessonAllocationDtos = studentLessonAllocationService.getStudentLessonAllocationByStudentID(studentID);
        return ResponseEntity.ok(studentLessonAllocationDtos);
    }

    @GetMapping("/by-moduleID/{moduleID}")
    public ResponseEntity<List<StudentLessonAllocationDto>> getStudentLessonAllocationByModuleID(@PathVariable("moduleID") String moduleID){
        List<StudentLessonAllocationDto> studentLessonAllocationDtos = studentLessonAllocationService.getStudentLessonAllocationByModuleID(moduleID);
        return ResponseEntity.ok(studentLessonAllocationDtos);
    }

    @GetMapping("/by-moduleID-and-studentID/{moduleID}/{studentID}")
    public ResponseEntity<List<StudentLessonAllocationDto>> getStudentLessonAllocationByModuleIDAndStudentID(@PathVariable("moduleID") String moduleID,@PathVariable("studentID") String studentID){
        List<StudentLessonAllocationDto> studentLessonAllocationDtos = studentLessonAllocationService.getStudentLessonAllocationByModuleIDAndStudentID(moduleID,studentID);
        return ResponseEntity.ok(studentLessonAllocationDtos);
    }

    @GetMapping("/by-moduleID-and-lessonID/{moduleID}/{lessonID}")
    public ResponseEntity<List<StudentLessonAllocationDto>> getStudentLessonAllocationByModuleIDAndLessonID(@PathVariable("moduleID") String moduleID,@PathVariable("lessonID") int lessonID){
        List<StudentLessonAllocationDto> studentLessonAllocationDtos = studentLessonAllocationService.getStudentLessonAllocationByModuleIDAndLessonID(moduleID,lessonID);
        return ResponseEntity.ok(studentLessonAllocationDtos);
    }

    @PutMapping("/{moduleID}/{lessonID}/{studentID}")
    public ResponseEntity<StudentLessonAllocationDto> updateStudentLessonAllocation(@PathVariable("moduleID") String moduleID,
                                                                                    @PathVariable("lessonID") int lessonID,
                                                                                    @PathVariable("studentID") String studentID,
                                                                                    @RequestBody StudentLessonAllocationDto updateDto){
        StudentLessonAllocationCompositeKey targetKey = new StudentLessonAllocationCompositeKey(moduleID,lessonID,studentID);
        StudentLessonAllocationDto savedData = studentLessonAllocationService.updateStudentLessonAllocation(targetKey,updateDto);

        return ResponseEntity.ok(savedData);
    }

    @DeleteMapping("/{moduleID}/{lessonID}/{studentID}")
    public ResponseEntity<String> deleteSudentLessonAllocation(@PathVariable("moduleID") String moduleID,
                                                                                    @PathVariable("lessonID") int lessonID,
                                                                                    @PathVariable("studentID") String studentID){
        StudentLessonAllocationCompositeKey targetKey = new StudentLessonAllocationCompositeKey(moduleID,lessonID,studentID);
        studentLessonAllocationService.deleteStudentLessonAllocation(targetKey);

        return ResponseEntity.ok("StudentLessonAllocation record successfully completed");
    }
}
