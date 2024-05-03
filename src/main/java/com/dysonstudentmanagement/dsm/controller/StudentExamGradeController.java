package com.dysonstudentmanagement.dsm.controller;

import com.dysonstudentmanagement.dsm.dto.StudentExamGradeDto;
import com.dysonstudentmanagement.dsm.entity.studentexamgrade.StudentExamGradeCompositeKey;
import com.dysonstudentmanagement.dsm.service.StudentExamGradeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@AllArgsConstructor
@RestController
@RequestMapping("/api/studentExamGrade")
/*
StudentExamGradeController

Controller class for handling student exam grade related API endpoints.

Original Author: Yijie Zhu 27/04/2024
Modifying Author: Billy Peters 28/04/2024 Implemented exam composite keys
 */
public class StudentExamGradeController {

    private final StudentExamGradeService studentExamGradeService;

    @PostMapping
    public ResponseEntity<StudentExamGradeDto> createStudentExamGrade(@RequestBody StudentExamGradeDto studentExamGradeDto) {
        StudentExamGradeDto createdExam = studentExamGradeService.createStudentExamGrade(studentExamGradeDto);
        return new ResponseEntity<>(createdExam, HttpStatus.CREATED);
    }

    @GetMapping("{moduleID}/{examID}/{studentID}")
    public ResponseEntity<StudentExamGradeDto> getStudentExamGrade(@PathVariable("moduleID") String moduleID,
                                                                   @PathVariable("examID") int examID,
                                                                   @PathVariable("studentID") String studentID) {

        StudentExamGradeCompositeKey targetKey = new StudentExamGradeCompositeKey(moduleID,examID,studentID);
        StudentExamGradeDto studentExamGradeDto = studentExamGradeService.getStudentExamGrade(targetKey);
        return ResponseEntity.ok(studentExamGradeDto);
    }

    @GetMapping("{moduleID}/{examID}")
    public ResponseEntity<List<StudentExamGradeDto>> getStudentExamGradeByModuleIDAndExamID(@PathVariable("moduleID") String moduleID,
                                                                   @PathVariable("examID") int examID) {
        List<StudentExamGradeDto> studentExamGradeDtos = studentExamGradeService.getStudentExamGradeByModuleIDAndExamID(moduleID, examID);
        return ResponseEntity.ok(studentExamGradeDtos);
    }

    @PutMapping("{moduleID}/{examID}/{studentID}")
    public ResponseEntity<StudentExamGradeDto> updateStudentExamGrade(@PathVariable("moduleID") String moduleID,
                                                                      @PathVariable("examID") int examID,
                                                                      @PathVariable("studentID") String studentID,
                                                                      @RequestBody StudentExamGradeDto updateDto) {
        StudentExamGradeCompositeKey targetKey = new StudentExamGradeCompositeKey(moduleID,examID,studentID);
        StudentExamGradeDto updatedExam = studentExamGradeService.updateStudentExamGrade(targetKey, updateDto);
        return ResponseEntity.ok(updatedExam);
    }

    @DeleteMapping("{moduleID}/{examID}/{studentID}")
    public ResponseEntity<String> deleteStudentExamGrade(@PathVariable("moduleID") String moduleID,
                                                         @PathVariable("examID") int examID,
                                                         @PathVariable("studentID") String studentID) {
        StudentExamGradeCompositeKey targetKey = new StudentExamGradeCompositeKey(moduleID,examID,studentID);
        studentExamGradeService.deleteStudentExamGrade(targetKey);
        return ResponseEntity.ok("StudentExamGrade record deleted Successfully");
    }
}

