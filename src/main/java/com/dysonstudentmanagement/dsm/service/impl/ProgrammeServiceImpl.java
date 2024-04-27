package com.dysonstudentmanagement.dsm.service.impl;

import com.dysonstudentmanagement.dsm.dto.programmeDto;
import com.dysonstudentmanagement.dsm.entity.Programme.programme;
import com.dysonstudentmanagement.dsm.exception.ResourceNotFoundException;
import com.dysonstudentmanagement.dsm.mapper.programmeMapper;

import com.dysonstudentmanagement.dsm.service.ProgrammeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ProgrammeServiceImpl implements ProgrammeService {
    private com.dysonstudentmanagement.dsm.repository.programmeRepository programmeRepository;

    @Override
    public programmeDto createProgramme(programmeDto programmeDto) {
        programme programme = programmeMapper.mapToProgramme(programmeDto);
        com.dysonstudentmanagement.dsm.entity.Programme.programme savedProgramme = programmeRepository.save(programme);
        return programmeMapper.mapToProgrammesDto(savedProgramme);
    }

    @Override
    public programmeDto getProgramme(String programmeID) {
        programme programme = programmeRepository.findById(programmeID)
                .orElseThrow(() -> new ResourceNotFoundException("programme not found with ID: " + programmeID));
        return programmeMapper.mapToProgrammesDto(programme);
    }

    @Override
    public List<programmeDto> getAllProgrammes() {
        List<programme> programmes = programmeRepository.findAll();
        return programmes.stream().map(programmeMapper::mapToProgrammesDto).collect(Collectors.toList());
    }

    @Override
    public programmeDto updateProgramme(String programmeID, programmeDto updatedProgrammeDto) {
        programme existingProgramme = programmeRepository.findById(programmeID)
                .orElseThrow(() -> new ResourceNotFoundException("programme not found with ID: " + programmeID));

        // Update fields that are allowed to be updated
        existingProgramme.setName(updatedProgrammeDto.getName());
        existingProgramme.setStartDate(updatedProgrammeDto.getStartDate());
        existingProgramme.setEndDate(updatedProgrammeDto.getEndDate());
        existingProgramme.setDescription(updatedProgrammeDto.getDescription());
        existingProgramme.setTotalCredits(updatedProgrammeDto.getTotalCredits());
        existingProgramme.setGradesReleased(updatedProgrammeDto.isGradesReleased());

        programme savedProgramme = programmeRepository.save(existingProgramme);
        return programmeMapper.mapToProgrammesDto(savedProgramme);
    }

    @Override
    public void deleteProgramme(String programmeID) {
        programme programme = programmeRepository.findById(programmeID)
                .orElseThrow(() -> new ResourceNotFoundException("programme not found with ID: " + programmeID));
        programmeRepository.delete(programme);
    }
}
