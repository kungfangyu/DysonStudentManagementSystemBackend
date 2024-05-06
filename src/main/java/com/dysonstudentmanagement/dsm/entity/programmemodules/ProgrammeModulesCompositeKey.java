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
/*
ProgrammeModulesCompositeKey

Defines the ProgrammeModules entity's composite key fields

Original Author: Imran Matloob 24/04/2024
 */
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

