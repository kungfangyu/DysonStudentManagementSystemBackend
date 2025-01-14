package com.dysonstudentmanagement.dsm.entity.programmeannouncement;

import com.dysonstudentmanagement.dsm.entity.programme.Programme;
import com.dysonstudentmanagement.dsm.entity.user.UserPrimaryData;
import com.dysonstudentmanagement.dsm.entitylistener.ProgrammeAnnouncementListener;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "ProgrammeAnnouncement")
@EntityListeners(ProgrammeAnnouncementListener.class)
@IdClass(ProgrammeAnnouncementCompositeKey.class)
/*
ProgrammeAnnouncement Entity

Spring representation of the database table "ProgrammeAnnouncement"

Original Author: Tianpu Li 25/04/2024
 */
public class ProgrammeAnnouncement {

    @Id
    @Column(name = "ProgrammeID", length = 12)
    private String programmeID;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AnnouncementID")
    private int announcementID;

    @Column(name = "StaffID")
    private String staffID;
    @Column(name = "Title")
    private String title;
    @Column(name = "Description")
    private String description;
    @Column(name = "DatePosted")
    private LocalDateTime datePosted;

    @MapsId
    @ManyToOne
    @JoinColumn(name = "ProgrammeId", referencedColumnName = "ProgrammeId")
    private Programme programme;

    @MapsId
    @ManyToOne
    @JoinColumn(name = "StaffID", referencedColumnName = "UserID")
    private UserPrimaryData userPrimaryData;
}
