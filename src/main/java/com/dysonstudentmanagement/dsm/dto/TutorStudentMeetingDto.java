package com.dysonstudentmanagement.dsm.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
/*
TutorStudentMeetingDto

Defines fields/data that will be transmitted to/from the API client when requesting/sending TutorStudentMeeting data.

Original Author: Jack Burnett 30/04/2024
 */
public class TutorStudentMeetingDto {
    private String staffID;
    private String studentID;
    private LocalDateTime meetingTime;
    private String notes;
}
