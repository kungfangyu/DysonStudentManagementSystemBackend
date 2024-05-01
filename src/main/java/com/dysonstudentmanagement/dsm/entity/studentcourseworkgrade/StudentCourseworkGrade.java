package com.dysonstudentmanagement.dsm.entity.studentcourseworkgrade;

import com.dysonstudentmanagement.dsm.entity.coursework.Coursework;
import com.dysonstudentmanagement.dsm.entity.moduleannouncement.ModuleAnnouncementCompositeKey;
import com.dysonstudentmanagement.dsm.entity.moduledetails.ModuleDetails;
import com.dysonstudentmanagement.dsm.entity.user.UserPrimaryData;
import com.dysonstudentmanagement.dsm.entitylistener.StudentCourseworkGradeListener;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/*
    Class: StudentCourseworkGrade
    Scaffolded JPA entity object to correspond with StudentCourseworkGrade table
    Original Author: Jack Burnett, 27/04/2024
*/

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "StudentCourseworkGrade")
@EntityListeners(StudentCourseworkGradeListener.class)
@IdClass(StudentCourseworkGradeCompositeKey.class)
public class StudentCourseworkGrade {
    @Id
    @Column(name = "ModuleID")
    private String moduleID;
    @Id
    @Column(name = "CourseworkID")
    private int courseworkID;
    @Id
    @Column(name = "StudentID")
    private String studentID;

    @Column(name = "SubmissionFilePath")
    private String submissionFilePath;
    @Column(name = "SubmissionTime")
    private LocalDateTime submissionTime;
    @Column(name = "isOnTime")
    private boolean isOnTime;
    @Column(name = "Grade")
    private Double grade;
    @Column(name = "Feedback")
    private String feedback;

    @MapsId
    @ManyToOne
    @JoinColumns({
            @JoinColumn (name = "ModuleID", referencedColumnName = "ModuleID"),
            @JoinColumn (name = "CourseworkID", referencedColumnName = "CourseworkID")
    })
    private Coursework coursework;

    @MapsId
    @ManyToOne
    @JoinColumn(name = "StudentID", referencedColumnName = "UserID")
    private UserPrimaryData userPrimaryData;
}
