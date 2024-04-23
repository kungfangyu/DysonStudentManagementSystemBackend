package com.dysonstudentmanagement.dsm.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class PreviousQualificationDto {
    private String userID;
    private String qualificationLevel;
    private String subject;
    private String grade;
    private LocalDate dateAchieved;
    private String institution;
}
