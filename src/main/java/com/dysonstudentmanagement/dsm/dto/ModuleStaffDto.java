package com.dysonstudentmanagement.dsm.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
/*
ModuleStaffDto

Defines fields/data that will be transmitted to/from the API client when requesting/sending moduleStaff data.

Original Author: Tianpu Li 25/04/2024
 */
public class ModuleStaffDto {
    private String moduleID;
    private String staffID;
}
