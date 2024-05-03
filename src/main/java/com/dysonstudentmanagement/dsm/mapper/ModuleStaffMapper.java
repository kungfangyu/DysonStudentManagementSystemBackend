package com.dysonstudentmanagement.dsm.mapper;

import com.dysonstudentmanagement.dsm.dto.ModuleStaffDto;
import com.dysonstudentmanagement.dsm.entity.modulestaff.ModuleStaff;

/*
    Class: ModuleStaffMapper
    Mapper class responsible for mapping between ModuleStaff and ModuleStaffDto objects.
    Original Author: Tianpu Li 25/04/2024
 */
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