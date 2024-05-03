package com.dysonstudentmanagement.dsm.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@ToString
/*
ProgrammeAnnouncementDto

Defines fields/data that will be transmitted to/from the API client when requesting/sending programmeAnnouncement data.

Original Author: Tianpu Li 25/04/2024
 */
public class ProgrammeAnnouncementDto {
    private String programmeID;
    private int announcementID;
    private String staffID;
    private String title;
    private String description;
    private LocalDateTime datePosted;
}
