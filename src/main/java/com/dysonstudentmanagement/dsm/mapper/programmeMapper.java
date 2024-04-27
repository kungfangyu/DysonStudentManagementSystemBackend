package com.dysonstudentmanagement.dsm.mapper;

import com.dysonstudentmanagement.dsm.dto.programmeDto;
import com.dysonstudentmanagement.dsm.entity.Programme.programme;

public class programmeMapper {
    public static programmeDto mapToProgrammesDto(programme programmeDetails) {
        return new programmeDto(
                programmeDetails.getProgrammeID(),
                programmeDetails.getName(),
                programmeDetails.getStartDate(),
                programmeDetails.getEndDate(),
                programmeDetails.getDescription(),
                programmeDetails.getTotalCredits(),
                programmeDetails.isGradesReleased()
        );
    }

    public static programme mapToProgramme(programmeDto ProgrammeDto) {
        programme programme = com.dysonstudentmanagement.dsm.entity.Programme.programme.builder()
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
