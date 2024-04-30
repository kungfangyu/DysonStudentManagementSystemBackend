package com.dysonstudentmanagement.dsm.service;

import com.dysonstudentmanagement.dsm.dto.CourseworkExtensionRequestDto;
import com.dysonstudentmanagement.dsm.entity.courseworkextensionrequest.CourseworkExtensionRequestCompositeKey;

import java.util.List;

/*
    Interface: CourseworkExtensionRequestService
    Interface which sets out the required API methods to be implemented.
    Original Author: Jack Burnett, 28/04/2024
*/
public interface CourseworkExtensionRequestService {
    CourseworkExtensionRequestDto createCourseworkExtensionRequest(CourseworkExtensionRequestDto courseworkExtensionRequestDto);

    CourseworkExtensionRequestDto getCourseworkExtensionRequest(CourseworkExtensionRequestCompositeKey targetKey);

    List<CourseworkExtensionRequestDto> getCourseworkExtensionRequests(String studentID, String moduleID, int courseworkID);

    CourseworkExtensionRequestDto updateCourseworkExtensionRequest(CourseworkExtensionRequestCompositeKey targetKey, CourseworkExtensionRequestDto updatedRequestDto);

    void deleteCourseworkExtensionRequest(CourseworkExtensionRequestCompositeKey targetKey);
}
