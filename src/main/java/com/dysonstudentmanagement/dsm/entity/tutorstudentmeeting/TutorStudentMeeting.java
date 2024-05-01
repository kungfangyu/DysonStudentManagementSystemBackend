package com.dysonstudentmanagement.dsm.entity.tutorstudentmeeting;

import com.dysonstudentmanagement.dsm.entity.coursework.Coursework;
import com.dysonstudentmanagement.dsm.entity.studentcourseworkgrade.StudentCourseworkGradeCompositeKey;
import com.dysonstudentmanagement.dsm.entity.user.UserPrimaryData;
import com.dysonstudentmanagement.dsm.entitylistener.TutorStudentMeetingListener;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/*
    Class: TutorStudentMeeting
    Scaffolded JPA entity object to correspond with TutorStudentMeeting table
    Original Author: Jack Burnett, 30/04/2024
*/

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "TutorStudentMeeting")
@EntityListeners(TutorStudentMeetingListener.class)
@IdClass(TutorStudentMeetingCompositeKey.class)
public class TutorStudentMeeting {
    @Id
    @Column(name = "StaffID")
    private String staffID;
    @Id
    @Column(name = "StudentID")
    private String studentID;
    @Id
    @Column(name = "MeetingTime")
    private LocalDateTime meetingTime;
    @Column(name = "Notes")
    private String notes;

    @MapsId
    @ManyToOne
    @JoinColumn(name = "StaffID", referencedColumnName = "UserID")
    private UserPrimaryData staffPrimaryData;

    @MapsId
    @ManyToOne
    @JoinColumn(name = "StudentID", referencedColumnName = "UserID")
    private UserPrimaryData studentPrimaryData;
}