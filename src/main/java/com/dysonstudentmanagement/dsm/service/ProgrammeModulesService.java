package com.dysonstudentmanagement.dsm.service;
import com.dysonstudentmanagement.dsm.dto.ProgrammeModulesDto;
import com.dysonstudentmanagement.dsm.entity.programmmodules.ProgrammeModulesCompositeKey;

import java.util.List;

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

