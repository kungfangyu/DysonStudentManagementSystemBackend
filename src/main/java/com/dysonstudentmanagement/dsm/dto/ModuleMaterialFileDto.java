package com.dysonstudentmanagement.dsm.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class ModuleMaterialFileDto {
    private String moduleID;
    private int materialNumber;
    private int fileNumber;
    private String filePath;
}
