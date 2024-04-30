package com.dysonstudentmanagement.dsm.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class TutorStudentMeetingDto {
    private String staffID;
    private String studentID;
    private LocalDateTime meetingTime;
    private String notes;
}
