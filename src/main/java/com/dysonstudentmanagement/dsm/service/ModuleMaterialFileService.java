package com.dysonstudentmanagement.dsm.service;

import com.dysonstudentmanagement.dsm.dto.ModuleMaterialFileDto;
import com.dysonstudentmanagement.dsm.entity.modulematerilafile.ModuleMaterialFileCompositeKey;

import java.util.List;

public interface ModuleMaterialFileService {
    ModuleMaterialFileDto createModuleMaterialFile(ModuleMaterialFileDto moduleMaterialFileDto);

    ModuleMaterialFileDto updateModuleMaterialFile(ModuleMaterialFileCompositeKey moduleMaterialFileCompositeKey, ModuleMaterialFileDto moduleMaterialFileDto);

    ModuleMaterialFileDto getModuleMaterialFileById(ModuleMaterialFileCompositeKey moduleMaterialFileCompositeKey);

    List<ModuleMaterialFileDto> getMaterialsByModuleMaterialId(String moduleId, int materialNumber);

    void deleteModuleMaterialFile(ModuleMaterialFileCompositeKey moduleMaterialFileCompositeKey);
}
