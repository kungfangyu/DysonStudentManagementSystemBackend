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
public class ModuleDetailsDto {
    private String moduleID;
    private String moduleName;
    private String modulePhoto;
    private LocalDate startDate;
    private LocalDate endDate;
    private int moduleCredits;
}
