package com.dysonstudentmanagement.dsm.service;

import com.dysonstudentmanagement.dsm.dto.ModuleAnnouncementDto;
import com.dysonstudentmanagement.dsm.entity.moduleannouncement.ModuleAnnouncementCompositeKey;

import java.util.List;

/*
    Interface: ModuleAnnouncementService
    Interface which sets out the required API methods to be implemented.
    Original Author: Jack Burnett, 25/04/2024
*/
public interface ModuleAnnouncementService {
    ModuleAnnouncementDto createModuleAnnouncement(ModuleAnnouncementDto moduleDetailsDto);

    ModuleAnnouncementDto getModuleAnnouncement(ModuleAnnouncementCompositeKey targetKey);

    List<ModuleAnnouncementDto> getModuleAnnouncementsByModuleID(String moduleID);

    ModuleAnnouncementDto updateModuleAnnouncement(ModuleAnnouncementCompositeKey targetKey, ModuleAnnouncementDto updatedAnnouncementDto);

    void deleteModuleDetails(ModuleAnnouncementCompositeKey targetKey);
}
