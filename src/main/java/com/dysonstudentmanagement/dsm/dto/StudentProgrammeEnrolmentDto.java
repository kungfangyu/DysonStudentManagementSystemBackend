package com.dysonstudentmanagement.dsm.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.dysonstudentmanagement.dsm.entity.studentprogrammeenrolment.StudentProgrammeEnrolment.*;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@ToString
/*
StudentProgrammeEnrolmentDto

Defines fields/data that will be transmitted to/from the API client when requesting/sending StudentProgrammeEnrolment data.

Original Author: Imran Matloob 24/04/2024
 */
public class StudentProgrammeEnrolmentDto {
    private String studentID;
    private String programmeID;
    private LocalDate dateEnrolled;
    private LocalDate dateCompleted;
    private float finalGrade;
    private Status status;
}
