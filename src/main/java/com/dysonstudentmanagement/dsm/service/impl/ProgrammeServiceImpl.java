package com.dysonstudentmanagement.dsm.service.impl;


import com.dysonstudentmanagement.dsm.dto.ProgrammeDto;
import com.dysonstudentmanagement.dsm.entity.programme.Programme;
import com.dysonstudentmanagement.dsm.exception.ResourceNotFoundException;
import com.dysonstudentmanagement.dsm.mapper.ProgrammeMapper;

import com.dysonstudentmanagement.dsm.repository.ProgrammeRepository;
import com.dysonstudentmanagement.dsm.service.ProgrammeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ProgrammeServiceImpl implements ProgrammeService {
    private ProgrammeRepository programmeRepository;

    @Override
    public ProgrammeDto createProgramme(ProgrammeDto programmeDto) {
        Programme programme = ProgrammeMapper.mapToProgramme(programmeDto);
        Programme savedProgramme = programmeRepository.save(programme);
        return ProgrammeMapper.mapToProgrammesDto(savedProgramme);
    }

    @Override
    public ProgrammeDto getProgramme(String programmeID) {
        Programme programme = programmeRepository.findById(programmeID)
                .orElseThrow(() -> new ResourceNotFoundException("programme not found with ID: " + programmeID));
        return ProgrammeMapper.mapToProgrammesDto(programme);
    }

    @Override
    public List<ProgrammeDto> getAllProgrammes() {
        List<Programme> programmes = programmeRepository.findAll();
        return programmes.stream().map(ProgrammeMapper::mapToProgrammesDto).collect(Collectors.toList());
    }

    @Override
    public ProgrammeDto updateProgramme(String programmeID, ProgrammeDto updatedProgrammeDto) {
        Programme existingProgramme = programmeRepository.findById(programmeID)
                .orElseThrow(() -> new ResourceNotFoundException("programme not found with ID: " + programmeID));

        // Update fields that are allowed to be updated
        existingProgramme.setName(updatedProgrammeDto.getName());
        existingProgramme.setStartDate(updatedProgrammeDto.getStartDate());
        existingProgramme.setEndDate(updatedProgrammeDto.getEndDate());
        existingProgramme.setDescription(updatedProgrammeDto.getDescription());
        existingProgramme.setTotalCredits(updatedProgrammeDto.getTotalCredits());
        existingProgramme.setGradesReleased(updatedProgrammeDto.isGradesReleased());


        Programme savedProgramme = programmeRepository.save(existingProgramme);
        return ProgrammeMapper.mapToProgrammesDto(savedProgramme);

    }

    @Override
    public void deleteProgramme(String programmeID) {

        Programme programme = programmeRepository.findById(programmeID)
                .orElseThrow(() -> new ResourceNotFoundException("programme not found with ID: " + programmeID));
        programmeRepository.delete(programme);
    }
}
