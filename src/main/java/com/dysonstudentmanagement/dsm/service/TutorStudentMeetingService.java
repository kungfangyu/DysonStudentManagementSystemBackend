package com.dysonstudentmanagement.dsm.service;

import com.dysonstudentmanagement.dsm.dto.TutorStudentMeetingDto;
import com.dysonstudentmanagement.dsm.entity.tutorstudentmeeting.TutorStudentMeetingCompositeKey;

import java.util.List;

public interface TutorStudentMeetingService {
    TutorStudentMeetingDto createTutorStudentMeeting(TutorStudentMeetingDto tutorStudentMeetingDto);

    TutorStudentMeetingDto getTutorStudentMeeting(TutorStudentMeetingCompositeKey targetKey);

    List<TutorStudentMeetingDto> getTutorStudentMeetingByStudentIDandStaffID(String studentID, String staffID);

    TutorStudentMeetingDto updateTutorStudentMeeting(TutorStudentMeetingCompositeKey targetKey, TutorStudentMeetingDto tutorStudentMeetingDto);

    void deleteTutorStudentMeeting(TutorStudentMeetingCompositeKey targetKey);
}
