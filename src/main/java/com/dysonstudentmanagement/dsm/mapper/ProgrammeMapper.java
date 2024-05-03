package com.dysonstudentmanagement.dsm.mapper;

import com.dysonstudentmanagement.dsm.dto.ProgrammeDto;
import com.dysonstudentmanagement.dsm.entity.programme.Programme;

/*
    Class: ProgrammeMapper
    Mapper class responsible for mapping between Programme and ProgrammeDto objects.
    Original Author: Imran Matloob 24/04/2024
 */
public class ProgrammeMapper {
    public static ProgrammeDto mapToProgrammesDto(Programme programmeDetails) {
        return new ProgrammeDto(
                programmeDetails.getProgrammeID(),
                programmeDetails.getName(),
                programmeDetails.getStartDate(),
                programmeDetails.getEndDate(),
                programmeDetails.getDescription(),
                programmeDetails.getTotalCredits(),
                programmeDetails.isGradesReleased()
        );
    }

    public static Programme mapToProgramme(ProgrammeDto ProgrammeDto) {
        Programme programme = Programme.builder()
                .programmeID(ProgrammeDto.getProgrammeID())
                .name(ProgrammeDto.getName())
                .startDate(ProgrammeDto.getStartDate())
                .endDate(ProgrammeDto.getEndDate())
                .description(ProgrammeDto.getDescription())
                .totalCredits(ProgrammeDto.getTotalCredits())
                .isGradesReleased(ProgrammeDto.isGradesReleased())
                .build();
        return programme;


    }

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
