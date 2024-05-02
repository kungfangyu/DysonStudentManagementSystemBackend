package com.dysonstudentmanagement.dsm.controller;

import com.dysonstudentmanagement.dsm.dto.PreviousQualificationDto;
import com.dysonstudentmanagement.dsm.entity.previousqualification.PreviousQualificationCompositeKey;
import com.dysonstudentmanagement.dsm.service.PreviousQualificationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/previousQualification")
/*
PreviousQualificationController

Provides api mappings to access previousQualificationService methods.

Original Author: Billy Peters 23/04/2024
 */
public class PreviousQualificationController {
    private PreviousQualificationService previousQualificationService;

    @PostMapping
    public ResponseEntity<PreviousQualificationDto> createPreviousQualification(@RequestBody PreviousQualificationDto previousQualificationDto) {
        PreviousQualificationDto savedQualification = previousQualificationService.createPreviousQualification(previousQualificationDto);
        return new ResponseEntity<>(savedQualification, HttpStatus.CREATED);
    }

    @GetMapping("{userID}")
    public ResponseEntity<List<PreviousQualificationDto>> getPreviousQualificationByUserId(@PathVariable("userID") String userID) {
        List<PreviousQualificationDto> qualificationDtos = previousQualificationService.getPreviousQualificationByUserID(userID);
        return ResponseEntity.ok(qualificationDtos);
    }

    @GetMapping("{userID}/{qualificationLevel}/{subject}")
    public ResponseEntity<PreviousQualificationDto> getPreviousQualification(@PathVariable("userID") String userID,
                                                                             @PathVariable("qualificationLevel") String qualificationLevel,
                                                                             @PathVariable("subject") String subject) {
        PreviousQualificationCompositeKey targetKey = new PreviousQualificationCompositeKey(userID, qualificationLevel, subject);
        PreviousQualificationDto qualificationDto = previousQualificationService.getPreviousQualification(targetKey);
        return ResponseEntity.ok(qualificationDto);
    }

    @PutMapping("{userID}/{qualificationLevel}/{subject}")
    public ResponseEntity<PreviousQualificationDto> updatePreviousQualification(@PathVariable("userID") String userID,
                                                                                @PathVariable("qualificationLevel") String qualificationLevel,
                                                                                @PathVariable("subject") String subject,
                                                                                @RequestBody PreviousQualificationDto updateDto) {
        PreviousQualificationCompositeKey targetKey = new PreviousQualificationCompositeKey(userID, qualificationLevel, subject);
        PreviousQualificationDto qualificationDto = previousQualificationService.updatePreviousQualification(targetKey, updateDto);
        return ResponseEntity.ok(qualificationDto);
    }

    @DeleteMapping("{userID}/{qualificationLevel}/{subject}")
    public ResponseEntity<String> deletePreviousQualification(@PathVariable("userID") String userID,
                                                              @PathVariable("qualificationLevel") String qualificationLevel,
                                                              @PathVariable("subject") String subject) {
        PreviousQualificationCompositeKey targetKey = new PreviousQualificationCompositeKey(userID, qualificationLevel, subject);
        previousQualificationService.deletePreviousQualification(targetKey);
        return ResponseEntity.ok(userID + " " + qualificationLevel + " " + subject + " : Deleted Succesfully");
    }
}