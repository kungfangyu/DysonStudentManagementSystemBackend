package com.dysonstudentmanagement.dsm.service;

import com.dysonstudentmanagement.dsm.dto.ProgrammeAnnouncementDto;
import com.dysonstudentmanagement.dsm.entity.programmeannouncement.ProgrammeAnnouncementCompositeKey;

import java.util.List;

/*
ProgrammeAnnouncementService

Interface that declares service methods that act upon ProgrammeAnnouncement table in database

Original Author: Tianpu Li 25/04/2024
 */
public interface ProgrammeAnnouncementService {
    ProgrammeAnnouncementDto createProgrammeAnnouncement(ProgrammeAnnouncementDto programmeAnnouncementDto);
    ProgrammeAnnouncementDto getProgrammeAnnouncement(ProgrammeAnnouncementCompositeKey targetKey);
    List<ProgrammeAnnouncementDto> getProgrammeAnnouncementsByProgrammeId(String programmeId);
    ProgrammeAnnouncementDto updateProgrammeAnnouncement(ProgrammeAnnouncementCompositeKey targetKey, ProgrammeAnnouncementDto programmeAnnouncementDto);
    void deleteProgrammeAnnouncement(ProgrammeAnnouncementCompositeKey targetKey);
}
