package com.dysonstudentmanagement.dsm.entity.StudentProgrammeEnrolment;

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


//CREATE TABLE `StudentProgrammeEnrolment` (
//`StudentID` VARCHAR(8),
//`ProgrammeID` VARCHAR(12),
//`Status` ENUM('enrolled','suspended','withdrawn','completed'),
//`DateEnrolled` DATE,
//`DateCompleted` DATE,
//`FinalGrade` FLOAT,


//PRIMARY KEY (`StudentID`,`ProgrammeID`),
//CONSTRAINT `StudentProgrammeEnrolment_FK_StudentID` FOREIGN KEY (`StudentID`) REFERENCES `UserPrimaryData`(`UserID`) ON UPDATE CASCADE ON DELETE CASCADE,
//CONSTRAINT `StudentProgrammeEnrolment_FK_ProgrammeID` FOREIGN KEY (`ProgrammeID`) REFERENCES `Programme`(`ProgrammeID`) ON UPDATE CASCADE ON DELETE RESTRICT
//);