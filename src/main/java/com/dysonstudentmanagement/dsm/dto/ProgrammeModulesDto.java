package com.dysonstudentmanagement.dsm.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString

/*
ProgrammeModulesDto

Defines fields/data that will be transmitted to/from the API client when requesting/sending programmeModule data.

Original Author: Imran Matloob 24/04/2024
 */
public class ProgrammeModulesDto {
    private String moduleID;
    private String programmeID;
}
