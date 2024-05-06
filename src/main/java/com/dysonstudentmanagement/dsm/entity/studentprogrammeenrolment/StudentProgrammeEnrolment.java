package com.dysonstudentmanagement.dsm.entity.studentprogrammeenrolment;

import com.dysonstudentmanagement.dsm.entity.programme.Programme;
import com.dysonstudentmanagement.dsm.entity.user.UserPrimaryData;
import com.dysonstudentmanagement.dsm.entitylistener.StudentProgrammeEnrolmentListener;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "StudentProgrammeEnrolment")
@EntityListeners(StudentProgrammeEnrolmentListener.class)
@IdClass(StudentProgrammeEnrolmentCompositeKey.class)
/*
StudentProgrammeEnrolment entity

Represents StudentProgrammeEnrolment table record in database

Original Author: Imran Matloob 24/04/2024
 */
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
    private LocalDate dateEnrolled;

    @Column(name = "DateCompleted")
    private LocalDate dateCompleted;

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
    private Programme programme;


}


