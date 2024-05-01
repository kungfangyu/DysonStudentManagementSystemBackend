package com.dysonstudentmanagement.dsm.entity.studentexamgrade;

import com.dysonstudentmanagement.dsm.entity.exam.Exam;
import com.dysonstudentmanagement.dsm.entity.moduledetails.ModuleDetails;
import com.dysonstudentmanagement.dsm.entity.user.UserPrimaryData;
import com.dysonstudentmanagement.dsm.entitylistener.StudentExamGradeListener;
import jakarta.persistence.*;
import lombok.*;

/**
 * Entity class representing student exam grades.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@ToString
@Table(name = "StudentExamGrade")
@EntityListeners(StudentExamGradeListener.class)
@IdClass(StudentExamGradeCompositeKey.class)
public class StudentExamGrade {

    @Id
    @Column(name="ModuleID",length=12)
    private String moduleID;


    @Id
    @Column(name = "ExamID")
    private int examID;  // Unique identifier for the exam



    @Id
    @Column(name = "StudentID", length = 8)
    private String studentID;  // Unique identifier for the user


    @Column(name = "Grade")
    private float grade;  // Grade achieved by the student in the exam


    @MapsId
    @ManyToOne
    @JoinColumns({
            @JoinColumn( name = "ModuleID", referencedColumnName = "ModuleID"),
            @JoinColumn(name = "ExamID", referencedColumnName = "ExamID")
    })
    private Exam exam;


    @MapsId
    @ManyToOne
    @JoinColumn(name = "StudentID", referencedColumnName = "UserID")
    private UserPrimaryData userPrimaryData;

}

//CREATE TABLE `StudentExamGrade` (
//`ModuleID` VARCHAR(12),
//`ExamID` INT,
//`StudentID` VARCHAR(8),
//`Grade` FLOAT,
//PRIMARY KEY (`ModuleID`,`ExamID`,`StudentID`),
//CONSTRAINT `StudentExamGrade_FK_Exam` FOREIGN KEY (`ModuleID`,`ExamID`) REFERENCES `Exam`(`ModuleID`,`ExamID`) ON UPDATE CASCADE ON DELETE CASCADE,
//CONSTRAINT `StudentExamGrade_FK_StudentID` FOREIGN KEY (`StudentID`) REFERENCES `UserPrimaryData`(`UserID`) ON UPDATE CASCADE ON DELETE CASCADE
//);
