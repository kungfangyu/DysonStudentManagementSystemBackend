package com.dysonstudentmanagement.dsm.service;

import com.dysonstudentmanagement.dsm.dto.ModuleMaterialDto;
import com.dysonstudentmanagement.dsm.entity.modulematerial.ModuleMaterialCompositeKey;

import java.util.List;

/*
ModuleMaterialService

Interface that declares service methods that act upon ModuleMaterial table in database

Original Author: Tianpu Li 25/04/2024
 */
public interface ModuleMaterialService {
    ModuleMaterialDto createModuleMaterial(ModuleMaterialDto moduleMaterialDto);

    ModuleMaterialDto updateModuleMaterial(ModuleMaterialCompositeKey moduleMaterialCompositeKey, ModuleMaterialDto moduleMaterialDto);

    ModuleMaterialDto getModuleMaterialById(ModuleMaterialCompositeKey moduleMaterialCompositeKey);

    List<ModuleMaterialDto> getMaterialsByModuleId(String moduleId);

    void deleteModuleMaterial(ModuleMaterialCompositeKey moduleMaterialCompositeKey);
}
