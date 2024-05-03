package com.dysonstudentmanagement.dsm.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@AllArgsConstructor
@ToString
/*
StudentExamGradeDto

DTO representing student exam grades.

Original Author: Yijie Zhu 27/04/2024
 */
public class StudentExamGradeDto {
    private String moduleID;

    private int examID;

    private String studentID;

    private float grade;
}
