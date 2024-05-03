package com.dysonstudentmanagement.dsm.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@ToString
/*
ProgrammeDto

Defines fields/data that will be transmitted to/from the API client when requesting/sending programme data.

Original Author: Imran Matloob 24/04/2024
 */
public class ProgrammeDto {
    private String programmeID;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private String description;
    private int totalCredits;
    private boolean isGradesReleased;
}


//    CREATE TABLE `programme` (
//        `ProgrammeID` VARCHAR(12),
//        `Name` VARCHAR(50),
//        `StartDate` Date,
//        `EndDate` Date,
//        `Description` TEXT,
//        `TotalCredits` INT,
//        `isGradesReleased` BOOL,
//        PRIMARY KEY(`ProgrammeID`)
//        );