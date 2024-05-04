package com.dysonstudentmanagement.dsm.mapper;

import com.dysonstudentmanagement.dsm.dto.AbsenceRequestDto;
import com.dysonstudentmanagement.dsm.entity.absencerequest.AbsenceRequest;

/*
    Class: AbsenceRequestMapper
    Mapper class responsible for mapping between AbsenceRequest and AbsenceRequestDto objects.
    Original Author: Billy Peters 04/05/2024
 */
public class AbsenceRequestMapper {
    public static AbsenceRequestDto mapToAbsenceRequestDto(AbsenceRequest absenceRequest){
        return new AbsenceRequestDto(
                absenceRequest.getModuleID(),
                absenceRequest.getLessonID(),
                absenceRequest.getStudentID(),
                absenceRequest.getRequestID(),
                absenceRequest.getRequestReason(),
                absenceRequest.getRequestStatus()
        );
    }

    public static AbsenceRequest mapToAbsenceRequest(AbsenceRequestDto absenceRequestDto){
        AbsenceRequest absenceRequest = AbsenceRequest.builder()
                .moduleID(absenceRequestDto.getModuleID())
                .lessonID(absenceRequestDto.getLessonID())
                .studentID(absenceRequestDto.getStudentID())
                .requestID(absenceRequestDto.getRequestID())
                .requestReason(absenceRequestDto.getRequestReason())
                .requestStatus(absenceRequestDto.getRequestStatus())
                .build();
        return absenceRequest;
    }
}
