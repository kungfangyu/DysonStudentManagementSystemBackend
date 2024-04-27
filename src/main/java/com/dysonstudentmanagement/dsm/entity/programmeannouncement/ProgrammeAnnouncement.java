package com.dysonstudentmanagement.dsm.entity.programmeannouncement;

import com.dysonstudentmanagement.dsm.entity.user.UserPrimaryData;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "ProgrammeAnnouncement")
@IdClass(ProgrammeAnnouncementCompositeKey.class)
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
    private Timestamp datePosted;

    @MapsId
    @ManyToOne
    @JoinColumn(name = "ProgrammeId", referencedColumnName = "ProgrammeId")
    private Programme programme;

    @MapsId
    @ManyToOne
    @JoinColumn(name = "StaffID", referencedColumnName = "UserID")
    private UserPrimaryData userPrimaryData;
}
