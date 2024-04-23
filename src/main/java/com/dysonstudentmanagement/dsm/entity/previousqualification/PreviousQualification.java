package com.dysonstudentmanagement.dsm.entity.previousqualification;

import com.dysonstudentmanagement.dsm.entity.user.UserPrimaryData;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "PreviousQualification")
@IdClass(PreviousQualificationCompositeKey.class)
public class PreviousQualification {
    @Id
    @Column(name = "UserID", length = 8)
    private String userID;
    @Id
    @Column(name = "QualificationLevel", length = 50)
    private String qualificationLevel;
    @Id
    @Column(name = "Subject", length = 50)
    private String subject;
    @Column(name = "Grade", length = 20)
    private String grade;
    @Column(name = "DateAchieved")
    private LocalDate dateAchieved;
    @Column(name = "Institution")
    private String institution;

    @MapsId
    @ManyToOne
    @JoinColumn(name = "UserID", referencedColumnName = "UserID")
    private UserPrimaryData userPrimaryData;
}
