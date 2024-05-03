package com.dysonstudentmanagement.dsm.mapper;

import com.dysonstudentmanagement.dsm.dto.ModuleMaterialDto;
import com.dysonstudentmanagement.dsm.entity.modulematerial.ModuleMaterial;

/*
    Class: ModuleMaterialMapper
    Mapper class responsible for mapping between ModuleMaterial and ModuleMaterialDto objects.
    Original Author: Tianpu Li 25/04/2024
 */
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
