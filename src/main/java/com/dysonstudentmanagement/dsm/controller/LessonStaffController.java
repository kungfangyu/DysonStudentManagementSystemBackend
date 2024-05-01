package com.dysonstudentmanagement.dsm.controller;

import com.dysonstudentmanagement.dsm.dto.LessonDto;
import com.dysonstudentmanagement.dsm.dto.LessonStaffDto;
import com.dysonstudentmanagement.dsm.entity.lessonstaff.LessonStaffCompositeKey;
import com.dysonstudentmanagement.dsm.service.LessonStaffService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/lessonStaff")
public class LessonStaffController {

    private LessonStaffService lessonStaffService;

    @PostMapping
    public ResponseEntity<LessonStaffDto> createLessonStaff(@RequestBody LessonStaffDto lessonStaffDto){
        LessonStaffDto savedData = lessonStaffService.createLessonStaff(lessonStaffDto);
        return new ResponseEntity<>(savedData, HttpStatus.CREATED);
    }

    @GetMapping("/by-moduleID/{moduleID}")
    public ResponseEntity<List<LessonStaffDto>> getLessonStaffByModuleID(@PathVariable("moduleID") String moduleID){
        List<LessonStaffDto> lessonStaffDtos = lessonStaffService.getLessonStaffByModuleID(moduleID);
        return ResponseEntity.ok(lessonStaffDtos);
    }

    @GetMapping("/by-staffID/{staffID}")
    public ResponseEntity<List<LessonStaffDto>> getLessonStaffByStaffID(@PathVariable("staffID") String staffID){
        List<LessonStaffDto> lessonStaffDtos = lessonStaffService.getLessonStaffByStaffID(staffID);
        return ResponseEntity.ok(lessonStaffDtos);
    }

    @GetMapping("/by-lecture/{moduleID}/{lectureID}")
    public ResponseEntity<List<LessonStaffDto>> getLessonStaffByModuleIDAndLectureID(@PathVariable("moduleID") String moduleID,@PathVariable("lectureID") int lectureID){
        List<LessonStaffDto> lessonStaffDtos = lessonStaffService.getLessonStaffByModuleIDAndLectureID(moduleID,lectureID);
        return ResponseEntity.ok(lessonStaffDtos);
    }

    @GetMapping("/lessonInfo-by-staffID/{staffID}")
    public ResponseEntity<List<LessonDto>> getLessonInfoByStaffID(@PathVariable("staffID") String staffID){
        List<LessonDto> lessonDtos = lessonStaffService.getLessonInfoByStaffID(staffID);
        return ResponseEntity.ok(lessonDtos);
    }

    @DeleteMapping("{moduleID}/{lessonID}/{staffID}")
    public ResponseEntity<String> deleteLessonStaff(@PathVariable("moduleID") String moduleID,
                                                    @PathVariable("lessonID") int lectureID,
                                                    @PathVariable("staffID") String staffID){
        LessonStaffCompositeKey targetKey = new LessonStaffCompositeKey(moduleID,lectureID,staffID);
        lessonStaffService.deleteLessonStaff(targetKey);
        return ResponseEntity.ok("Lesson Staff record deleted successfully");
    }
}
