package com.dysonstudentmanagement.dsm.service.impl;

import com.dysonstudentmanagement.dsm.dto.programmeStaffDto;
import com.dysonstudentmanagement.dsm.entity.Programme.programme;
import com.dysonstudentmanagement.dsm.entity.ProgrammeStaff.programmeStaff;
import com.dysonstudentmanagement.dsm.entity.ProgrammeStaff.programmeStaffCompositeKey;
import com.dysonstudentmanagement.dsm.entity.user.UserPrimaryData;
import com.dysonstudentmanagement.dsm.exception.ResourceNotFoundException;
import com.dysonstudentmanagement.dsm.mapper.programmeStaffMapper;
import com.dysonstudentmanagement.dsm.repository.programmeRepository;
import com.dysonstudentmanagement.dsm.repository.UserPrimaryDataRepository;
import com.dysonstudentmanagement.dsm.service.ProgrammeStaffService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@AllArgsConstructor
@Service
public class ProgrammeStaffServiveImpl implements ProgrammeStaffService {

    private com.dysonstudentmanagement.dsm.repository.programmeStaffRepository programmeStaffRepository;

    private programmeRepository programmeRepo;

    private UserPrimaryDataRepository userPrimaryDataRepo;


    @Override
    public programmeStaffDto createProgrammeStaff(programmeStaffDto programmeStaffDto) {
        programmeStaff programmeStaff = programmeStaffMapper.mapToProgrammeStaff(programmeStaffDto);

        UserPrimaryData staffData = userPrimaryDataRepo.findById(programmeStaff.getStaffID())
                .orElseThrow(() -> new DataIntegrityViolationException("Failed...StudentID does not exist in foreign key table 'UserPrimaryData'"));
        programmeStaff.setStaffPrimaryData(staffData);

        programme programme = programmeRepo.findById(programmeStaff.getProgrammeID())
                .orElseThrow(() -> new DataIntegrityViolationException("Failed...ProgrammeID does not exist in foreign key table 'programme"));
        programmeStaff.setProgramme(programme);

        programmeStaffCompositeKey targetKey = new programmeStaffCompositeKey(programmeStaff.getProgrammeID(), programmeStaff.getStaffID());

        Optional<programmeStaff> programmeStaffExists = programmeStaffRepository.findById(targetKey);

        if(programmeStaffExists.isPresent()){
            throw new DataIntegrityViolationException("Failed...Record already exists for given primary key");
        } else {
            programmeStaff savedData = programmeStaffRepository.save(programmeStaff);
            return programmeStaffMapper.mapToProgrammeStaffDto(savedData);
        }
    }


    @Override
    public List<programmeStaffDto> getAllProgrammesStaff() {
        List<programmeStaff> programmes = programmeStaffRepository.findAll();
        return programmes.stream()
                .map(programmeStaffMapper::mapToProgrammeStaffDto)
                .collect(Collectors.toList());
    }


    @Override
    public programmeStaffDto getProgrammeStaff(programmeStaffCompositeKey targetKey) {
        programmeStaff programmeStaff = programmeStaffRepository.findById(targetKey)
                .orElseThrow(() -> new ResourceNotFoundException("programmeStaff record not found with provided key"));
        return programmeStaffMapper.mapToProgrammeStaffDto(programmeStaff);
    }

    @Override
    public List<programmeStaffDto> getProgrammeStaffByStaffID(String staffID) {
        List<programmeStaff> programmeStaffList = programmeStaffRepository.findByStaffID(staffID);
        return programmeStaffList.stream()
                .map(programmeStaffMapper::mapToProgrammeStaffDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<programmeStaffDto> getProgrammeStaffDtoByProgrammeID(String programmeID) {
        List<programmeStaff> programmeStaffList = programmeStaffRepository.findByProgrammeID(programmeID);
        return programmeStaffList.stream()
                .map(programmeStaffMapper::mapToProgrammeStaffDto)
                .collect(Collectors.toList());
    }

    @Override
    public programmeStaffDto updateProgrammeStaff(programmeStaffCompositeKey targetKey, programmeStaffDto updatedProgrammeStaffDto) {
        // Retrieve the existing programmeStaff entity by its composite key
        programmeStaff programmeStaff = programmeStaffRepository.findById(targetKey)
                .orElseThrow(() -> new ResourceNotFoundException("programmeStaff record not found with provided key"));

        programmeStaffRepository.delete(programmeStaff);

        com.dysonstudentmanagement.dsm.entity.ProgrammeStaff.programmeStaff newProgramStaff = new programmeStaff();
        // Update the fields of the existing programmeStaff entity with the data from the DTO
        newProgramStaff.setProgrammeID(updatedProgrammeStaffDto.getProgrammeID());
        newProgramStaff.setStaffID(updatedProgrammeStaffDto.getStaffID());

        // Retrieve the staff data to ensure it exists in the UserPrimaryData table
        UserPrimaryData staffData = userPrimaryDataRepo.findById(newProgramStaff.getStaffID())
                .orElseThrow(() -> new DataIntegrityViolationException("Failed...StaffID does not exist in foreign key table 'UserPrimaryData'"));
        programmeStaff.setStaffPrimaryData(staffData);

        // Retrieve the programme data to ensure it exists in the programme table
        programme programme = programmeRepo.findById(newProgramStaff.getProgrammeID())
                .orElseThrow(() -> new DataIntegrityViolationException("Failed...ProgrammeID does not exist in foreign key table 'programme'"));
        programmeStaff.setProgramme(programme);

        System.out.println(newProgramStaff);
        // Delete the existing programmeStaff entity

        // Save the updated programmeStaff entity

        com.dysonstudentmanagement.dsm.entity.ProgrammeStaff.programmeStaff savedData = programmeStaffRepository.save(newProgramStaff);

        // Map the saved programmeStaff entity to DTO and return it
        return programmeStaffMapper.mapToProgrammeStaffDto(savedData);
    }

    @Override
    public void deleteProgrammeStaff(programmeStaffCompositeKey targetKey) {
        programmeStaff programmeStaff = programmeStaffRepository.findById(targetKey)
                .orElseThrow(() -> new ResourceNotFoundException("programmeStaff record not found with provided key"));
        programmeStaffRepository.delete(programmeStaff);
    }

}
