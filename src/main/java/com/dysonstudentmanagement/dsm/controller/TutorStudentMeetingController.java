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
        TutorStudentMeetingCompositeKey pk = new TutorStudentMeetingCompositeKey(staffID, studentID, meetingTime);
        return ResponseEntity.ok(tutorStudentMeetingService.getTutorStudentMeeting(pk));
    }

    @GetMapping("{staffID}/{studentID}")
    public ResponseEntity<List<TutorStudentMeetingDto>> getSTutorStudentMeetingByStaffIDandStudentID(@PathVariable("staffID") String staffID,
                                                                                                     @PathVariable("studentID") String studentID) {
        return ResponseEntity.ok(tutorStudentMeetingService.getTutorStudentMeetingByStudentIDandStaffID(studentID, staffID));
    }

    @PostMapping("{staffID}/{studentID}/{meetingTime}")
    public ResponseEntity<TutorStudentMeetingDto> updateStudentModuleGrade(@PathVariable("staffID") String staffID,
                                                                          @PathVariable("studentID") String studentID,
                                                                          @PathVariable("meetingTime") String meetingTime,
                                                                          @RequestBody TutorStudentMeetingDto tutorStudentMeetingDto) {
        TutorStudentMeetingCompositeKey pk = new TutorStudentMeetingCompositeKey(staffID, studentID, meetingTime);
        TutorStudentMeetingDto savedMeeting = tutorStudentMeetingService.updateTutorStudentMeeting(pk, tutorStudentMeetingDto);
        return ResponseEntity.ok(savedMeeting);
    }

    @DeleteMapping("{staffID}/{studentID}/{meetingTime}")
    public ResponseEntity<String> deleteTutorStudentMeeting(@PathVariable("staffID") String staffID,
                                                            @PathVariable("studentID") String studentID,
                                                            @PathVariable("meetingTime") String meetingTime) {
        TutorStudentMeetingCompositeKey pk = new TutorStudentMeetingCompositeKey(staffID, studentID, meetingTime);
        tutorStudentMeetingService.deleteTutorStudentMeeting(pk);
        return new ResponseEntity<>("Tutor student meeting deleted", HttpStatus.OK);
    }

}
