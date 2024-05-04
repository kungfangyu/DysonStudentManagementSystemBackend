package com.dysonstudentmanagement.dsm.dto;

import com.dysonstudentmanagement.dsm.entity.studentmodulegrade.StudentModuleGrade;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StudentModuleGradeDto {
    private String studentID;
    private String moduleID;
    private float grade;
    private float percentageAttendance;
    private StudentModuleGrade.Status status;
}
