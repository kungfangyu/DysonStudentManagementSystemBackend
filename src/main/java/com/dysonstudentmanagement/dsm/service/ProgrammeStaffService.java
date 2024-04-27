package com.dysonstudentmanagement.dsm.service;

import com.dysonstudentmanagement.dsm.dto.programmeStaffDto;
import com.dysonstudentmanagement.dsm.entity.ProgrammeStaff.programmeStaffCompositeKey;

import java.util.List;

public interface ProgrammeStaffService {

        programmeStaffDto createProgrammeStaff(programmeStaffDto programmeStaffDto);

        List<programmeStaffDto> getAllProgrammesStaff();

        programmeStaffDto getProgrammeStaff(programmeStaffCompositeKey targetKey);

        List<programmeStaffDto> getProgrammeStaffByStaffID(String staffID);

        List<programmeStaffDto> getProgrammeStaffDtoByProgrammeID(String ProgrammeID);

        programmeStaffDto updateProgrammeStaff(programmeStaffCompositeKey targetKey, programmeStaffDto updatedProgrammeStaffDto);

        void deleteProgrammeStaff(programmeStaffCompositeKey targetKey);
}
