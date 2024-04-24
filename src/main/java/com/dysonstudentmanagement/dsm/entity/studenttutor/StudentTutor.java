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
