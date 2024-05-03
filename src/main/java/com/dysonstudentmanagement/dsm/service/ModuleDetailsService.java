package com.dysonstudentmanagement.dsm.service;

import com.dysonstudentmanagement.dsm.dto.ModuleDetailsDto;

import java.util.List;

/*
ModuleDetailsService

Interface that declares service methods that act upon ModuleDetail table in database

Original Author: Billy Peters 24/04/2024
 */
public interface ModuleDetailsService {
    ModuleDetailsDto createModuleDetails(ModuleDetailsDto moduleDetailsDto);

    ModuleDetailsDto getModuleDetails(String moduleID);

    List<ModuleDetailsDto> getAllModuleDetails();

    ModuleDetailsDto updateModuleDetails(String moduleID, ModuleDetailsDto updateModuleDetailsDto);

    void deleteModuleDetails(String moduleID);
}
