package com.dysonstudentmanagement.dsm.mapper;

import com.dysonstudentmanagement.dsm.dto.ProgrammeAnnouncementDto;
import com.dysonstudentmanagement.dsm.entity.programmeannouncement.ProgrammeAnnouncement;

/*
    Class: ProgrammeAnnouncementMapper
    Mapper class responsible for mapping between ProgrammeAnnouncement and ProgrammeAnnouncementDto objects.
    Original Author: Tianpu Li 25/04/2024
 */
public class ProgrammeAnnouncementMapper {
    public static ProgrammeAnnouncementDto mapToProgrammeAnnouncementDto(ProgrammeAnnouncement programmeAnnouncement) {
        return new ProgrammeAnnouncementDto(
                programmeAnnouncement.getProgrammeID(),
                programmeAnnouncement.getAnnouncementID(),
                programmeAnnouncement.getStaffID(),
                programmeAnnouncement.getTitle(),
                programmeAnnouncement.getDescription(),
                programmeAnnouncement.getDatePosted()
        );
    }

    public static ProgrammeAnnouncement mapToProgrammeAnnouncement(ProgrammeAnnouncementDto programmeAnnouncementDto) {
        return ProgrammeAnnouncement.builder()
                .programmeID(programmeAnnouncementDto.getProgrammeID())
                .announcementID(programmeAnnouncementDto.getAnnouncementID())
                .staffID(programmeAnnouncementDto.getStaffID())
                .title(programmeAnnouncementDto.getTitle())
                .description(programmeAnnouncementDto.getDescription())
                .datePosted(programmeAnnouncementDto.getDatePosted())
                .build();
    }

}
