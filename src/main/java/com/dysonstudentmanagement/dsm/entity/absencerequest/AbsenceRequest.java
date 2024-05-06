package com.dysonstudentmanagement.dsm.entity.absencerequest;

import com.dysonstudentmanagement.dsm.entity.studentlessonallocation.StudentLessonAllocation;
import com.dysonstudentmanagement.dsm.entitylistener.AbsenceRequestListener;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@ToString
@Table(name = "AbsenceRequest")
@EntityListeners(AbsenceRequestListener.class)
@IdClass(AbsenceRequestCompositeKey.class)
/*
AbsenceRequest Entity

Spring representation of the database table "AbsenceRequest"

Original Author: Billy Peters 04/05/2024
 */
public class AbsenceRequest {
    @Id
    @Column(name = "ModuleID", length = 12)
    private String moduleID;
    @Id
    @Column(name = "LessonID")
    private int lessonID;
    @Id
    @Column(name = "StudentID", length = 8)
    private String studentID;
    @Id
    @Column(name = "RequestID")
    private int requestID;
    @Column(name = "RequestReason")
    private String requestReason;
    @Enumerated(EnumType.STRING)
    @Column(name = "RequestStatus")
    private RequestStatus requestStatus;

    @MapsId
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "ModuleID", referencedColumnName = "ModuleID"),
            @JoinColumn(name = "LessonID", referencedColumnName = "LessonID"),
            @JoinColumn(name = "StudentID", referencedColumnName = "StudentID")
    })
    private StudentLessonAllocation studentLessonAllocation;


    public enum RequestStatus{
        submitted,
        accepted,
        rejected
    }
}


//CREATE TABLE `AbsenceRequest` (
//`ModuleID` VARCHAR(12),
//`LessonID` INT,
//`StudentID`  VARCHAR(8),
//`RequestID` INT,
//`RequestReason` TEXT,
//`RequestStatus` ENUM('submitted', 'accepted','rejected'),
//PRIMARY KEY (`ModuleID`,`LessonID`,`StudentID`,`RequestID`),
//CONSTRAINT `AbsenceRequest_FK_StudentLessonAllocation` FOREIGN KEY (`ModuleID`,`LessonID`,`StudentID`) REFERENCES `StudentLessonAllocation`(`ModuleID`,`LessonID`,`StudentID`) ON UPDATE CASCADE ON DELETE CASCADE
//);