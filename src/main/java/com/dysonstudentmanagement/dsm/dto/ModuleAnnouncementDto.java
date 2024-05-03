package com.dysonstudentmanagement.dsm.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
/*
ModuleAnnouncementDto

Defines fields/data that will be transmitted to/from the API client when requesting/sending moduleAnnouncement data.

Original Author: Jack Burnett 25/04/2024
 */
public class ModuleAnnouncementDto {
    private String moduleID;
    private int announcementID;
    private String staffID;
    private String title;
    private String description;
    private LocalDateTime datePosted;
}
