package com.dysonstudentmanagement.dsm.mapper;

import com.dysonstudentmanagement.dsm.dto.ModuleAnnouncementDto;
import com.dysonstudentmanagement.dsm.entity.moduleannouncement.ModuleAnnouncement;

/*
    Class: ModuleAnnouncementMapper
    Standard data transfer object class to manage information sent in API requests.
    Original Author: Jack Burnett, 25/04/2024
*/
public class ModuleAnnouncementMapper {
    public static ModuleAnnouncementDto mapToModuleAnnouncementDto (ModuleAnnouncement moduleAnnouncement) {
        return new ModuleAnnouncementDto(
                moduleAnnouncement.getModuleID(),
                moduleAnnouncement.getAnnouncementID(),
                moduleAnnouncement.getStaffId(),
                moduleAnnouncement.getTitle(),
                moduleAnnouncement.getDescription(),
                moduleAnnouncement.getDatePosted()
        );
    }

    public static ModuleAnnouncement mapToModuleAnnouncement(ModuleAnnouncementDto moduleAnnouncementDto) {
        ModuleAnnouncement moduleAnnouncement = ModuleAnnouncement.builder()
                .moduleID(moduleAnnouncementDto.getModuleID())
                .announcementID(moduleAnnouncementDto.getAnnouncementID())
                .staffId(moduleAnnouncementDto.getStaffID())
                .title(moduleAnnouncementDto.getTitle())
                .description(moduleAnnouncementDto.getDescription())
                .datePosted(moduleAnnouncementDto.getDatePosted())
                .build();
        return moduleAnnouncement;
    }
}
