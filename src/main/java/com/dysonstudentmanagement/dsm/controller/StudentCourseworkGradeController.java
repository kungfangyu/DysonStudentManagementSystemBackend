package com.dysonstudentmanagement.dsm.controller;

import com.dysonstudentmanagement.dsm.dto.StudentCourseworkGradeDto;
import com.dysonstudentmanagement.dsm.entity.studentcourseworkgrade.StudentCourseworkGradeCompositeKey;
import com.dysonstudentmanagement.dsm.service.StudentCourseworkGradeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/StudentCourseworkGrade")
public class StudentCourseworkGradeController {
    private StudentCourseworkGradeService studentCourseworkGradeService;

    @PostMapping
    public ResponseEntity<StudentCourseworkGradeDto> createStudentCourseworkGrade(@RequestBody StudentCourseworkGradeDto studentCourseworkGradeDto) {
        StudentCourseworkGradeDto studentCourseworkGrade = studentCourseworkGradeService.createStudentCourseworkGrade(studentCourseworkGradeDto);
        return new ResponseEntity<>(studentCourseworkGrade, HttpStatus.CREATED);
    }

    @GetMapping("{studentID}/{moduleID}/{courseworkID}")
    public ResponseEntity<StudentCourseworkGradeDto> getIndividualCoursework(@PathVariable("studentID") String studentID,
                                                                 @PathVariable("moduleID") String moduleID,
                                                                 @PathVariable("courseworkID") int courseworkID){
        StudentCourseworkGradeCompositeKey newCourseworkGradeCompositeKey = new StudentCourseworkGradeCompositeKey(studentID, courseworkID, moduleID);
        StudentCourseworkGradeDto studentCourseworkGradeDto = studentCourseworkGradeService.getStudentCourseworkGrade(newCourseworkGradeCompositeKey);
        return ResponseEntity.ok(studentCourseworkGradeDto);
    }

    @GetMapping("{moduleID}/{courseworkID}")
    public ResponseEntity<List<StudentCourseworkGradeDto>> getCourseworkByModuleIDAndCourseworkID(@PathVariable("moduleID") String moduleID,
                                                                                                  @PathVariable("courseworkID") int courseworkID){
        List<StudentCourseworkGradeDto> studentCourseworkGradeDtos = studentCourseworkGradeService.getStudentCourseGradeByModuleIdAndCourseworkID(moduleID,courseworkID);
        return ResponseEntity.ok(studentCourseworkGradeDtos);
    }

    @PutMapping("{studentID}/{moduleID}/{courseworkID}")
    public ResponseEntity<StudentCourseworkGradeDto> updateCoursework(@PathVariable("studentID") String studentID,
                                                          @PathVariable("moduleID") String moduleID,
                                                          @PathVariable("courseworkID") int courseworkID,
                                                          @RequestBody StudentCourseworkGradeDto updateDto) {
        StudentCourseworkGradeCompositeKey studentCourseworkGradeCompositeKey = new StudentCourseworkGradeCompositeKey(studentID, courseworkID, moduleID);
        StudentCourseworkGradeDto studentCourseworkGradeDto = studentCourseworkGradeService.updateStudentCourseworkGrade(studentCourseworkGradeCompositeKey, updateDto);
        return ResponseEntity.ok(studentCourseworkGradeDto);
    }

    @DeleteMapping("{studentID}/{moduleID}/{courseworkID}")
    public ResponseEntity<String> deleteCoursework(@PathVariable("studentID") String studentID,
                                                   @PathVariable("moduleID") String moduleID,
                                                   @PathVariable("courseworkID") int courseworkID) {
        StudentCourseworkGradeCompositeKey studentCourseworkGradeCompositeKey = new StudentCourseworkGradeCompositeKey(studentID, courseworkID, moduleID);
        studentCourseworkGradeService.deleteStudentCourseworkGrade(studentCourseworkGradeCompositeKey);
        return ResponseEntity.ok(studentID + " " + moduleID + " " + courseworkID + ": Deleted Successfully");
    }
}
