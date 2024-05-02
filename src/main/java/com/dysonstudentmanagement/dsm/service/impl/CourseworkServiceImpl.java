package com.dysonstudentmanagement.dsm.service.impl;

import com.dysonstudentmanagement.dsm.dto.CourseworkDto;
import com.dysonstudentmanagement.dsm.entity.coursework.Coursework;
import com.dysonstudentmanagement.dsm.entity.coursework.CourseworkCompositeKey;
import com.dysonstudentmanagement.dsm.entity.moduledetails.ModuleDetails;
import com.dysonstudentmanagement.dsm.exception.ResourceNotFoundException;
import com.dysonstudentmanagement.dsm.mapper.CourseworkMapper;
import com.dysonstudentmanagement.dsm.repository.CourseworkRepository;
import com.dysonstudentmanagement.dsm.repository.ModuleDetailsRepository;
import com.dysonstudentmanagement.dsm.service.CourseworkService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/*
    Class: CourseworkServiceImpl
    Class is intended to handle data management in the database which respond to API methods.
    Original Author: Jack Burnett, 27/04/2024
*/
@AllArgsConstructor
@Service
public class CourseworkServiceImpl implements CourseworkService {
    private CourseworkRepository courseworkRepo;
    private ModuleDetailsRepository moduleDetailsRepo;

    @Override
    public CourseworkDto createCoursework(CourseworkDto courseworkDto) {
        Coursework coursework = CourseworkMapper.mapToCoursework(courseworkDto);

        //check for valid foreign key
        ModuleDetails moduleDetails = moduleDetailsRepo.findById(coursework.getModuleID())
                .orElseThrow(() -> new DataIntegrityViolationException("Failed... ModuleID does not exist in target table Coursework")
                );
        int courseworkID = courseworkRepo.findByModuleID(coursework.getModuleID()).size()+1; // manual incrementing of coursework id as hibernate does not support autoincrementing of composite keys columns
        coursework.setCourseworkID(courseworkID);

        coursework.setModuleDetails(moduleDetails);
        Coursework savedData = courseworkRepo.save(coursework);
        return CourseworkMapper.mapToCourseworkDto(savedData);
    }

    @Override
    public CourseworkDto getCoursework(CourseworkCompositeKey targetKey) {
        Coursework coursework = courseworkRepo.findById(targetKey)
                .orElseThrow(() -> new ResourceNotFoundException("Coursework record not found with primary key provided")
                );
        return CourseworkMapper.mapToCourseworkDto(coursework);
    }

    @Override
    public List<CourseworkDto> getCourseworkByModuleID(String moduleID) {
        List<Coursework> coursework = courseworkRepo.findByModuleID(moduleID);
        return coursework.stream().map(CourseworkMapper::mapToCourseworkDto).collect(Collectors.toList());
    }

    @Override
    public CourseworkDto updateCoursework(CourseworkCompositeKey targetKey, CourseworkDto updatedCourseworkDto) {
        Coursework updatedCoursework = CourseworkMapper.mapToCoursework(updatedCourseworkDto);
        Coursework coursework = courseworkRepo.findById(targetKey)
                .orElseThrow(() -> new ResourceNotFoundException("Coursework record not found with primary key provided")
                );
        coursework.setDescription(updatedCoursework.getDescription());
        coursework.setDeadline(updatedCoursework.getDeadline());
        coursework.setPercentageOfModule(updatedCoursework.getPercentageOfModule());
        coursework.setCourseworkPublished(updatedCoursework.isCourseworkPublished());
        coursework.setGradePublished(updatedCoursework.isGradePublished());
        Coursework savedData = courseworkRepo.save(coursework);
        return CourseworkMapper.mapToCourseworkDto(savedData);
    }

    @Override
    public void deleteCoursework(CourseworkCompositeKey targetKey) {
        Coursework coursework = courseworkRepo.findById(targetKey)
                .orElseThrow(() -> new ResourceNotFoundException("Coursework record not found with primary key provided")
                );
        courseworkRepo.deleteById(targetKey);
    }
}
