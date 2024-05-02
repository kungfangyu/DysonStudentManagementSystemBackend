package com.dysonstudentmanagement.dsm.controller;

import com.dysonstudentmanagement.dsm.dto.LessonDto;
import com.dysonstudentmanagement.dsm.entity.lesson.LessonCompositeKey;
import com.dysonstudentmanagement.dsm.service.LessonService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/lesson")
/*
LessonController

Provides api mappings to access lessonService methods.

Original Author: Billy Peters 26/04/2024
 */
public class LessonController {

    private LessonService lessonService;

    @PostMapping
    public ResponseEntity<LessonDto> createLesson(@RequestBody LessonDto lessonDto){
        LessonDto savedLesson = lessonService.createLesson(lessonDto);
        return new ResponseEntity<>(savedLesson, HttpStatus.CREATED);
    }

    @GetMapping ("/{moduleID}/{lectureID}")
    public ResponseEntity<LessonDto> getLesson(@PathVariable("moduleID") String moduleID,@PathVariable("lectureID") int lectureID){
        LessonCompositeKey targetKey = new LessonCompositeKey(moduleID,lectureID);
        LessonDto targetLesson = lessonService.getLesson(targetKey);
        return ResponseEntity.ok(targetLesson);
    }

    @GetMapping("/by-moduleID/{moduleID}")
    public ResponseEntity<List<LessonDto>> getLessonByModuleID(@PathVariable("moduleID") String moduleID){
        List<LessonDto> moduleLessons = lessonService.getLessonByModule(moduleID);
        return ResponseEntity.ok(moduleLessons);
    }

    @PutMapping("/{moduleID}/{lectureID}")
    public ResponseEntity<LessonDto> updateLesson(@PathVariable("moduleID") String moduleID,@PathVariable("lectureID") int lectureID,@RequestBody LessonDto requestedUpdate){
        LessonCompositeKey targetKey = new LessonCompositeKey(moduleID,lectureID);
        LessonDto updatedLesson = lessonService.updateLesson(targetKey,requestedUpdate);
        return ResponseEntity.ok(updatedLesson);
    }

    @DeleteMapping("/{moduleID}/{lectureID}")
    public ResponseEntity<String> deleteLesson(@PathVariable("moduleID") String moduleID,@PathVariable("lectureID") int lectureID){
        LessonCompositeKey targetKey = new LessonCompositeKey(moduleID,lectureID);
        lessonService.deleteLesson(targetKey);
        return ResponseEntity.ok(moduleID+" lesson: " +lectureID+" successfully deleted");

    }


}
