package com.dysonstudentmanagement.dsm.repository;

import com.dysonstudentmanagement.dsm.entity.programmeannouncement.ProgrammeAnnouncement;
import com.dysonstudentmanagement.dsm.entity.programmeannouncement.ProgrammeAnnouncementCompositeKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProgrammeAnnouncementRepository extends JpaRepository<ProgrammeAnnouncement, ProgrammeAnnouncementCompositeKey> {
    List<ProgrammeAnnouncement> findByProgrammeID(String programmeId);
}
