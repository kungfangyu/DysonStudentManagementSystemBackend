package com.dysonstudentmanagement.dsm.repository;

import com.dysonstudentmanagement.dsm.entity.ProgrammModules.programmeModules;
import com.dysonstudentmanagement.dsm.entity.ProgrammModules.programmeModulesCompositeKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface programmeModulesRepository extends JpaRepository<programmeModules, programmeModulesCompositeKey> {

    public List<programmeModules> findByModuleID(String moduleID);

    public List<programmeModules> findByProgrammeID(String ProgrammeID);

    public List<programmeModules> findAll();

}
