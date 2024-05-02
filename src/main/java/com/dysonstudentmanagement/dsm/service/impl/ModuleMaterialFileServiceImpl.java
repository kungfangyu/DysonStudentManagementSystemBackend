package com.dysonstudentmanagement.dsm.service.impl;

import com.dysonstudentmanagement.dsm.dto.ModuleMaterialFileDto;
import com.dysonstudentmanagement.dsm.entity.modulematerial.ModuleMaterial;
import com.dysonstudentmanagement.dsm.entity.modulematerial.ModuleMaterialCompositeKey;
import com.dysonstudentmanagement.dsm.entity.modulematerilafile.ModuleMaterialFile;
import com.dysonstudentmanagement.dsm.entity.modulematerilafile.ModuleMaterialFileCompositeKey;
import com.dysonstudentmanagement.dsm.mapper.ModuleMaterialFileMapper;
import com.dysonstudentmanagement.dsm.repository.ModuleMaterialFileRepository;
import com.dysonstudentmanagement.dsm.repository.ModuleMaterialRepository;
import com.dysonstudentmanagement.dsm.service.ModuleMaterialFileService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ModuleMaterialFileServiceImpl implements ModuleMaterialFileService {
    private ModuleMaterialFileRepository moduleMaterialFileRepository;
    private ModuleMaterialRepository moduleMaterialRepository;

    @Override
    public ModuleMaterialFileDto createModuleMaterialFile(ModuleMaterialFileDto moduleMaterialFileDto) {
        ModuleMaterialFile moduleMaterialFile = ModuleMaterialFileMapper.mapToModuleMaterialFile(moduleMaterialFileDto);
        ModuleMaterialCompositeKey moduleMaterialCompositeKey = new ModuleMaterialCompositeKey(moduleMaterialFile.getModuleID(),
                moduleMaterialFile.getMaterialNumber());
        ModuleMaterial moduleMaterial = moduleMaterialRepository.findById(moduleMaterialCompositeKey)
                .orElseThrow(() -> new DataIntegrityViolationException("Module Material Not Found"));


        int fileNumber = moduleMaterialFileRepository.findByModuleIDAndMaterialNumber(moduleMaterialFile.getModuleID(),moduleMaterialFile.getMaterialNumber()).size()+1;
        moduleMaterialFile.setFileNumber(fileNumber); //manual assignment of fileNumber as hibernate does not support autoincrementing of a single column in a composite key.

        moduleMaterialFile.setModuleMaterial(moduleMaterial);
        ModuleMaterialFile savedModuleMaterialFile = moduleMaterialFileRepository.save(moduleMaterialFile);
        return ModuleMaterialFileMapper.mapToModuleMaterialFileDto(savedModuleMaterialFile);
    }

    @Override
    public ModuleMaterialFileDto getModuleMaterialFileById(ModuleMaterialFileCompositeKey moduleMaterialFileCompositeKey) {
        ModuleMaterialFile moduleMaterialFile = moduleMaterialFileRepository.findById(moduleMaterialFileCompositeKey)
                .orElseThrow(() -> new DataIntegrityViolationException("Module Material File Not Found"));
        return ModuleMaterialFileMapper.mapToModuleMaterialFileDto(moduleMaterialFile);
    }

    @Override
    public List<ModuleMaterialFileDto> getMaterialsByModuleMaterialId(String moduleId, int materialNumber) {
        List<ModuleMaterialFile> moduleMaterialFileList = moduleMaterialFileRepository.findByModuleIDAndMaterialNumber(moduleId, materialNumber);
        return moduleMaterialFileList.stream().map(ModuleMaterialFileMapper::mapToModuleMaterialFileDto).collect(Collectors.toList());
    }

    @Override
    public ModuleMaterialFileDto updateModuleMaterialFile(ModuleMaterialFileCompositeKey moduleMaterialFileCompositeKey, ModuleMaterialFileDto moduleMaterialFileDto) {
        ModuleMaterialFile moduleMaterialFile = moduleMaterialFileRepository.findById(moduleMaterialFileCompositeKey)
                .orElseThrow(() -> new DataIntegrityViolationException("Module Material File Not Found"));
        moduleMaterialFile.setFilePath(moduleMaterialFileDto.getFilePath());
        ModuleMaterialFile savedModuleMaterialFile = moduleMaterialFileRepository.save(moduleMaterialFile);
        return ModuleMaterialFileMapper.mapToModuleMaterialFileDto(savedModuleMaterialFile);
    }

    @Override
    public void deleteModuleMaterialFile(ModuleMaterialFileCompositeKey moduleMaterialFileCompositeKey) {
        ModuleMaterialFile moduleMaterialFile = moduleMaterialFileRepository.findById(moduleMaterialFileCompositeKey)
                .orElseThrow(() -> new DataIntegrityViolationException("Module Material File Not Found"));
        moduleMaterialFileRepository.deleteById(moduleMaterialFileCompositeKey);
    }
}
