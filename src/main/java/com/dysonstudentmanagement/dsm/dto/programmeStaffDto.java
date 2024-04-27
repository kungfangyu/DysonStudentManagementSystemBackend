package com.dysonstudentmanagement.dsm.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class programmeStaffDto {
    private String programmeID;
    private String staffID;
}


//    CREATE TABLE `programmeStaff` (
//        `ProgrammeID` VARCHAR(12),
//        `StaffID` VARCHAR(8),
//        PRIMARY KEY(`ProgrammeID`,`StaffID`),
//        CONSTRAINT `ProgrammeStaff_FK_ProgrammeID` FOREIGN KEY (`ProgrammeID`) REFERENCES `programme`(`ProgrammeID`) ON UPDATE CASCADE ON DELETE CASCADE,
//        CONSTRAINT `ProgrammeStaff_FK_StaffID` FOREIGN KEY (`StaffID`) REFERENCES `UserPrimaryData`(`UserID`) ON UPDATE CASCADE ON DELETE CASCADE
//        );