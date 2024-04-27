package com.dysonstudentmanagement.dsm.mapper;

import com.dysonstudentmanagement.dsm.dto.ModuleStaffDto;
import com.dysonstudentmanagement.dsm.entity.modulestaff.ModuleStaff;

public class ModuleStaffMapper {

    public static ModuleStaffDto mapToModuleStaffDto(ModuleStaff moduleStaff) {
        return new ModuleStaffDto(
                moduleStaff.getModuleID(),
                moduleStaff.getStaffID()
        );
    }

    public static ModuleStaff mapToModuleStaff(ModuleStaffDto moduleStaffDto) {
        return ModuleStaff.builder()
                .moduleID(moduleStaffDto.getModuleID())
                .staffID(moduleStaffDto.getStaffID())
                .build();
    }
}