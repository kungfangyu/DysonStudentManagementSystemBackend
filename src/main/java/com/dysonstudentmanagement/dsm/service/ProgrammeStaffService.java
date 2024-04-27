package com.dysonstudentmanagement.dsm.service;

import com.dysonstudentmanagement.dsm.dto.ProgrammeStaffDto;
import com.dysonstudentmanagement.dsm.entity.ProgrammeStaff.ProgrammeStaffCompositeKey;

import java.util.List;

public interface ProgrammeStaffService {

        ProgrammeStaffDto createProgrammeStaff(ProgrammeStaffDto programmeStaffDto);

        List<ProgrammeStaffDto> getAllProgrammesStaff();

        ProgrammeStaffDto getProgrammeStaff(ProgrammeStaffCompositeKey targetKey);

        List<ProgrammeStaffDto> getProgrammeStaffByStaffID(String staffID);

        List<ProgrammeStaffDto> getProgrammeStaffDtoByProgrammeID(String ProgrammeID);

        ProgrammeStaffDto updateProgrammeStaff(ProgrammeStaffCompositeKey targetKey, ProgrammeStaffDto updatedProgrammeStaffDto);

        void deleteProgrammeStaff(ProgrammeStaffCompositeKey targetKey);
}
