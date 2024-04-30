package com.dysonstudentmanagement.dsm.mapper;

import com.dysonstudentmanagement.dsm.dto.TutorStudentMeetingDto;
import com.dysonstudentmanagement.dsm.entity.tutorstudentmeeting.TutorStudentMeeting;

public class TutorStudentMeetingMapper {
    public static TutorStudentMeetingDto mapToTutorStudentMeetingDto(TutorStudentMeeting tutorStudentMeeting) {
        return new TutorStudentMeetingDto(
                tutorStudentMeeting.getStaffID(),
                tutorStudentMeeting.getStudentID(),
                tutorStudentMeeting.getMeetingTime(),
                tutorStudentMeeting.getNotes()
        );
    }

    public static TutorStudentMeeting mapToTutorStudentMeeting(TutorStudentMeetingDto tutorStudentMeetingDto) {
        return TutorStudentMeeting.builder()
                .staffID(tutorStudentMeetingDto.getStaffID())
                .studentID(tutorStudentMeetingDto.getStudentID())
                .meetingTime(tutorStudentMeetingDto.getMeetingTime())
                .notes(tutorStudentMeetingDto.getNotes())
                .build();
    }
}
