package com.dysonstudentmanagement.dsm.mapper;

import com.dysonstudentmanagement.dsm.dto.programmeStaffDto;
import com.dysonstudentmanagement.dsm.entity.ProgrammeStaff.programmeStaff;


public class programmeStaffMapper {

    public static programmeStaffDto mapToProgrammeStaffDto(programmeStaff ProgrammeStaff){
        return new programmeStaffDto(
                ProgrammeStaff.getProgrammeID(),
                ProgrammeStaff.getStaffID()
        );
    }

    public static programmeStaff mapToProgrammeStaff(programmeStaffDto ProgrammeStaffDto){
        programmeStaff programmeStaff = com.dysonstudentmanagement.dsm.entity.ProgrammeStaff.programmeStaff.builder()
                .programmeID(ProgrammeStaffDto.getProgrammeID())
                .staffID(ProgrammeStaffDto.getStaffID())
                .build();
        return programmeStaff;
    }
}


//    CREATE TABLE `programmeStaff` (
//        `ProgrammeID` VARCHAR(12),
//        `StaffID` VARCHAR(8),
//        PRIMARY KEY(`ProgrammeID`,`StaffID`),
//        CONSTRAINT `ProgrammeStaff_FK_ProgrammeID` FOREIGN KEY (`ProgrammeID`) REFERENCES `programme`(`ProgrammeID`) ON UPDATE CASCADE ON DELETE CASCADE,
//        CONSTRAINT `ProgrammeStaff_FK_StaffID` FOREIGN KEY (`StaffID`) REFERENCES `UserPrimaryData`(`UserID`) ON UPDATE CASCADE ON DELETE CASCADE
//        );