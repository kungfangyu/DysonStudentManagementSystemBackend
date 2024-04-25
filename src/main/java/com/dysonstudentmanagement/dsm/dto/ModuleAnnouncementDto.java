package com.dysonstudentmanagement.dsm.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
public class ModuleAnnouncementDto {
    private String moduleID;
    private int announcementID;
    private String staffID;
    private String title;
    private String description;
    private Timestamp datePosted;
}
