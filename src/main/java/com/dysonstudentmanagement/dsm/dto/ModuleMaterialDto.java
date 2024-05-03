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
ModuleMaterialDto

Defines fields/data that will be transmitted to/from the API client when requesting/sending moduleMaterial data.

Original Author: Tianpu Li 25/04/2024
 */
public class ModuleMaterialDto {
    private String moduleID;
    private int materialNumber;
    private String title;
    private String description;
    private boolean isPublished;
}
