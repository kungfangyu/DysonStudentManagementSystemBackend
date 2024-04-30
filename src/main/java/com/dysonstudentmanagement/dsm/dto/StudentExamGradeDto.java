package com.dysonstudentmanagement.dsm.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * DTO representing student exam grades.
 */

@Getter
@Setter
@AllArgsConstructor
@ToString
public class StudentExamGradeDto {
    private String moduleID;

    private int examID;

    private String studentID;

    private float grade;
}
