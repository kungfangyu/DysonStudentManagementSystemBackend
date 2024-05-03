package com.dysonstudentmanagement.dsm.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
/*
StudentModuleGradeDto

Defines fields/data that will be transmitted to/from the API client when requesting/sending StudentModuleGrade data.

Original Author: Grace Liu 25/04/2024
 */
public class StudentModuleGradeDto {
    private String studentID;
    private String moduleID;
    private float grade;
    private float percentageAttendance;
}
