package com.dysonstudentmanagement.dsm.entity.studentmodulegrade;

import com.dysonstudentmanagement.dsm.entity.moduledetails.ModuleDetails;
import com.dysonstudentmanagement.dsm.entity.user.UserPrimaryData;
import com.dysonstudentmanagement.dsm.entitylistener.StudentModuleGradeListener;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "StudentModuleGrade")
@EntityListeners(StudentModuleGradeListener.class)
@IdClass(StudentModuleGradeCompositeKey.class)
/*
StudentModuleGrade entity

Represents StudentModuleGrade table record in database

Original Author: Grace Liu 25/04/2024
 */
public class StudentModuleGrade {
    @Id
    @Column(name = "StudentID", length = 8)
    private String studentID;
    @Id
    @Column(name = "ModuleID", length = 12)
    private String moduleID;

    @Column(name = "Grade")
    private float grade;
    @Column(name = "PercentageAttendance")
    private float percentageAttendance;

    @Enumerated(EnumType.STRING)
    @Column(name = "Status")
    private Status status;

    public enum Status {
        enrolled, suspended, withdrawn
    }

    @MapsId
    @ManyToOne
    @JoinColumn(name = "StudentID", referencedColumnName = "UserID")
    private UserPrimaryData userPrimaryData;

    @MapsId
    @ManyToOne
    @JoinColumn(name = "ModuleID", referencedColumnName = "ModuleID")
    private ModuleDetails moduleDetails;
}
