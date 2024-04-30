package com.dysonstudentmanagement.dsm.mapper;

import com.dysonstudentmanagement.dsm.dto.ProgrammeModulesDto;
import com.dysonstudentmanagement.dsm.entity.programmemodules.ProgrammeModules;

public class ProgrammeModulesMapper {
    public static ProgrammeModulesDto mapToProgrammeModulesDto(ProgrammeModules programmeModules) {
        return new ProgrammeModulesDto(
                programmeModules.getModuleID(),
                programmeModules.getProgrammeID()
        );
    }

    public static ProgrammeModules mapToProgrammeModules(ProgrammeModulesDto programmeModulesDto) {
        ProgrammeModules programmeModules = new ProgrammeModules();
        programmeModules.setModuleID(programmeModulesDto.getModuleID());
        programmeModules.setProgrammeID(programmeModulesDto.getProgrammeID());
        return programmeModules;
    }
}
