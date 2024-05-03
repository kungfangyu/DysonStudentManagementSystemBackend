package com.dysonstudentmanagement.dsm.controller;

import com.dysonstudentmanagement.dsm.dto.StudentProgrammeEnrolmentDto;
import com.dysonstudentmanagement.dsm.entity.studentprogrammeenrolment.StudentProgrammeEnrolmentCompositeKey;
import com.dysonstudentmanagement.dsm.service.StudentProgrammeEnrolmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/studentProgrammeEnrolment")
/*
studentProgrammeEnrolmentController

Provides api mappings to access programmeService methods.

Original Author: Imran Matloob 24/04/2024
 */
public class StudentProgrammeEnrolmentController {

    private StudentProgrammeEnrolmentService studentProgrammeEnrolmentService;

    @PostMapping
    public ResponseEntity<StudentProgrammeEnrolmentDto> createStudentProgrammeEnrolment(@RequestBody StudentProgrammeEnrolmentDto studentProgrammeEnrolmentDto) {
        System.out.println("studentProgrammeEnrolmentDto:");
        System.out.println(studentProgrammeEnrolmentDto);
        StudentProgrammeEnrolmentDto savedStudentProgrammeEnrolment = studentProgrammeEnrolmentService.createStudentProgrammeEnrolment(studentProgrammeEnrolmentDto);
        return new ResponseEntity<>(savedStudentProgrammeEnrolment, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<StudentProgrammeEnrolmentDto>> getAllStudentProgrammeEnrolment() {
        List<StudentProgrammeEnrolmentDto> studentProgrammeEnrolmentList = studentProgrammeEnrolmentService.getAllStudentProgrammeEnrolment();
        return ResponseEntity.ok(studentProgrammeEnrolmentList);
    }

    @GetMapping("/{studentID}/{programmeID}")
    public ResponseEntity<StudentProgrammeEnrolmentDto> getStudentProgrammeEnrolment(@PathVariable("studentID") String studentID, @PathVariable("programmeID") String programmeID) {
        StudentProgrammeEnrolmentCompositeKey targetKey = new StudentProgrammeEnrolmentCompositeKey(studentID, programmeID);
        StudentProgrammeEnrolmentDto studentProgrammeEnrolmentDto = studentProgrammeEnrolmentService.getStudentProgrammeEnrolmentDto(targetKey);
        return ResponseEntity.ok(studentProgrammeEnrolmentDto);
    }

    @GetMapping("/byStudent/{studentID}")
    public ResponseEntity<List<StudentProgrammeEnrolmentDto>> getStudentProgrammeEnrolmentByStudentID(@PathVariable("studentID") String studentID) {
        List<StudentProgrammeEnrolmentDto> studentProgrammeEnrolmentDtos = studentProgrammeEnrolmentService.getStudentProgrammeEnrolmentDtoByStudentID(studentID);
        return ResponseEntity.ok(studentProgrammeEnrolmentDtos);
    }

    @GetMapping("/byProgramme/{programmeID}")
    public ResponseEntity<List<StudentProgrammeEnrolmentDto>> getStudentProgrammeEnrolmentByProgrammeID(@PathVariable("programmeID") String programmeID) {
        List<StudentProgrammeEnrolmentDto> studentProgrammeEnrolmentDtos = studentProgrammeEnrolmentService.getStudentProgrammeEnrolmentDtoByProgrammeID(programmeID);
        return ResponseEntity.ok(studentProgrammeEnrolmentDtos);
    }

    @PutMapping("/{studentID}/{programmeID}")
    public ResponseEntity<StudentProgrammeEnrolmentDto> updateStudentProgrammeEnrolment(@PathVariable("studentID") String studentID, @PathVariable("programmeID") String programmeID, @RequestBody StudentProgrammeEnrolmentDto updatedStudentProgrammeEnrolmentDto) {
        StudentProgrammeEnrolmentCompositeKey targetKey = new StudentProgrammeEnrolmentCompositeKey(studentID, programmeID);
        StudentProgrammeEnrolmentDto savedStudentProgrammeEnrolment = studentProgrammeEnrolmentService.updateStudentProgrammeEnrolment(targetKey, updatedStudentProgrammeEnrolmentDto);
        return ResponseEntity.ok(savedStudentProgrammeEnrolment);
    }

    @DeleteMapping("/{studentID}/{programmeID}")
    public ResponseEntity<String> deleteStudentProgrammeEnrolment(@PathVariable("studentID") String studentID, @PathVariable("programmeID") String programmeID) {
        StudentProgrammeEnrolmentCompositeKey targetKey = new StudentProgrammeEnrolmentCompositeKey(studentID, programmeID);
        studentProgrammeEnrolmentService.deleteStudentProgrammeEnrolment(targetKey);
        return ResponseEntity.ok("Student Programme Enrolment record deleted successfully");
    }
}