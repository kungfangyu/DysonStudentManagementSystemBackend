package com.dysonstudentmanagement.dsm.service;

import com.dysonstudentmanagement.dsm.dto.TutorStudentMeetingDto;
import com.dysonstudentmanagement.dsm.entity.tutorstudentmeeting.TutorStudentMeetingCompositeKey;

import java.util.List;

/*
TutorStudentMeetingService

Interface that declares service methods that act upon TutorStudentMeeting table in database

Original Author: Jack Burnett 30/04/2024
 */
public interface TutorStudentMeetingService {
    TutorStudentMeetingDto createTutorStudentMeeting(TutorStudentMeetingDto tutorStudentMeetingDto);

    TutorStudentMeetingDto getTutorStudentMeeting(TutorStudentMeetingCompositeKey targetKey);

    List<TutorStudentMeetingDto> getTutorStudentMeetingByStaffID(String staffID);

    TutorStudentMeetingDto updateTutorStudentMeeting(TutorStudentMeetingCompositeKey targetKey, TutorStudentMeetingDto tutorStudentMeetingDto);

    void deleteTutorStudentMeeting(TutorStudentMeetingCompositeKey targetKey);
}
