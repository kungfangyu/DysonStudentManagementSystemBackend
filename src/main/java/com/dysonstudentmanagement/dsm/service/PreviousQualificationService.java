package com.dysonstudentmanagement.dsm.service;

import com.dysonstudentmanagement.dsm.dto.PreviousQualificationDto;
import com.dysonstudentmanagement.dsm.entity.previousqualification.PreviousQualificationCompositeKey;

import java.util.List;

/*
PreviousQualificationService

Interface that declares service methods that act upon PreviousQualification table in database

Original Author: Billy Peters 23/04/2024
 */
public interface PreviousQualificationService {
    PreviousQualificationDto createPreviousQualification(PreviousQualificationDto qualificationDto);

    PreviousQualificationDto getPreviousQualification(PreviousQualificationCompositeKey targetKey);
    List<PreviousQualificationDto> getPreviousQualificationByUserID(String userID);

    PreviousQualificationDto updatePreviousQualification(PreviousQualificationCompositeKey targetKey, PreviousQualificationDto updatedQualificationDto);

    void deletePreviousQualification(PreviousQualificationCompositeKey targetKey);


}
