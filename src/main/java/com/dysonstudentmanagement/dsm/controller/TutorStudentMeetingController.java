package com.dysonstudentmanagement.dsm.controller;

import com.dysonstudentmanagement.dsm.dto.TutorStudentMeetingDto;
import com.dysonstudentmanagement.dsm.entity.tutorstudentmeeting.TutorStudentMeetingCompositeKey;
import com.dysonstudentmanagement.dsm.service.TutorStudentMeetingService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/tutorStudentMeeting")
/*
TutorStudentMeetingController

Provides api mappings to access tutorStudentMeetingService methods.

Original Author: Jack Burnett 30/04/2024
Modifying Author: Billy Peters 04/05/2024 Removed StudentID from composite primary key
 */
public class TutorStudentMeetingController {
    private TutorStudentMeetingService tutorStudentMeetingService;

    @PostMapping
    public ResponseEntity<TutorStudentMeetingDto> createTutorStudentMeeting(@RequestBody TutorStudentMeetingDto tutorStudentMeetingDto) {
        TutorStudentMeetingDto savedMeeting = tutorStudentMeetingService.createTutorStudentMeeting(tutorStudentMeetingDto);
        return new ResponseEntity<>(savedMeeting, HttpStatus.CREATED);
    }

    @GetMapping("{staffID}/{studentID}/{meetingTime}")
    public ResponseEntity<TutorStudentMeetingDto> getStudentModuleGrade(@PathVariable("staffID") String staffID,
                                                                       @PathVariable("studentID") String studentID,
                                                                       @PathVariable("meetingTime") String meetingTime) {
        TutorStudentMeetingCompositeKey pk = new TutorStudentMeetingCompositeKey(staffID, meetingTime);
        return ResponseEntity.ok(tutorStudentMeetingService.getTutorStudentMeeting(pk));
    }

    @GetMapping("{staffID}")
    public ResponseEntity<List<TutorStudentMeetingDto>> getTutorStudentMeetingByStaffID(@PathVariable("staffID") String staffID) {
        return ResponseEntity.ok(tutorStudentMeetingService.getTutorStudentMeetingByStaffID(staffID));
    }
    @GetMapping("/by-studentID/{studentID}")
    public ResponseEntity<List<TutorStudentMeetingDto>> getTutorStudentMeetingByStudentID(@PathVariable("studentID") String studentID) {
        return ResponseEntity.ok(tutorStudentMeetingService.getTutorStudentMeetingByStudentID(studentID));
    }




    @PutMapping("{staffID}/{meetingTime}")
    public ResponseEntity<TutorStudentMeetingDto> updateTutorStudentMeeting(@PathVariable("staffID") String staffID,
                                                                          @PathVariable("meetingTime") String meetingTime,
                                                                          @RequestBody TutorStudentMeetingDto tutorStudentMeetingDto) {
        TutorStudentMeetingCompositeKey pk = new TutorStudentMeetingCompositeKey(staffID, meetingTime);
        TutorStudentMeetingDto savedMeeting = tutorStudentMeetingService.updateTutorStudentMeeting(pk, tutorStudentMeetingDto);
        return ResponseEntity.ok(savedMeeting);
    }

    @DeleteMapping("{staffID}/{studentID}/{meetingTime}")
    public ResponseEntity<String> deleteTutorStudentMeeting(@PathVariable("staffID") String staffID,
                                                            @PathVariable("studentID") String studentID,
                                                            @PathVariable("meetingTime") String meetingTime) {
        TutorStudentMeetingCompositeKey pk = new TutorStudentMeetingCompositeKey(staffID,meetingTime);
        tutorStudentMeetingService.deleteTutorStudentMeeting(pk);
        return new ResponseEntity<>("Tutor student meeting deleted", HttpStatus.OK);
    }

}
