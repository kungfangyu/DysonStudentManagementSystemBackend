package com.dysonstudentmanagement.dsm.entity.studenttutor;

import com.dysonstudentmanagement.dsm.entity.user.UserPrimaryData;
import com.dysonstudentmanagement.dsm.entity.useremergencycontact.UserEmergencyContactCompositeKey;
import com.dysonstudentmanagement.dsm.entitylistener.StudentTutorListener;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "StudentTutor")
@EntityListeners(StudentTutorListener.class)
@IdClass(StudentTutorCompositeKey.class)
/*
StudentTutor Entity

Spring representation of the database table "StudentTutor".

Original Author: Billy Peters 24/04/2024

 */
public class StudentTutor{

    @Id
    @Column(name = "StudentID", length = 8)
    private String studentID;
    @Id
    @Column(name = "StaffID", length = 8)
    private String staffID;

    @MapsId
    @OneToOne
    @JoinColumn(name = "StudentID", referencedColumnName = "UserID")
    private UserPrimaryData studentPrimaryData;

    @MapsId
    @ManyToOne
    @JoinColumn(name = "StaffID", referencedColumnName = "UserID")
    private UserPrimaryData staffPrimaryData;

}
