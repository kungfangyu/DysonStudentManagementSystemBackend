package com.dysonstudentmanagement.dsm.service;
import com.dysonstudentmanagement.dsm.dto.programmeModulesDto;
import com.dysonstudentmanagement.dsm.entity.ProgrammModules.programmeModulesCompositeKey;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProgrammeModulesService {

      programmeModulesDto createProgrammeModules(programmeModulesDto programmeModulesDto);

      List<programmeModulesDto> getAllProgrammeModules();

      programmeModulesDto getProgrammeModules(programmeModulesCompositeKey targetKey);

      List<programmeModulesDto> getProgrammeModulesDtoByModulesID(String ModulesID);

      List<programmeModulesDto> getProgrammeModulesDtoByProgrammeID(String ProgrammeID);


      programmeModulesDto updateProgrammeModules(programmeModulesCompositeKey targetKey,
                                                 programmeModulesDto updatedProgrammeModulesDto);

      void deleteProgrammeModules(programmeModulesCompositeKey targetKey);
}

