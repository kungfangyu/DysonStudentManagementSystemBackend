package com.dysonstudentmanagement.dsm.entity.moduleannouncement;

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
public class ModuleAnnouncementCompositeKey implements Serializable {
    @Id
    @Column (name = "ModuleID", length = 12)
    private String moduleID;
    @Id
    @Column (name = "AnnouncementID")
    private int announcementID;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ModuleAnnouncementCompositeKey that = (ModuleAnnouncementCompositeKey) o;
        return getAnnouncementID() == that.getAnnouncementID() && Objects.equals(getModuleID(), that.getModuleID());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getModuleID(), getAnnouncementID());
    }
}
