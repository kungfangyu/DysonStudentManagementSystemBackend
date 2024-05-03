package com.dysonstudentmanagement.dsm.service;

import org.springframework.stereotype.Service;

import com.dysonstudentmanagement.dsm.dto.ProgrammeModulesDto;
import com.dysonstudentmanagement.dsm.entity.programmemodules.ProgrammeModulesCompositeKey;


import java.util.List;

/*
ProgrammeModulesService

Interface that declares service methods that act upon ProgrammeModules table in database

Original Author: Imran Matloob 24/04/2024
 */
public interface ProgrammeModulesService {

      ProgrammeModulesDto createProgrammeModules(ProgrammeModulesDto programmeModulesDto);

      List<ProgrammeModulesDto> getAllProgrammeModules();

      ProgrammeModulesDto getProgrammeModules(ProgrammeModulesCompositeKey targetKey);

      List<ProgrammeModulesDto> getProgrammeModulesDtoByModulesID(String ModulesID);

      List<ProgrammeModulesDto> getProgrammeModulesDtoByProgrammeID(String ProgrammeID);


      ProgrammeModulesDto updateProgrammeModules(ProgrammeModulesCompositeKey targetKey,
                                                 ProgrammeModulesDto updatedProgrammeModulesDto);

      void deleteProgrammeModules(ProgrammeModulesCompositeKey targetKey);
}

