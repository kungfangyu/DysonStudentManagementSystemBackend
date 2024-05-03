package com.dysonstudentmanagement.dsm.service.impl;

import com.dysonstudentmanagement.dsm.dto.PreviousQualificationDto;
import com.dysonstudentmanagement.dsm.entity.previousqualification.PreviousQualification;
import com.dysonstudentmanagement.dsm.entity.previousqualification.PreviousQualificationCompositeKey;
import com.dysonstudentmanagement.dsm.entity.user.UserPrimaryData;
import com.dysonstudentmanagement.dsm.exception.ResourceNotFoundException;
import com.dysonstudentmanagement.dsm.mapper.PreviousQualificationMapper;
import com.dysonstudentmanagement.dsm.repository.PreviousQualificationRepository;
import com.dysonstudentmanagement.dsm.repository.UserPrimaryDataRepository;
import com.dysonstudentmanagement.dsm.service.PreviousQualificationService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
/*
PreviousQualificationServiceImpl

Class that implements service methods that act upon PreviousQualification table in database

Original Author: Billy Peters 23/04/2024
 */
public class PreviousQualificationServiceImpl implements PreviousQualificationService {
    private PreviousQualificationRepository previousQualificationRepo;
    private UserPrimaryDataRepository userPrimaryDataRepo;

    @Override
    public PreviousQualificationDto createPreviousQualification(PreviousQualificationDto qualificationDto) {
        PreviousQualification previousQualification = PreviousQualificationMapper.mapToPreviousQualification(qualificationDto);
        PreviousQualificationCompositeKey newRecordCompositeKey = new PreviousQualificationCompositeKey(previousQualification.getUserID(),
                previousQualification.getQualificationLevel(),
                previousQualification.getSubject()
        );

        UserPrimaryData userPrimaryData = userPrimaryDataRepo.findById(previousQualification.getUserID())
                .orElseThrow(() -> new DataIntegrityViolationException("Failed...UserID does not exist in foreign key table 'UserPrimaryData'") //TODO: consider making custom @ResponseStatus exception (as with other custom made exception to better describe exception to api caller.
                );

        Optional<PreviousQualification> previousQualificationExists = previousQualificationRepo.findById(newRecordCompositeKey);
        if(previousQualificationExists.isPresent()){
            throw new DataIntegrityViolationException("Failed...Record already exists for given primary key");
        }else{
            previousQualification.setUserPrimaryData(userPrimaryData);
            PreviousQualification savedData = previousQualificationRepo.save(previousQualification);
            return PreviousQualificationMapper.mapToPreviousQualificationDto(savedData);
        }
    }

    @Override
    public PreviousQualificationDto getPreviousQualification(PreviousQualificationCompositeKey targetKey) {
        PreviousQualification qualification = previousQualificationRepo.findById(targetKey)
                .orElseThrow(() -> new ResourceNotFoundException("Previous Qualification record not found with primary key provided")
                );
        return PreviousQualificationMapper.mapToPreviousQualificationDto(qualification);
    }

    @Override
    public List<PreviousQualificationDto> getPreviousQualificationByUserID(String userID) {
        List<PreviousQualification> previousQualifications = previousQualificationRepo.findByUserID(userID);
        return previousQualifications.stream().map((previousQualification) -> PreviousQualificationMapper.mapToPreviousQualificationDto(previousQualification)).collect(Collectors.toList());
    }

    @Override
    public PreviousQualificationDto updatePreviousQualification(PreviousQualificationCompositeKey targetKey, PreviousQualificationDto updatedQualificationDto) {
        PreviousQualification updatedQualification = PreviousQualificationMapper.mapToPreviousQualification(updatedQualificationDto);
        PreviousQualification qualification = previousQualificationRepo.findById(targetKey)
                .orElseThrow(() -> new ResourceNotFoundException("Previous Qualification record not found with primary key provided")
                );
        qualification.setGrade(updatedQualification.getGrade());
        qualification.setDateAchieved(updatedQualification.getDateAchieved());
        qualification.setInstitution(updatedQualification.getInstitution());
        PreviousQualification savedData = previousQualificationRepo.save(qualification);
        return PreviousQualificationMapper.mapToPreviousQualificationDto(savedData);
    }

    @Override
    public void deletePreviousQualification(PreviousQualificationCompositeKey targetKey) {
        PreviousQualification qualification = previousQualificationRepo.findById(targetKey)
                .orElseThrow(() -> new ResourceNotFoundException("Previous Qualification record not found with primary key provided")
                );
        previousQualificationRepo.deleteById(targetKey);

    }
}
