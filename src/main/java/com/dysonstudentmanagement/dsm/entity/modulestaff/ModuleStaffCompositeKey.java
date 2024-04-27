package com.dysonstudentmanagement.dsm.entity.modulestaff;

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
public class ModuleStaffCompositeKey implements Serializable {
    @Id
    @Column(name = "ModuleID", length = 12)
    private String moduleID;
    @Id
    @Column(name = "StaffID", length = 8)
    private String staffID;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ModuleStaffCompositeKey that = (ModuleStaffCompositeKey) o;
        return Objects.equals(getModuleID(), that.getModuleID()) && Objects.equals(getStaffID(), that.getStaffID());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getModuleID(), getStaffID());
    }
}
