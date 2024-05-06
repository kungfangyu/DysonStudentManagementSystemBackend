package com.dysonstudentmanagement.dsm.entity.studentprogrammeenrolment;

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
StudentProgrammeEnrolmentCompositeKey

Defines the StudentProgrammeEnrolment entity's composite key fields

Original Author: Imran Matloob 24/04/2024
 */
public class StudentProgrammeEnrolmentCompositeKey implements Serializable {

    @Id
    @Column(name = "StudentID", length = 8)
    private String studentID;

    @Id
    @Column(name = "ProgrammeID", length = 12)
    private String programmeID;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentProgrammeEnrolmentCompositeKey that = (StudentProgrammeEnrolmentCompositeKey) o;
        return Objects.equals(getStudentID(), that.getStudentID()) && Objects.equals(getProgrammeID(), that.getProgrammeID());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStudentID(), getProgrammeID());
    }
}

