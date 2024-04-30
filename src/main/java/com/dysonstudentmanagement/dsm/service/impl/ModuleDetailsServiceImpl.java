package com.dysonstudentmanagement.dsm.service.impl;

import com.dysonstudentmanagement.dsm.dto.ModuleDetailsDto;
import com.dysonstudentmanagement.dsm.entity.moduledetails.ModuleDetails;
import com.dysonstudentmanagement.dsm.mapper.ModuleDetailsMapper;
import com.dysonstudentmanagement.dsm.repository.ModuleDetailsRepository;
import com.dysonstudentmanagement.dsm.service.ModuleDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ModuleDetailsServiceImpl implements ModuleDetailsService {
    private ModuleDetailsRepository moduleDetailsRepository;

    @Override
    public ModuleDetailsDto createModuleDetails(ModuleDetailsDto moduleDetailsDto) {
        ModuleDetails moduleDetails = ModuleDetailsMapper.mapToModuleDetails(moduleDetailsDto);
        Optional<ModuleDetails> optionalModuleDetails = moduleDetailsRepository.findById(moduleDetails.getModuleID());
        if (optionalModuleDetails.isPresent()) {
            throw new DataIntegrityViolationException("Module already exists");
        }else {
            moduleDetailsRepository.save(moduleDetails);
            return ModuleDetailsMapper.mapToModuleDetailsDto(moduleDetails);
        }
    }

    @Override
    public ModuleDetailsDto getModuleDetails(String moduleID) {
        ModuleDetails moduleDetails = moduleDetailsRepository.findById(moduleID)
                .orElseThrow(() -> new DataIntegrityViolationException("Module does not exist"));
        return ModuleDetailsMapper.mapToModuleDetailsDto(moduleDetails);
    }

    @Override
    public List<ModuleDetailsDto> getAllModuleDetails() {
        List<ModuleDetails> moduleDetailsList = moduleDetailsRepository.findAll();
        return moduleDetailsList.stream().map(ModuleDetailsMapper::mapToModuleDetailsDto).collect(Collectors.toList());
    }

    @Override
    public ModuleDetailsDto updateModuleDetails(String moduleID, ModuleDetailsDto updateModuleDetailsDto) {
        ModuleDetails moduleDetails = moduleDetailsRepository.findById(moduleID)
                .orElseThrow(() -> new DataIntegrityViolationException("Module does not exist"));
        moduleDetails.setModuleName(updateModuleDetailsDto.getModuleName());
        moduleDetails.setModuleCredits(updateModuleDetailsDto.getModuleCredits());
        ModuleDetails savedData = moduleDetailsRepository.save(moduleDetails);
        return ModuleDetailsMapper.mapToModuleDetailsDto(savedData);
    }

    @Override
    public void deleteModuleDetails(String moduleID) {
        ModuleDetails moduleDetails = moduleDetailsRepository.findById(moduleID)
                .orElseThrow(() -> new DataIntegrityViolationException("Module does not exist"));
        moduleDetailsRepository.deleteById(moduleID);
    }
}
