package com.dysonstudentmanagement.dsm.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * DTO (Data Transfer Object) class representing exam-related data.
 */
@Getter   // Generating getter methods for all fields
@Setter   // Generating setter methods for all fields
@AllArgsConstructor   // Generating a constructor with all fields
@ToString  // Generating a toString method for all fields
public class ExamDto {
    private int examID;  // Unique identifier for the exam
    private String moduleID;  // Identifier for the module associated with the exam
    private LocalDateTime startTime;  // Start time of the exam
    private LocalDateTime endTime;    // End time of the exam
    private float percentageOfModule;  // Percentage of module covered by the exam
}

//CREATE TABLE `Exam` (
//`ModuleID` VARCHAR(12),
//`ExamID` INT,
//`StartTime` DATETIME,
//`EndTime` DATETIME,
//`PercentageOfModule` FLOAT,
//PRIMARY KEY (`ModuleID`,`ExamID`),
//CONSTRAINT `Exam_FK_ModuleID` FOREIGN KEY (`ModuleID`) REFERENCES `ModuleDetails`(`ModuleID`) ON UPDATE CASCADE ON DELETE CASCADE
//);

