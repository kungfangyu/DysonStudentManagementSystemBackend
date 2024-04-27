package com.dysonstudentmanagement.dsm.repository;

import com.dysonstudentmanagement.dsm.entity.Programme.programme;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface programmeRepository extends JpaRepository<programme, String> {
    List<programme> findByProgrammeID(String programmeID);
}
