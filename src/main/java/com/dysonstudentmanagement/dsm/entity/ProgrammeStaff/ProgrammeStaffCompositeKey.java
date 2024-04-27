package com.dysonstudentmanagement.dsm.entity.ProgrammeStaff;

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


//    CREATE TABLE `PreviousQualification` (
//        `UserID` VARCHAR(8),
//        `QualificationLevel` VARCHAR(50),
//        `Subject` VARCHAR(50),
//        `Grade` VARCHAR(20),
//        `DateAchieved` DATE,
//        `Institution` VARCHAR(100),
//        PRIMARY KEY (`UserID`,`QualificationLevel`,`Subject`),
//        CONSTRAINT `PreviousQualification_FK_StudentID` FOREIGN KEY (`UserID`) REFERENCES `UserPrimaryData`(`UserID`) ON UPDATE CASCADE ON DELETE CASCADE
//        );