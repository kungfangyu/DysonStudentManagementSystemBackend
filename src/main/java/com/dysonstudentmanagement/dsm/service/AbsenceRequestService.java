package com.dysonstudentmanagement.dsm.service;

import com.dysonstudentmanagement.dsm.dto.AbsenceRequestDto;
import com.dysonstudentmanagement.dsm.entity.absencerequest.AbsenceRequestCompositeKey;

import java.util.List;

/*
AbsenceRequestService

Interface that declares service methods that act upon AbsenceRequest table in database

Original Author: Billy Peters 04/05/2024
 */
public interface AbsenceRequestService {
    AbsenceRequestDto createAbsenceRequest(AbsenceRequestDto absenceRequestDto);

    AbsenceRequestDto getAbsenceRequest(AbsenceRequestCompositeKey targetKey);

    List<AbsenceRequestDto> getAbsenceRequestByModuleID(String moduleID);

    List<AbsenceRequestDto> getAbsenceRequestByModuleIDAndLessonID(String moduleID, int lessonID);

    List<AbsenceRequestDto> getAbsenceRequestByStudentID(String studentID);

    List<AbsenceRequestDto> getAbsenceRequestByModuleIDAndLessonIDAndStudentID(String moduleID, int lessonID,String studentID);

    AbsenceRequestDto updateAbsenceRequest(AbsenceRequestCompositeKey targetKey, AbsenceRequestDto updateDto);

    void deleteAbsenceRequest(AbsenceRequestCompositeKey targetKey);
}
