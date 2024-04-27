package com.dysonstudentmanagement.dsm.service;

import com.dysonstudentmanagement.dsm.dto.programmeDto;

import java.util.List;

public interface ProgrammeService {
    programmeDto createProgramme(programmeDto programmeDto);

    programmeDto getProgramme(String programmeID);

    List<programmeDto> getAllProgrammes();

    programmeDto updateProgramme(String programmeID, programmeDto updatedProgrammeDto);

    void deleteProgramme(String programmeID);
}

