package com.dysonstudentmanagement.dsm.repository;

import com.dysonstudentmanagement.dsm.entity.coursework.Coursework;
import com.dysonstudentmanagement.dsm.entity.coursework.CourseworkCompositeKey;
import com.dysonstudentmanagement.dsm.entity.moduleannouncement.ModuleAnnouncement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/*
    Interface: CourseworkRepository
    Interface which provides the Coursework Entity with the relevant JPARepo methods.
    Original Author: Jack Burnett, 27/04/2024
*/
public interface CourseworkRepository extends JpaRepository<Coursework, CourseworkCompositeKey> {
    public List<Coursework> findByModuleID(String moduleID);
}
