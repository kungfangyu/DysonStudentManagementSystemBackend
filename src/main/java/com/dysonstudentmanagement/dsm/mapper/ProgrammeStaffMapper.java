package com.dysonstudentmanagement.dsm.mapper;

import com.dysonstudentmanagement.dsm.dto.ProgrammeStaffDto;
import com.dysonstudentmanagement.dsm.entity.programmestaff.ProgrammeStaff;

/*
    Class: ProgrammeStaffMapper
    Mapper class responsible for mapping between ProgrammeStaff and ProgrammeStaffDto objects.
    Original Author: Imran Matloob 24/04/2024
 */
public class ProgrammeStaffMapper {

    public static ProgrammeStaffDto mapToProgrammeStaffDto(ProgrammeStaff ProgrammeStaff){
        return new ProgrammeStaffDto(
                ProgrammeStaff.getProgrammeID(),
                ProgrammeStaff.getStaffID()
        );
    }

    public static ProgrammeStaff mapToProgrammeStaff(ProgrammeStaffDto ProgrammeStaffDto){
       ProgrammeStaff programmeStaff = ProgrammeStaff.builder()
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