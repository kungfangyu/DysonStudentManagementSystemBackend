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
ModuleMaterialFileDto

Defines fields/data that will be transmitted to/from the API client when requesting/sending moduleMaterialFile data.

Original Author: Tianpu Li 25/04/2024
 */
public class ModuleMaterialFileDto {
    private String moduleID;
    private int materialNumber;
    private int fileNumber;
    private String filePath;
}
