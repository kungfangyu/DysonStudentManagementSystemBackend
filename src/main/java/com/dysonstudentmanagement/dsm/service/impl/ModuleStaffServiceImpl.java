package com.dysonstudentmanagement.dsm.service.impl;

import com.dysonstudentmanagement.dsm.dto.ModuleStaffDto;
import com.dysonstudentmanagement.dsm.entity.moduledetails.ModuleDetails;
import com.dysonstudentmanagement.dsm.entity.modulestaff.ModuleStaff;
import com.dysonstudentmanagement.dsm.entity.modulestaff.ModuleStaffCompositeKey;
import com.dysonstudentmanagement.dsm.entity.user.UserPrimaryData;
import com.dysonstudentmanagement.dsm.exception.ResourceNotFoundException;
import com.dysonstudentmanagement.dsm.mapper.ModuleStaffMapper;
import com.dysonstudentmanagement.dsm.repository.ModuleDetailsRepository;
import com.dysonstudentmanagement.dsm.repository.ModuleStaffRepository;
import com.dysonstudentmanagement.dsm.repository.UserPrimaryDataRepository;
import com.dysonstudentmanagement.dsm.service.ModuelStaffService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ModuleStaffServiceImpl implements ModuelStaffService {
    private ModuleDetailsRepository moduleDetailsRepository;
    private UserPrimaryDataRepository userPrimaryDataRepository;
    private ModuleStaffRepository moduleStaffRepository;

    @Override
    public ModuleStaffDto createModuleStaff(ModuleStaffDto moduleStaffDto) {
        ModuleStaff moduleStaff = ModuleStaffMapper.mapToModuleStaff(moduleStaffDto);
        UserPrimaryData userPrimaryData = userPrimaryDataRepository.findById(moduleStaff.getStaffID())
                .orElseThrow(() -> new DataIntegrityViolationException("User does not exist"));
        ModuleDetails moduleDetails = moduleDetailsRepository.findById(moduleStaff.getModuleID())
                .orElseThrow(() -> new DataIntegrityViolationException("Module does not exist"));

        ModuleStaffCompositeKey pk = new ModuleStaffCompositeKey(moduleStaff.getModuleID(), moduleStaff.getStaffID());
        Optional<ModuleStaff> moduleStaffOptional = moduleStaffRepository.findById(pk);
        if (moduleStaffOptional.isPresent()) {
            throw new DataIntegrityViolationException("Failed...Record already exists for given primary key");
        }else {
            moduleStaff.setModuleDetails(moduleDetails);
            moduleStaff.setUserPrimaryData(userPrimaryData);
            return ModuleStaffMapper.mapToModuleStaffDto(moduleStaff);
        }
    }

    @Override
    public List<ModuleStaffDto> getModuleByStaffId(String staffId) {
        List<ModuleStaff> moduleStaffs = moduleStaffRepository.findByStaffID(staffId);
        return moduleStaffs.stream().map(ModuleStaffMapper::mapToModuleStaffDto).collect(Collectors.toList());
    }

    @Override
    public List<ModuleStaffDto> getStaffByModuleId(String moduleId) {
        List<ModuleStaff> moduleStaffs = moduleStaffRepository.findByModuleID(moduleId);
        return moduleStaffs.stream().map(ModuleStaffMapper::mapToModuleStaffDto).collect(Collectors.toList());
    }

    @Override
    public void deleteModuleStaff(ModuleStaffCompositeKey targetKey) {
        ModuleStaff moduleStaff = moduleStaffRepository.findById(targetKey)
                .orElseThrow(() -> new ResourceNotFoundException("Module does not exist"));
        moduleStaffRepository.deleteById(targetKey);
    }
}
