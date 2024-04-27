package com.dysonstudentmanagement.dsm.mapper;

import com.dysonstudentmanagement.dsm.dto.programmeModulesDto;
import com.dysonstudentmanagement.dsm.entity.ProgrammModules.programmeModules;

public class programmeModulesMapper {
    public static programmeModulesDto mapToProgrammeModulesDto(programmeModules programmeModules) {
        return new programmeModulesDto(
                programmeModules.getModuleID(),
                programmeModules.getProgrammeID()
        );
    }

    public static programmeModules mapToProgrammeModules(programmeModulesDto programmeModulesDto) {
        programmeModules programmeModules = new programmeModules();
        programmeModules.setModuleID(programmeModulesDto.getModuleID());
        programmeModules.setProgrammeID(programmeModulesDto.getProgrammeID());
        return programmeModules;
    }
}
