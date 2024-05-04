package com.dysonstudentmanagement.dsm.controller;

import com.dysonstudentmanagement.dsm.dto.AdminModuleStudentDto;
import com.dysonstudentmanagement.dsm.dto.ModuleDetailsDto;
import com.dysonstudentmanagement.dsm.dto.StudentModuleGradeDto;
import com.dysonstudentmanagement.dsm.entity.studentmodulegrade.StudentModuleGradeCompositeKey;
import com.dysonstudentmanagement.dsm.service.StudentModuleGradeService;
import com.dysonstudentmanagement.dsm.service.impl.AdminModuleStudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/studentModuleGrade")
/*
StudentModuleGradeController

Provides api mappings to access studentModuleGradeService methods.

Original Author: Grace Liu 25/04/2024
 */
public class StudentModuleGradeController {
    private StudentModuleGradeService studentModuleGradeService;
    private AdminModuleStudentService adminModuleStudentService;

    @PostMapping
    public ResponseEntity<StudentModuleGradeDto> createStudentModuleGrade(@RequestBody StudentModuleGradeDto studentModuleGradeDto) {
        StudentModuleGradeDto savedGrade = studentModuleGradeService.createStudentModuleGrade(studentModuleGradeDto);
        return new ResponseEntity<>(savedGrade, HttpStatus.CREATED);
    }

    @GetMapping("{studentID}/{moduleID}")
    public ResponseEntity<StudentModuleGradeDto> getStudentModuleGrade(@PathVariable("studentID") String studentID,
                                                                       @PathVariable("moduleID") String moduleID) {
        StudentModuleGradeCompositeKey pk = new StudentModuleGradeCompositeKey(studentID, moduleID);
        return ResponseEntity.ok(studentModuleGradeService.getStudentModuleGrade(pk));
    }

    @GetMapping("{moduleID}")
    public ResponseEntity<List<StudentModuleGradeDto>> getStudentModuleGradesByModuleID(@PathVariable("moduleID") String moduleID) {
        return ResponseEntity.ok(studentModuleGradeService.getStudentModuleGradeByModuleID(moduleID));
    }

    @GetMapping("/getModulesDetailsByStudentID/{studentID}")
    public ResponseEntity<List<ModuleDetailsDto>> getModuleDetailsByStudentID(@PathVariable("studentID") String studentID) {
        return ResponseEntity.ok(studentModuleGradeService.getModuleDetailsByStudentID(studentID));
    }


    @GetMapping("student/{studentID}")
    public ResponseEntity<List<StudentModuleGradeDto>> getStudentModuleGradesByStudentID(@PathVariable("studentID") String studentID) {
        return ResponseEntity.ok(studentModuleGradeService.getStudentModuleGradeByStudentID(studentID));
    }

    @GetMapping("getAdminModuleStudents/{moduleID}")
    public ResponseEntity<List<AdminModuleStudentDto>> getAdminModuleStudentsByModuleID(@PathVariable("moduleID") String moduleID) {
        return  ResponseEntity.ok(adminModuleStudentService.getStudentsByModuleId(moduleID));
    }

    @PutMapping("{studentID}/{moduleID}")
    public ResponseEntity<StudentModuleGradeDto> updateStudentModuleGrade(@PathVariable("studentID") String studentID,
                                                                          @PathVariable("moduleID") String moduleID,
                                                                          @RequestBody StudentModuleGradeDto studentModuleGradeDto) {
        StudentModuleGradeCompositeKey pk = new StudentModuleGradeCompositeKey(studentID, moduleID);
        StudentModuleGradeDto savedGrade = studentModuleGradeService.updateStudentModuleGrade(pk, studentModuleGradeDto);
        return ResponseEntity.ok(savedGrade);
    }

    @DeleteMapping("{studentID}/{moduleID}")
    public ResponseEntity<String> deleteStudentModuleGrade(@PathVariable("studentID") String studentID,
                                                           @PathVariable("moduleID") String moduleID) {
        StudentModuleGradeCompositeKey pk = new StudentModuleGradeCompositeKey(studentID, moduleID);
        studentModuleGradeService.deleteStudentModuleGrade(pk);
        return new ResponseEntity<>("Student Module Grade deleted", HttpStatus.OK);
    }
}
