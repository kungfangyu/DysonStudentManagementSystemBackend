package com.dysonstudentmanagement.dsm.service;

import com.dysonstudentmanagement.dsm.dto.ModuleDetailsDto;
import com.dysonstudentmanagement.dsm.dto.ModuleStaffDto;
import com.dysonstudentmanagement.dsm.entity.modulestaff.ModuleStaffCompositeKey;

import java.util.List;

public interface ModuelStaffService {
    ModuleStaffDto createModuleStaff(ModuleStaffDto moduleStaffDto);
    List<ModuleStaffDto> getStaffByModuleId(String moduleId);
    List<ModuleStaffDto> getModuleByStaffId(String staffId);
    List<ModuleDetailsDto> getModuleDetailsByStaffID(String staffID);
    void deleteModuleStaff(ModuleStaffCompositeKey targetKey);
}
