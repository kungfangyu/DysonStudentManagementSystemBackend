package com.dysonstudentmanagement.dsm.mapper;

import com.dysonstudentmanagement.dsm.dto.PreviousQualificationDto;
import com.dysonstudentmanagement.dsm.entity.previousqualification.PreviousQualification;

public class PreviousQualificationMapper {
    public static PreviousQualificationDto mapToPreviousQualificationDto(PreviousQualification previousQualification){
        return new PreviousQualificationDto(
                previousQualification.getUserID(),
                previousQualification.getQualificationLevel(),
                previousQualification.getSubject(),
                previousQualification.getGrade(),
                previousQualification.getDateAchieved(),
                previousQualification.getInstitution()
        );
    }

    public static PreviousQualification mapToPreviousQualification(PreviousQualificationDto previousQualificationDto){
        PreviousQualification previousQualification = PreviousQualification.builder()
                .userID(previousQualificationDto.getUserID())
                .qualificationLevel(previousQualificationDto.getQualificationLevel())
                .subject(previousQualificationDto.getSubject())
                .grade(previousQualificationDto.getGrade())
                .dateAchieved(previousQualificationDto.getDateAchieved())
                .institution(previousQualificationDto.getInstitution())
                .build();
        return previousQualification;
    }
}
