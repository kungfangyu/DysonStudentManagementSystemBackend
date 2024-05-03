package com.dysonstudentmanagement.dsm.service;

import com.dysonstudentmanagement.dsm.dto.ProgrammeDto;


import java.util.List;

/*
ProgrammeService

Interface that declares service methods that act upon Programme table in database

Original Author: Imran Matloob 24/04/2024
 */
public interface ProgrammeService {
    ProgrammeDto createProgramme(ProgrammeDto programmeDto);

    ProgrammeDto getProgramme(String programmeID);

    List<ProgrammeDto> getAllProgrammes();

    ProgrammeDto updateProgramme(String programmeID, ProgrammeDto updatedProgrammeDto);

    void deleteProgramme(String programmeID);
}

