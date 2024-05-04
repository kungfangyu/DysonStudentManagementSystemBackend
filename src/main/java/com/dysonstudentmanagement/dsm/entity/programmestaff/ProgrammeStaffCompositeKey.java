package com.dysonstudentmanagement.dsm.entity.programmestaff;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
/*
ProgrammeStaffCompositeKey

Defines the ProgrammeStaff entity's composite key fields

Original Author: Imran Matloob 24/04/2024
 */
public class ProgrammeStaffCompositeKey implements Serializable {
    @Id
    @Column(name = "ProgrammeID", length = 12)
    private String programmeID;
    @Id
    @Column(name = "StaffID", length = 8)
    private String staffID;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProgrammeStaffCompositeKey that = (ProgrammeStaffCompositeKey) o;
        return getProgrammeID().equals(that.getProgrammeID()) && getStaffID().equals(that.getStaffID());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProgrammeID(), getStaffID());
    }
}


//    CREATE TABLE `programmeStaff` (
//        `ProgrammeID` VARCHAR(12),
//        `StaffID` VARCHAR(8),
//        PRIMARY KEY(`ProgrammeID`,`StaffID`),
//        CONSTRAINT `ProgrammeStaff_FK_ProgrammeID` FOREIGN KEY (`ProgrammeID`) REFERENCES `programme`(`ProgrammeID`) ON UPDATE CASCADE ON DELETE CASCADE,
//        CONSTRAINT `ProgrammeStaff_FK_StaffID` FOREIGN KEY (`StaffID`) REFERENCES `UserPrimaryData`(`UserID`) ON UPDATE CASCADE ON DELETE CASCADE
//        );