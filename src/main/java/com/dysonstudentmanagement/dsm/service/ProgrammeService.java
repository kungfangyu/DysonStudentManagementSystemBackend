package com.dysonstudentmanagement.dsm.service;

import com.dysonstudentmanagement.dsm.dto.ProgrammeDto;

import java.util.List;

public interface ProgrammeService {
    ProgrammeDto createProgramme(ProgrammeDto programmeDto);

    ProgrammeDto getProgramme(String programmeID);

    List<ProgrammeDto> getAllProgrammes();

    ProgrammeDto updateProgramme(String programmeID, ProgrammeDto updatedProgrammeDto);

    void deleteProgramme(String programmeID);
}

