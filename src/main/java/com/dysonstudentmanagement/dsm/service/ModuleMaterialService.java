package com.dysonstudentmanagement.dsm.service;

import com.dysonstudentmanagement.dsm.dto.ModuleMaterialDto;
import com.dysonstudentmanagement.dsm.entity.ModuleMaterial.ModuleMaterialCompositeKey;

import java.util.List;

public interface ModuleMaterialService {
    ModuleMaterialDto createModuleMaterial(ModuleMaterialDto moduleMaterialDto);

    ModuleMaterialDto updateModuleMaterial(ModuleMaterialCompositeKey moduleMaterialCompositeKey, ModuleMaterialDto moduleMaterialDto);

    ModuleMaterialDto getModuleMaterialById(ModuleMaterialCompositeKey moduleMaterialCompositeKey);

    List<ModuleMaterialDto> getMaterialsByModuleId(String moduleId);

    void deleteModuleMaterial(ModuleMaterialCompositeKey moduleMaterialCompositeKey);
}
