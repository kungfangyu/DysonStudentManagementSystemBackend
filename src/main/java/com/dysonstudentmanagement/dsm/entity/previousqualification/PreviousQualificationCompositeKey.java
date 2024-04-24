package com.dysonstudentmanagement.dsm.entity.previousqualification;

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
public class PreviousQualificationCompositeKey implements Serializable {
    @Id
    @Column(name = "UserID", length = 8)
    private String userID;
    @Id
    @Column(name = "QualificationLevel", length = 50)
    private String qualificationLevel;
    @Id
    @Column(name = "Subject", length = 50)
    private String subject;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PreviousQualificationCompositeKey that = (PreviousQualificationCompositeKey) o;
        return Objects.equals(getUserID(), that.getUserID()) && Objects.equals(getQualificationLevel(), that.getQualificationLevel()) && Objects.equals(getSubject(), that.getSubject());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserID(), getQualificationLevel(), getSubject());
    }


}
