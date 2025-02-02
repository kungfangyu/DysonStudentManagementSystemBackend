package com.dysonstudentmanagement.dsm.service.impl;

import com.dysonstudentmanagement.dsm.dto.ModuleMaterialDto;
import com.dysonstudentmanagement.dsm.entity.modulematerial.ModuleMaterial;
import com.dysonstudentmanagement.dsm.entity.modulematerial.ModuleMaterialCompositeKey;
import com.dysonstudentmanagement.dsm.entity.moduledetails.ModuleDetails;
import com.dysonstudentmanagement.dsm.mapper.ModuleMaterialMapper;
import com.dysonstudentmanagement.dsm.repository.ModuleDetailsRepository;
import com.dysonstudentmanagement.dsm.repository.ModuleMaterialRepository;
import com.dysonstudentmanagement.dsm.service.ModuleMaterialService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
/*
ModuleMaterialServiceImpl

Class that implements service methods that act upon ModuleMaterial table in database

Original Author: Tianpu Li 25/04/2024
 */
public class ModuleMaterialServiceImpl implements ModuleMaterialService {
    private ModuleMaterialRepository moduleMaterialRepository;
    private ModuleDetailsRepository moduleDetailsRepository;

    @Override
    public ModuleMaterialDto createModuleMaterial(ModuleMaterialDto moduleMaterialDto) {
        ModuleMaterial moduleMaterial = ModuleMaterialMapper.mapToModuleMaterial(moduleMaterialDto);
        ModuleDetails moduleDetails = moduleDetailsRepository.findById(moduleMaterial.getModuleID())
                .orElseThrow(() -> new DataIntegrityViolationException("Module Details Not Found"));

        int materialNumber = moduleMaterialRepository.findByModuleID(moduleMaterial.getModuleID()).size()+1;
        moduleMaterial.setMaterialNumber(materialNumber); // manual incrementing of material number as hibernate does not support autoincrementing of a single composite key column

        moduleMaterial.setModuleDetails(moduleDetails);
        ModuleMaterial savedModuleMaterial = moduleMaterialRepository.save(moduleMaterial);
        return ModuleMaterialMapper.mapToModuleMaterialDto(savedModuleMaterial);
    }

    @Override
    public ModuleMaterialDto getModuleMaterialById(ModuleMaterialCompositeKey moduleMaterialCompositeKey) {
        ModuleMaterial moduleMaterial = moduleMaterialRepository.findById(moduleMaterialCompositeKey)
                .orElseThrow(() -> new DataIntegrityViolationException("Module Material Not Found"));
        return ModuleMaterialMapper.mapToModuleMaterialDto(moduleMaterial);
    }

    @Override
    public List<ModuleMaterialDto> getMaterialsByModuleId(String moduleId) {
        List<ModuleMaterial> moduleMaterialList = moduleMaterialRepository.findByModuleID(moduleId);
        return moduleMaterialList.stream().map(ModuleMaterialMapper::mapToModuleMaterialDto).collect(Collectors.toList());
    }

    @Override
    public ModuleMaterialDto updateModuleMaterial(ModuleMaterialCompositeKey moduleMaterialCompositeKey, ModuleMaterialDto moduleMaterialDto) {
        ModuleMaterial moduleMaterial = moduleMaterialRepository.findById(moduleMaterialCompositeKey)
                .orElseThrow(() -> new DataIntegrityViolationException("Module Material Not Found"));
        moduleMaterial.setTitle(moduleMaterialDto.getTitle());
        moduleMaterial.setDescription(moduleMaterialDto.getDescription());
        ModuleMaterial savedModuleMaterial = moduleMaterialRepository.save(moduleMaterial);
        return ModuleMaterialMapper.mapToModuleMaterialDto(savedModuleMaterial);
    }

    @Override
    public void deleteModuleMaterial(ModuleMaterialCompositeKey moduleMaterialCompositeKey) {
        ModuleMaterial moduleMaterial = moduleMaterialRepository.findById(moduleMaterialCompositeKey)
                .orElseThrow(() -> new DataIntegrityViolationException("Module Material Not Found"));
        moduleMaterialRepository.deleteById(moduleMaterialCompositeKey);
    }
}
