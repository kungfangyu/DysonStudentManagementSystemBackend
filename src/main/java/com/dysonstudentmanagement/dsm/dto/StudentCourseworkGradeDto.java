package com.dysonstudentmanagement.dsm.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
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
