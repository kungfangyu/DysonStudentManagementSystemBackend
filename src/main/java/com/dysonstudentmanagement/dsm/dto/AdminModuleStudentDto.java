package com.dysonstudentmanagement.dsm.dto;
/*
* A data structure send to admin module student list page
* Original Author: Tianpu Li 04/05/2024
* */
import com.dysonstudentmanagement.dsm.entity.studentmodulegrade.StudentModuleGrade;
import lombok.*;

@Data
@Getter
@Setter
@ToString
public class AdminModuleStudentDto {
    private String studentId;
    private String name;
    private String email;
    private float grade;
    private StudentModuleGrade.Status status;
    private String tutor;
}
