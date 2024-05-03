package com.dysonstudentmanagement.dsm.dto;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@ToString
/*
ModuleDetailsDto

Defines fields/data that will be transmitted to/from the API client when requesting/sending moduleDetails data.

Original Author: Billy Peters 24/04/2024
 */
public class ModuleDetailsDto {
    private String moduleID;
    private String moduleName;
    private String modulePhoto;
    private String moduleDescription;
    private LocalDate startDate;
    private LocalDate endDate;
    private int moduleCredits;
}
