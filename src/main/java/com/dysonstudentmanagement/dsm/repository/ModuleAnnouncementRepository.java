package com.dysonstudentmanagement.dsm.repository;

import com.dysonstudentmanagement.dsm.entity.moduleannouncement.ModuleAnnouncement;
import com.dysonstudentmanagement.dsm.entity.moduleannouncement.ModuleAnnouncementCompositeKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/*
    Interface: ModuleAnnouncementRepository
    Interface which provides the ModuleAnnouncement Entity with the relevant JPARepo methods.
    Original Author: Jack Burnett, 25/04/2024
*/
public interface ModuleAnnouncementRepository extends JpaRepository<ModuleAnnouncement, ModuleAnnouncementCompositeKey> {
    public List<ModuleAnnouncement> findByModuleID(String moduleID);
}
