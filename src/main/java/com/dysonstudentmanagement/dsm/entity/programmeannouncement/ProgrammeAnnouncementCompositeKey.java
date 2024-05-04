package com.dysonstudentmanagement.dsm.entity.programmeannouncement;

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
ProgrammeAnnouncementCompositeKey

Defines the ProgrammeAnnouncement entity's composite key fields

Original Author: Tianpu Li 25/04/2024
 */
public class ProgrammeAnnouncementCompositeKey implements Serializable {
    @Id
    @Column(name = "ProgrammeID", length = 12)
    private String programmeID;
    @Id
    @Column(name = "AnnouncementID")
    private int announcementID;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProgrammeAnnouncementCompositeKey that = (ProgrammeAnnouncementCompositeKey) o;
        return getAnnouncementID() == that.getAnnouncementID() && Objects.equals(getProgrammeID(), that.getProgrammeID());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProgrammeID(), getAnnouncementID());
    }
}
