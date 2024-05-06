package com.dysonstudentmanagement.dsm.controller;

import com.dysonstudentmanagement.dsm.dto.AbsenceRequestDto;
import com.dysonstudentmanagement.dsm.entity.absencerequest.AbsenceRequestCompositeKey;
import com.dysonstudentmanagement.dsm.service.AbsenceRequestService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/absenceRequest")
/*
AbsenceRequestController

Provides api mappings to access AbsenceRequestService methods.

Original Author: Billy Peters 04/05/2024
 */
public class AbsenceRequestController {
    private AbsenceRequestService absenceRequestService;

    @PostMapping
    public ResponseEntity<AbsenceRequestDto> createAbsenceRequest(@RequestBody AbsenceRequestDto absenceRequestDto) {
        System.out.println(absenceRequestDto);
        AbsenceRequestDto savedData = absenceRequestService.createAbsenceRequest(absenceRequestDto);
        return new ResponseEntity<>(savedData, HttpStatus.CREATED);
    }

    @GetMapping("{moduleID}/{lessonID}/{studentID}/{requestID}")
    public ResponseEntity<AbsenceRequestDto> getAbsenceRequest(@PathVariable("moduleID") String moduleID,
                                                               @PathVariable("lessonID") int lessonID,
                                                               @PathVariable("studentID") String studentID,
                                                               @PathVariable("requestID") int requestID) {
        AbsenceRequestCompositeKey targetKey = new AbsenceRequestCompositeKey(moduleID, lessonID, studentID, requestID);
        AbsenceRequestDto absenceRequestDto = absenceRequestService.getAbsenceRequest(targetKey);
        return ResponseEntity.ok(absenceRequestDto);
    }

    @GetMapping("/by-moduleID/{moduleID}")
    public ResponseEntity<List<AbsenceRequestDto>> getAbsenceRequestByModuleID(@PathVariable("moduleID") String moduleID) {
        List<AbsenceRequestDto> absenceRequestDtos = absenceRequestService.getAbsenceRequestByModuleID(moduleID);
        return ResponseEntity.ok(absenceRequestDtos);
    }

    @GetMapping("/by-moduleID-and-lessonID/{moduleID}/{lessonID}")
    public ResponseEntity<List<AbsenceRequestDto>> getAbsenceRequestByModuleIDAndLessonID(@PathVariable("moduleID") String moduleID,
                                                                                          @PathVariable("lessonID") int lessonID) {
        List<AbsenceRequestDto> absenceRequestDtos = absenceRequestService.getAbsenceRequestByModuleIDAndLessonID(moduleID, lessonID);
        return ResponseEntity.ok(absenceRequestDtos);
    }

    @GetMapping("/by-moduleID-and-lessonID-and-studentID/{moduleID}/{lessonID}/{studentID}")
    public ResponseEntity<List<AbsenceRequestDto>> getAbsenceRequestByModuleIDAndLessonIDAndStudentID(@PathVariable("moduleID") String moduleID,
                                                                                                      @PathVariable("lessonID") int lessonID,
                                                                                                      @PathVariable("studentID") String studentID) {
        List<AbsenceRequestDto> absenceRequestDtos = absenceRequestService.getAbsenceRequestByModuleIDAndLessonIDAndStudentID(moduleID, lessonID, studentID);
        return ResponseEntity.ok(absenceRequestDtos);
    }

    @GetMapping("/by-studentID/{studentID}")
    public ResponseEntity<List<AbsenceRequestDto>> getAbsenceRequestByStudentID(@PathVariable("studentID") String studentID) {
        List<AbsenceRequestDto> absenceRequestDtos = absenceRequestService.getAbsenceRequestByStudentID(studentID);
        return ResponseEntity.ok(absenceRequestDtos);
    }

    @PutMapping("{moduleID}/{lessonID}/{studentID}/{requestID}")
    public ResponseEntity<AbsenceRequestDto> updateAbsenceRequest(@PathVariable("moduleID") String moduleID,
                                                                  @PathVariable("lessonID") int lessonID,
                                                                  @PathVariable("studentID") String studentID,
                                                                  @PathVariable("requestID") int requestID,
                                                                  @RequestBody AbsenceRequestDto absenceRequestDto) {
        AbsenceRequestCompositeKey targetKey = new AbsenceRequestCompositeKey(moduleID, lessonID, studentID, requestID);
        AbsenceRequestDto savedData = absenceRequestService.updateAbsenceRequest(targetKey, absenceRequestDto);
        return ResponseEntity.ok(savedData);
    }

    @DeleteMapping("{moduleID}/{lessonID}/{studentID}/{requestID}")
    public ResponseEntity<String> deleteAbsenceRequest(@PathVariable("moduleID") String moduleID,
                                                                  @PathVariable("lessonID") int lessonID,
                                                                  @PathVariable("studentID") String studentID,
                                                                  @PathVariable("requestID") int requestID){
        AbsenceRequestCompositeKey targetKey = new AbsenceRequestCompositeKey(moduleID, lessonID, studentID, requestID);
        absenceRequestService.deleteAbsenceRequest(targetKey);
        return ResponseEntity.ok("AbsenceRequest successfully deleted");
    }
}
