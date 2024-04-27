package com.dysonstudentmanagement.dsm.service;

import com.dysonstudentmanagement.dsm.dto.CourseworkDto;
import com.dysonstudentmanagement.dsm.entity.coursework.CourseworkCompositeKey;

import java.util.List;

/*
    Interface: CourseworkService
    Interface which sets out the required API methods to be implemented.
    Original Author: Jack Burnett, 27/04/2024
*/
public interface CourseworkService {
    CourseworkDto createCoursework(CourseworkDto moduleDetailsDto);

    CourseworkDto getCoursework(CourseworkCompositeKey targetKey);

    List<CourseworkDto> getCourseworkByModuleID(String moduleID);

    CourseworkDto updateCoursework(CourseworkCompositeKey targetKey, CourseworkDto updatedAnnouncementDto);

    void deleteCoursework(CourseworkCompositeKey targetKey);
}
