package com.dysonstudentmanagement.dsm.entity.modulematerilafile;

import jakarta.persistence.*;
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
ModuleMaterialFileCompositeKey

Defines the ModuleMaterialFile entity's composite key fields

Original Author: Grace Liu 25/04/2024
 */
public class ModuleMaterialFileCompositeKey implements Serializable {
    @Id
    @Column(name = "ModuleID", length = 12)
    private String moduleID;
    @Id
    @Column(name = "MaterialNumber")
    private int materialNumber;
    @Id
    @Column(name = "FileNumber")
    private int fileNumber;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ModuleMaterialFileCompositeKey that = (ModuleMaterialFileCompositeKey) o;
        return getMaterialNumber() == that.getMaterialNumber() && getFileNumber() == that.getFileNumber() && Objects.equals(getModuleID(), that.getModuleID());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getModuleID(), getMaterialNumber(), getFileNumber());
    }
}
