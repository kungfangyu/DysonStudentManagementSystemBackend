package com.dysonstudentmanagement.dsm.mapper;

import com.dysonstudentmanagement.dsm.dto.ModuleMaterialDto;
import com.dysonstudentmanagement.dsm.entity.ModuleMaterial.ModuleMaterial;

public class ModuleMaterialMapper {
    public static ModuleMaterialDto mapToModuleMaterialDto(ModuleMaterial moduleMaterial) {
        return new ModuleMaterialDto(
                moduleMaterial.getModuleID(),
                moduleMaterial.getMaterialNumber(),
                moduleMaterial.getTitle(),
                moduleMaterial.getDescription(),
                moduleMaterial.isPublished()
        );
    }

    public static ModuleMaterial mapToModuleMaterial(ModuleMaterialDto moduleMaterialDto) {
        return ModuleMaterial.builder()
                .moduleID(moduleMaterialDto.getModuleID())
                .materialNumber(moduleMaterialDto.getMaterialNumber())
                .title(moduleMaterialDto.getTitle())
                .description(moduleMaterialDto.getDescription())
                .isPublished(moduleMaterialDto.isPublished())
                .build();
    }
}
