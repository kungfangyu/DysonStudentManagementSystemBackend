package com.dysonstudentmanagement.dsm.entity.moduleannouncement;

import com.dysonstudentmanagement.dsm.entity.moduledetails.ModuleDetails;
import com.dysonstudentmanagement.dsm.entity.user.UserPrimaryData;
import com.dysonstudentmanagement.dsm.entitylistener.ModuleAnnouncementListener;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/*
    Class: ModuleAnnouncement
    Scaffolded JPA entity object to correspond with ModuleAnnouncement table
    Original Author: Jack Burnett, 25/04/2024
*/

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "ModuleAnnouncement")
@EntityListeners(ModuleAnnouncementListener.class)
@IdClass(ModuleAnnouncementCompositeKey.class)
public class ModuleAnnouncement {
    @Id
    @Column(name = "ModuleID", length = 12)
    private String moduleID;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AnnouncementID")
    private int announcementID;

    @Column(name = "StaffID")
    private String staffId;
    @Column(name = "Title")
    private String title;
    @Column(name = "Description")
    private String description;
    @Column(name = "DatePosted")
    private LocalDateTime datePosted;

    @MapsId
    @ManyToOne
    @JoinColumn(name = "ModuleID", referencedColumnName = "ModuleID")
    private ModuleDetails moduleDetails;

    @MapsId
    @ManyToOne
    @JoinColumn(name = "StaffID", referencedColumnName = "UserID")
    private UserPrimaryData userPrimaryData;
}