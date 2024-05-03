package com.dysonstudentmanagement.dsm.mapper;

import com.dysonstudentmanagement.dsm.dto.ModuleDetailsDto;
import com.dysonstudentmanagement.dsm.entity.moduledetails.ModuleDetails;

/*
    Class: ModuleDetailsMapper
    Mapper class responsible for mapping between ModuleDetails and ModuleDetailsDto objects.
    Original Author: Billy Peters 24/04/2024
 */
public class ModuleDetailsMapper {
    public static ModuleDetailsDto mapToModuleDetailsDto(ModuleDetails moduleDetails){
        return new ModuleDetailsDto(
                moduleDetails.getModuleID(),
                moduleDetails.getModuleName(),
                moduleDetails.getModulePhoto(),
                moduleDetails.getModuleDescription(),
                moduleDetails.getStartDate(),
                moduleDetails.getEndDate(),
                moduleDetails.getModuleCredits()
        );
    }

    public static ModuleDetails mapToModuleDetails(ModuleDetailsDto moduleDetailsDto){
        ModuleDetails moduleDetails = ModuleDetails.builder()
                .moduleID(moduleDetailsDto.getModuleID())
                .moduleName(moduleDetailsDto.getModuleName())
                .modulePhoto(moduleDetailsDto.getModulePhoto())
                .moduleDescription(moduleDetailsDto.getModuleDescription())
                .startDate(moduleDetailsDto.getStartDate())
                .endDate(moduleDetailsDto.getEndDate())
                .moduleCredits(moduleDetailsDto.getModuleCredits())
                .build();
        return moduleDetails;
    }
}
