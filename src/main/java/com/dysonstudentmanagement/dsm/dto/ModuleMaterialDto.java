package com.dysonstudentmanagement.dsm.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class ModuleMaterialDto {
    private String moduleID;
    private int materialNumber;
    private String title;
    private String description;
    private boolean isPublished;
}
