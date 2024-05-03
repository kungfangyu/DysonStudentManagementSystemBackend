package com.dysonstudentmanagement.dsm.service;


import com.dysonstudentmanagement.dsm.dto.ProgrammeStaffDto;
import com.dysonstudentmanagement.dsm.entity.programmestaff.ProgrammeStaffCompositeKey;


import java.util.List;

/*
ProgrammeStaffService

Interface that declares service methods that act upon ProgrammeStaff table in database

Original Author: Imran Matloob 24/04/2024
 */
public interface ProgrammeStaffService {


        ProgrammeStaffDto createProgrammeStaff(ProgrammeStaffDto programmeStaffDto);

        List<ProgrammeStaffDto> getAllProgrammeStaff();

        ProgrammeStaffDto getProgrammeStaff(ProgrammeStaffCompositeKey targetKey);

        List<ProgrammeStaffDto> getProgrammeStaffByStaffID(String staffID);

        List<ProgrammeStaffDto> getProgrammeStaffDtoByProgrammeID(String ProgrammeID);

        ProgrammeStaffDto updateProgrammeStaff(ProgrammeStaffCompositeKey targetKey, ProgrammeStaffDto updatedProgrammeStaffDto);

        void deleteProgrammeStaff(ProgrammeStaffCompositeKey targetKey);

}
