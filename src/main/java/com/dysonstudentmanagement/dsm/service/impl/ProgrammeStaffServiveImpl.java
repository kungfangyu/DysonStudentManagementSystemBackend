package com.dysonstudentmanagement.dsm.service.impl;

import com.dysonstudentmanagement.dsm.dto.ProgrammeStaffDto;
import com.dysonstudentmanagement.dsm.entity.programme.Programme;
import com.dysonstudentmanagement.dsm.entity.programmestaff.ProgrammeStaff;
import com.dysonstudentmanagement.dsm.entity.programmestaff.ProgrammeStaffCompositeKey;
import com.dysonstudentmanagement.dsm.entity.user.UserPrimaryData;
import com.dysonstudentmanagement.dsm.exception.ResourceNotFoundException;
import com.dysonstudentmanagement.dsm.mapper.ProgrammeStaffMapper;
import com.dysonstudentmanagement.dsm.repository.ProgrammeRepository;
import com.dysonstudentmanagement.dsm.repository.ProgrammeStaffRepository;
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

    private ProgrammeStaffRepository programmeStaffRepository;

    private ProgrammeRepository programmeRepo;

    private UserPrimaryDataRepository userPrimaryDataRepo;


    @Override
    public ProgrammeStaffDto createProgrammeStaff(ProgrammeStaffDto programmeStaffDto) {
        ProgrammeStaff programmeStaff = ProgrammeStaffMapper.mapToProgrammeStaff(programmeStaffDto);

        UserPrimaryData staffData = userPrimaryDataRepo.findById(programmeStaff.getStaffID())
                .orElseThrow(() -> new DataIntegrityViolationException("Failed...StudentID does not exist in foreign key table 'UserPrimaryData'"));
        programmeStaff.setStaffPrimaryData(staffData);

        Programme programme = programmeRepo.findById(programmeStaff.getProgrammeID())
                .orElseThrow(() -> new DataIntegrityViolationException("Failed...ProgrammeID does not exist in foreign key table 'programme"));
        programmeStaff.setProgramme(programme);

        ProgrammeStaffCompositeKey targetKey = new ProgrammeStaffCompositeKey(programmeStaff.getProgrammeID(), programmeStaff.getStaffID());

        Optional<ProgrammeStaff> programmeStaffExists = programmeStaffRepository.findById(targetKey);

        if(programmeStaffExists.isPresent()){
            throw new DataIntegrityViolationException("Failed...Record already exists for given primary key");
        } else {
            ProgrammeStaff savedData = programmeStaffRepository.save(programmeStaff);
            return ProgrammeStaffMapper.mapToProgrammeStaffDto(savedData);
        }
    }


    @Override
    public List<ProgrammeStaffDto> getAllProgrammesStaff() {
        List<ProgrammeStaff> programmes = programmeStaffRepository.findAll();
        return programmes.stream()
                .map(ProgrammeStaffMapper::mapToProgrammeStaffDto)
                .collect(Collectors.toList());
    }


    @Override
    public ProgrammeStaffDto getProgrammeStaff(ProgrammeStaffCompositeKey targetKey) {
        ProgrammeStaff programmeStaff = programmeStaffRepository.findById(targetKey)
                .orElseThrow(() -> new ResourceNotFoundException("programmeStaff record not found with provided key"));
        return ProgrammeStaffMapper.mapToProgrammeStaffDto(programmeStaff);
    }

    @Override
    public List<ProgrammeStaffDto> getProgrammeStaffByStaffID(String staffID) {
        List<ProgrammeStaff> programmeStaffList = programmeStaffRepository.findByStaffID(staffID);
        return programmeStaffList.stream()
                .map(ProgrammeStaffMapper::mapToProgrammeStaffDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProgrammeStaffDto> getProgrammeStaffDtoByProgrammeID(String programmeID) {
        List<ProgrammeStaff> programmeStaffList = programmeStaffRepository.findByProgrammeID(programmeID);
        return programmeStaffList.stream()
                .map(ProgrammeStaffMapper::mapToProgrammeStaffDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProgrammeStaffDto updateProgrammeStaff(ProgrammeStaffCompositeKey targetKey, ProgrammeStaffDto updatedProgrammeStaffDto) {
        // Retrieve the existing programmeStaff entity by its composite key
        ProgrammeStaff programmeStaff = programmeStaffRepository.findById(targetKey)
                .orElseThrow(() -> new ResourceNotFoundException("programmeStaff record not found with provided key"));

        programmeStaffRepository.delete(programmeStaff);

        ProgrammeStaff newProgramStaff = new ProgrammeStaff();
        // Update the fields of the existing programmeStaff entity with the data from the DTO
        newProgramStaff.setProgrammeID(updatedProgrammeStaffDto.getProgrammeID());
        newProgramStaff.setStaffID(updatedProgrammeStaffDto.getStaffID());

        // Retrieve the staff data to ensure it exists in the UserPrimaryData table
        UserPrimaryData staffData = userPrimaryDataRepo.findById(newProgramStaff.getStaffID())
                .orElseThrow(() -> new DataIntegrityViolationException("Failed...StaffID does not exist in foreign key table 'UserPrimaryData'"));
        programmeStaff.setStaffPrimaryData(staffData);

        // Retrieve the programme data to ensure it exists in the programme table
        Programme programme = programmeRepo.findById(newProgramStaff.getProgrammeID())
                .orElseThrow(() -> new DataIntegrityViolationException("Failed...ProgrammeID does not exist in foreign key table 'programme'"));
        programmeStaff.setProgramme(programme);

        System.out.println(newProgramStaff);
        // Delete the existing programmeStaff entity

        // Save the updated programmeStaff entity

        ProgrammeStaff savedData = programmeStaffRepository.save(newProgramStaff);

        // Map the saved programmeStaff entity to DTO and return it
        return ProgrammeStaffMapper.mapToProgrammeStaffDto(savedData);
    }

    @Override
    public void deleteProgrammeStaff(ProgrammeStaffCompositeKey targetKey) {
        ProgrammeStaff programmeStaff = programmeStaffRepository.findById(targetKey)
                .orElseThrow(() -> new ResourceNotFoundException("programmeStaff record not found with provided key"));
        programmeStaffRepository.delete(programmeStaff);
    }

}
