package com.dysonstudentmanagement.dsm.mapper;

import com.dysonstudentmanagement.dsm.dto.ModuleMaterialFileDto;
import com.dysonstudentmanagement.dsm.entity.modulematerilafile.ModuleMaterialFile;

/*
    Class: ModuleMaterialFileMapper
    Mapper class responsible for mapping between ModuleMaterialFile and ModuleMaterialFileDto objects.
    Original Author: Grace Liu 25/04/2024
 */
public class ModuleMaterialFileMapper {
    public static ModuleMaterialFileDto mapToModuleMaterialFileDto(ModuleMaterialFile moduleMaterialFile) {
        return new ModuleMaterialFileDto(
                moduleMaterialFile.getModuleID(),
                moduleMaterialFile.getMaterialNumber(),
                moduleMaterialFile.getFileNumber(),
                moduleMaterialFile.getFilePath()
        );
    }

    public static ModuleMaterialFile mapToModuleMaterialFile(ModuleMaterialFileDto moduleMaterialFileDto) {
        return ModuleMaterialFile.builder()
                .moduleID(moduleMaterialFileDto.getModuleID())
                .materialNumber(moduleMaterialFileDto.getMaterialNumber())
                .fileNumber(moduleMaterialFileDto.getFileNumber())
                .filePath(moduleMaterialFileDto.getFilePath())
                .build();
    }
}
