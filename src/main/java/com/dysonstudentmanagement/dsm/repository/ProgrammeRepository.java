package com.dysonstudentmanagement.dsm.repository;

import com.dysonstudentmanagement.dsm.entity.programme.Programme;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProgrammeRepository extends JpaRepository<Programme, String> {
    List<Programme> findByProgrammeID(String programmeID);
}
