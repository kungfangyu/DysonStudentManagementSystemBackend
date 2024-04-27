package com.dysonstudentmanagement.dsm.service;

import com.dysonstudentmanagement.dsm.dto.ModuleStaffDto;
import com.dysonstudentmanagement.dsm.entity.modulestaff.ModuleStaffCompositeKey;

import java.util.List;

public interface ModuelStaffService {
    ModuleStaffDto createModuleStaff(ModuleStaffDto moduleStaffDto);
    List<ModuleStaffDto> getStaffByModuleId(String moduleId);
    List<ModuleStaffDto> getModuleByStaffId(String staffId);
    void deleteModuleStaff(ModuleStaffCompositeKey targetKey);
}
