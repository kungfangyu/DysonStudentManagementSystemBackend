package com.dysonstudentmanagement.dsm.repository;

import com.dysonstudentmanagement.dsm.entity.programmmodules.ProgrammeModules;
import com.dysonstudentmanagement.dsm.entity.programmmodules.ProgrammeModulesCompositeKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ProgrammeModulesRepository extends JpaRepository<ProgrammeModules, ProgrammeModulesCompositeKey> {

    public List<ProgrammeModules> findByModuleID(String moduleID);

    public List<ProgrammeModules> findByProgrammeID(String ProgrammeID);

    public List<ProgrammeModules> findAll();

}
