package com.dysonstudentmanagement.dsm.entity.exam;

import com.dysonstudentmanagement.dsm.entity.lesson.LessonCompositeKey;
import com.dysonstudentmanagement.dsm.entity.moduledetails.ModuleDetails;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Entity class representing an exam.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@ToString
@Table(name = "Exam")
@IdClass(ExamCompositeKey.class)
/*
Exam Entity

Spring representation of the database table "Exam"

Original Author: Yijie Zhu 27/04/2024
Modifying Author: Imran Matloob 28/04/2024 Implemented foreign key/referencing for moduleID
 */
public class Exam {
    @Id
    @Column(name="ModuleID",length=12)
    private String moduleID; // Identifier for the module associated with the exam
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ExamID")
    private int examID;  // Unique identifier for the exam

    @Column(name = "StartTime")
    private LocalDateTime startTime;  // Start time of the exam

    @Column(name = "EndTime")
    private LocalDateTime endTime;    // End time of the exam

    @Column(name = "PercentageOfModule")
    private float percentageOfModule;  // Percentage of module covered by the exam



    @MapsId
    @ManyToOne
    @JoinColumn(name = "ModuleID", referencedColumnName = "ModuleID")
    private ModuleDetails moduleDetails;



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