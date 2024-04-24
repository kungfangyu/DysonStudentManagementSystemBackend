package com.dysonstudentmanagement.dsm.service;

import com.dysonstudentmanagement.dsm.dto.ModuleDetailsDto;

import java.util.List;

public interface ModuleDetailsService {
    ModuleDetailsDto createModuleDetails(ModuleDetailsDto moduleDetailsDto);

    ModuleDetailsDto getModuleDetails(String moduleID);

    List<ModuleDetailsDto> getAllModuleDetails();

    ModuleDetailsDto updateModuleDetails(String moduleID, ModuleDetailsDto updateModuleDetailsDto);

    void deleteModuleDetails(String moduleID);
}
