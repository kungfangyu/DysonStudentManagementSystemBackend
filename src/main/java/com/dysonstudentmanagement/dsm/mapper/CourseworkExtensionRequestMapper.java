package com.dysonstudentmanagement.dsm.mapper;

import com.dysonstudentmanagement.dsm.dto.CourseworkExtensionRequestDto;
import com.dysonstudentmanagement.dsm.entity.courseworkextensionrequest.CourseworkExtensionRequest;

/*
    Class: CourseworkExtensionRequestMapper
    Standard data transfer object class to manage information sent in API requests.
    Original Author: Jack Burnett, 28/04/2024
*/
public class CourseworkExtensionRequestMapper {
    public static CourseworkExtensionRequestDto mapToCourseworkExtensionRequestDto (CourseworkExtensionRequest courseworkExtensionRequest) {
        return new CourseworkExtensionRequestDto(
                courseworkExtensionRequest.getStudentID(),
                courseworkExtensionRequest.getModuleID(),
                courseworkExtensionRequest.getCourseworkID(),
                courseworkExtensionRequest.getRequestNumber(),
                courseworkExtensionRequest.getRequestDate(),
                courseworkExtensionRequest.getRequestReason(),
                courseworkExtensionRequest.getStatus(),
                courseworkExtensionRequest.getAdjustedDeadline()
        );
    }
    public static CourseworkExtensionRequest mapToCourseworkExtensionRequest (CourseworkExtensionRequestDto courseworkExtensionRequestDto) {
        CourseworkExtensionRequest courseworkExtensionRequest = CourseworkExtensionRequest.builder()
                .studentID(courseworkExtensionRequestDto.getStudentID())
                .moduleID(courseworkExtensionRequestDto.getModuleID())
                .courseworkID(courseworkExtensionRequestDto.getCourseworkID())
                .requestNumber(courseworkExtensionRequestDto.getRequestNumber())
                .requestDate(courseworkExtensionRequestDto.getRequestDate())
                .requestReason(courseworkExtensionRequestDto.getRequestReason())
                .status(courseworkExtensionRequestDto.getStatus())
                .adjustedDeadline(courseworkExtensionRequestDto.getAdjustedDeadline())
                .build();
        return courseworkExtensionRequest;
    }
}
