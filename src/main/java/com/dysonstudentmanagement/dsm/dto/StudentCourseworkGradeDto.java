package com.dysonstudentmanagement.dsm.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
/*
StudentCourseworkGradeDto

Defines fields/data that will be transmitted to/from the API client when requesting/sending StudentCourseworkGrade data.

Original Author: Jack Burnett 27/04/2024
 */
public class StudentCourseworkGradeDto {
    private String moduleID;
    private int courseworkID;
    private String studentID;
    private String submissionFilePath;
    private LocalDateTime submissionTime;
    private boolean isOnTime;
    private Double grade;
    private String feedback;
}
