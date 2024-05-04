package com.dysonstudentmanagement.dsm.entity.programme;
import com.dysonstudentmanagement.dsm.idgenerator.ProgrammeIDGenerator;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "programme")
/*
Programme Entity

Spring representation of the database table "Programme"

Original Author: Imran Matloob 24/04/2024
 */
public class Programme {

    @Id
    @GeneratedValue(generator = "programmeIDGenerator")
    @GenericGenerator(name = "programmeIDGenerator", type = ProgrammeIDGenerator.class)
    @Column(name="ProgrammeID",length=12)
    private String programmeID;
    @Column(name = "Name",length=50)
    private String name;
    @Column(name = "StartDate")
    private LocalDate startDate;
    @Column(name = "EndDate")
    private LocalDate endDate;
    @Column(name="Description")
    private String description;
    @Column(name="TotalCredits")
    private int totalCredits;
    @Column(name="isGradesReleased")
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