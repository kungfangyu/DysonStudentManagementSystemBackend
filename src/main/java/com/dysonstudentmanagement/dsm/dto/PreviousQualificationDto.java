package com.dysonstudentmanagement.dsm.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
/*
PreviousQualificationDto

Defines fields/data that will be transmitted to/from the API client when requesting/sending PreviousQualification data.

Original Author: Billy Peters 23/04/2024
 */
public class PreviousQualificationDto {
    private String userID;
    private String qualificationLevel;
    private String subject;
    private String grade;
    private LocalDate dateAchieved;
    private String institution;
}
