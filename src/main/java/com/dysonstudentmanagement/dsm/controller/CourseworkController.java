package com.dysonstudentmanagement.dsm.controller;

import com.dysonstudentmanagement.dsm.dto.CourseworkDto;
import com.dysonstudentmanagement.dsm.dto.ModuleAnnouncementDto;
import com.dysonstudentmanagement.dsm.entity.coursework.CourseworkCompositeKey;
import com.dysonstudentmanagement.dsm.entity.moduleannouncement.ModuleAnnouncementCompositeKey;
import com.dysonstudentmanagement.dsm.service.CourseworkService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/Coursework")
/*
CourseworkController

Provides api mappings to access courseworkService methods.

Original Author: Jack Burnett 26/04/2024
 */
public class CourseworkController {
    private CourseworkService courseworkService;

    @PostMapping
    public ResponseEntity<CourseworkDto> createCoursework(@RequestBody CourseworkDto courseworkDto) {
        CourseworkDto createdCoursework = courseworkService.createCoursework(courseworkDto);
        return new ResponseEntity<>(createdCoursework, HttpStatus.CREATED);
    }

    @GetMapping("{moduleID}")
    public ResponseEntity<List<CourseworkDto>> getAllCourseworkByModuleId(@PathVariable("moduleID") String moduleID) {
        return ResponseEntity.ok(courseworkService.getCourseworkByModuleID(moduleID));
    }

    @GetMapping("{moduleID}/{courseworkID}")
    public ResponseEntity<CourseworkDto> getIndividualCoursework(@PathVariable("moduleID") String moduleID,
                                                                                 @PathVariable("courseworkID") int courseworkID){
        CourseworkCompositeKey newCourseworkCompositeKey = new CourseworkCompositeKey(moduleID, courseworkID);
        CourseworkDto courseworkDto = courseworkService.getCoursework(newCourseworkCompositeKey);
        return ResponseEntity.ok(courseworkDto);
    }

    @PutMapping("{moduleID}/{courseworkID}")
    public ResponseEntity<CourseworkDto> updateCoursework(@PathVariable("moduleID") String moduleID,
                                                                          @PathVariable("courseworkID") int courseworkID,
                                                                          @RequestBody CourseworkDto updateDto) {
        CourseworkCompositeKey newCourseworkCompositeKey = new CourseworkCompositeKey(moduleID, courseworkID);
        CourseworkDto courseworkDto = courseworkService.updateCoursework(newCourseworkCompositeKey, updateDto);
        return ResponseEntity.ok(courseworkDto);
    }

    @DeleteMapping("{moduleID}/{courseworkID}")
    public ResponseEntity<String> deleteCoursework(@PathVariable("moduleID") String moduleID,
                                                              @PathVariable("courseworkID") int courseworkID) {
        CourseworkCompositeKey newCourseworkCompositeKey = new CourseworkCompositeKey(moduleID, courseworkID);
        courseworkService.deleteCoursework(newCourseworkCompositeKey);
        return ResponseEntity.ok(moduleID + " " + courseworkID + ": Deleted Successfully");
    }
}
