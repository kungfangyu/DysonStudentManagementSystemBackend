package com.dysonstudentmanagement.dsm.entity.StudentProgrammeEnrolment;

import com.dysonstudentmanagement.dsm.entity.ProgrammModules.programmeModulesCompositeKey;
import com.dysonstudentmanagement.dsm.entity.Programme.programme;
import com.dysonstudentmanagement.dsm.entity.user.UserPrimaryData;
import com.dysonstudentmanagement.dsm.entity.Programme.programme.*;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "StudentProgrammeEnrolment")
@IdClass(StudentProgrammeEnrolmentCompositeKey.class)
public class StudentProgrammeEnrolment {

    @Id
    @Column(name = "StudentID", length = 8)
    private String studentID;

    @Id
    @Column(name = "ProgrammeID", length = 12)
    private String programmeID;

    @Enumerated(EnumType.STRING)
    @Column(name = "Status")
    private Status status;

    @Column(name = "DateEnrolled")
    private Date dateEnrolled;

    @Column(name = "DateCompleted")
    private Date dateCompleted;

    @Column(name = "FinalGrade")
    private float finalGrade;

    public enum Status {
        enrolled, suspended, withdrawn, completed
    }


    @MapsId
    @ManyToOne
    @JoinColumn(name = "StudentID", referencedColumnName = "UserID")
    private UserPrimaryData studentPrimaryData;
    @MapsId
    @ManyToOne
    @JoinColumn(name = "ProgrammeID", referencedColumnName = "ProgrammeID")
    private programme programme;


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