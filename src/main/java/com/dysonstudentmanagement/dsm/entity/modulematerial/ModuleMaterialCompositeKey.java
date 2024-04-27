package com.dysonstudentmanagement.dsm.entity.modulematerial;


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
public class ModuleMaterialCompositeKey implements Serializable {
    @Id
    @Column(name = "ModuleID", length = 12)
    private String moduleID;
    @Id
    @Column(name = "MaterialNumber")
    private int materialNumber;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ModuleMaterialCompositeKey that = (ModuleMaterialCompositeKey) o;
        return getMaterialNumber() == that.getMaterialNumber() && Objects.equals(getModuleID(), that.getModuleID());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getModuleID(), getMaterialNumber());
    }
}


