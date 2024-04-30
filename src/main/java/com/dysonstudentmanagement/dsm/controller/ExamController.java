package com.dysonstudentmanagement.dsm.controller;

import com.dysonstudentmanagement.dsm.dto.ExamDto;
import com.dysonstudentmanagement.dsm.entity.exam.ExamCompositeKey;
import com.dysonstudentmanagement.dsm.service.ExamService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for handling exam-related API endpoints.
 */
@AllArgsConstructor
@RestController
@RequestMapping("/api/exam")
public class ExamController {

    private final ExamService examService;

    @PostMapping
    public ResponseEntity<ExamDto> createExam(@RequestBody ExamDto examDto) {
        ExamDto createdExam = examService.createExam(examDto);
        return new ResponseEntity<>(createdExam, HttpStatus.CREATED);
    }

    @GetMapping("{moduleID}/{examID}")
    public ResponseEntity<ExamDto> getExam(@PathVariable("moduleID") String moduleID, @PathVariable("examID") Integer examID) {
        ExamCompositeKey targetKey = new ExamCompositeKey(moduleID,examID);
        ExamDto examDto = examService.getExam(targetKey);
        return ResponseEntity.ok(examDto);
    }

    @GetMapping("/by-moduleID/{moduleID}")
    public ResponseEntity<List<ExamDto>> getExamByModuleID(@PathVariable("moduleID") String moduleID) {
        List<ExamDto> examDtos = examService.getExamByModuleID(moduleID);
        return ResponseEntity.ok(examDtos);
    }


    @PutMapping("{moduleID}/{examID}")
    public ResponseEntity<ExamDto> updateExam(@PathVariable("moduleID")String moduleID,
                                              @PathVariable("examID") int examID,
                                              @RequestBody ExamDto updateDto) {
        ExamCompositeKey targetKey = new ExamCompositeKey(moduleID,examID);
        updateDto.setExamID(examID);
        ExamDto updatedExam = examService.updateExam(targetKey, updateDto);
        return ResponseEntity.ok(updatedExam);
    }

    @DeleteMapping("{moduleID}/{examID}")
    public ResponseEntity<String> deleteExam(@PathVariable("moduleID")String moduleID,
                                             @PathVariable("examID") int examID) {
        ExamCompositeKey targetKey = new ExamCompositeKey(moduleID,examID);
        examService.deleteExam(targetKey);
        return ResponseEntity.ok("Exam deleted Successfully");
    }
}

