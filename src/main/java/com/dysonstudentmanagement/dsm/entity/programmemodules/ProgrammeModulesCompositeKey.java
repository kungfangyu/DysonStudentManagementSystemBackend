package com.dysonstudentmanagement.dsm.entity.programmemodules;

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
public class ProgrammeModulesCompositeKey implements Serializable {

    @Id
    @Column(name = "ModuleID", length = 12)
    private String moduleID;

    @Id
    @Column(name = "ProgrammeID", length = 12)
    private String programmeID;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProgrammeModulesCompositeKey that = (ProgrammeModulesCompositeKey) o;
        return getModuleID().equals(that.getModuleID()) && getProgrammeID().equals(that.getProgrammeID());
    }

    @Override
    public int hashCode() {
        return Objects.hash(moduleID, programmeID);
    }
}


//CREATE TABLE `ProgrammeModules` (
//`ModuleID` VARCHAR(12),
//`ProgrammeID` VARCHAR(12),
//PRIMARY KEY (`ModuleID`,`ProgrammeID`),
//CONSTRAINT `ProgrammeModules_FK_ModuleID` FOREIGN KEY (`ModuleID`) REFERENCES `ModuleDetails`(`ModuleID`) ON UPDATE CASCADE ON DELETE RESTRICT,
//CONSTRAINT `ProgrammeModules_FK_ProgrammeID` FOREIGN KEY (`ProgrammeID`) REFERENCES `Programme`(`ProgrammeID`) ON UPDATE CASCADE ON DELETE CASCADE
//);