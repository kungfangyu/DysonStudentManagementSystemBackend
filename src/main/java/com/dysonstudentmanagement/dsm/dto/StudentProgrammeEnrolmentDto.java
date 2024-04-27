package com.dysonstudentmanagement.dsm.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.dysonstudentmanagement.dsm.entity.StudentProgrammeEnrolment.StudentProgrammeEnrolment.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class StudentProgrammeEnrolmentDto {

    private String studentID;

    private String programmeID;

    private Date dateEnrolled;
    private Date dateCompleted;

    private float finalGrade;

    private Status status;
}
