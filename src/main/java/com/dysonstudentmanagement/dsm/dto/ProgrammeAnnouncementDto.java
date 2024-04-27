package com.dysonstudentmanagement.dsm.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class ProgrammeAnnouncementDto {
    private String programmeID;
    private int announcementID;
    private String staffID;
    private String title;
    private String description;
    private Timestamp datePosted;
}
