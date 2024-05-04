package com.dysonstudentmanagement.dsm.service.impl;

import com.dysonstudentmanagement.dsm.dto.AbsenceRequestDto;
import com.dysonstudentmanagement.dsm.entity.absencerequest.AbsenceRequest;
import com.dysonstudentmanagement.dsm.entity.absencerequest.AbsenceRequestCompositeKey;
import com.dysonstudentmanagement.dsm.entity.studentlessonallocation.StudentLessonAllocation;
import com.dysonstudentmanagement.dsm.entity.studentlessonallocation.StudentLessonAllocationCompositeKey;
import com.dysonstudentmanagement.dsm.exception.ResourceNotFoundException;
import com.dysonstudentmanagement.dsm.mapper.AbsenceRequestMapper;
import com.dysonstudentmanagement.dsm.repository.AbsenceRequestRepository;
import com.dysonstudentmanagement.dsm.repository.StudentLessonAllocationRepository;
import com.dysonstudentmanagement.dsm.service.AbsenceRequestService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
/*
AbsenceRequestService

Class that implements service methods that act upon AbsenceRequest table in database

Original Author: Billy Peters 04/05/2024
 */
public class AbsenceRequestServiceImpl implements AbsenceRequestService {
    private StudentLessonAllocationRepository studentLessonAllocationRepo;
    private AbsenceRequestRepository absenceRequestRepo;
    @Override
    public AbsenceRequestDto createAbsenceRequest(AbsenceRequestDto absenceRequestDto) {
        AbsenceRequest absenceRequest = AbsenceRequestMapper.mapToAbsenceRequest(absenceRequestDto);
        StudentLessonAllocation associatedStudentLessonAllocation = studentLessonAllocationRepo.findById(new StudentLessonAllocationCompositeKey(absenceRequest.getModuleID(),absenceRequest.getLessonID(),absenceRequest.getStudentID()))
                .orElseThrow(() -> new DataIntegrityViolationException("Failed... StudentLessonAllocation ID does not exist in target table StudentLessonAllocation")
                );
        absenceRequest.setStudentLessonAllocation(associatedStudentLessonAllocation);
        int requestID = absenceRequestRepo.findByModuleIDAndLessonIDAndStudentID(absenceRequest.getModuleID(),absenceRequest.getLessonID(),absenceRequest.getStudentID()).size()+1;
        absenceRequest.setRequestID(requestID); //manually assign requestID as hibernate does not support autoincrementing of one column in a composite key
        AbsenceRequest savedData = absenceRequestRepo.save(absenceRequest);
        return AbsenceRequestMapper.mapToAbsenceRequestDto(savedData);
    }

    @Override
    public AbsenceRequestDto getAbsenceRequest(AbsenceRequestCompositeKey targetKey) {
        AbsenceRequest absenceRequest = absenceRequestRepo.findById(targetKey)
                .orElseThrow(() -> new ResourceNotFoundException("AbsenceRequest record not found with primary key provided")
                );
        return AbsenceRequestMapper.mapToAbsenceRequestDto(absenceRequest);
    }

    @Override
    public List<AbsenceRequestDto> getAbsenceRequestByModuleID(String moduleID) {
        List<AbsenceRequest> absenceRequests = absenceRequestRepo.findByModuleID(moduleID);
        return absenceRequests.stream().map(AbsenceRequestMapper::mapToAbsenceRequestDto).collect(Collectors.toList());
    }

    @Override
    public List<AbsenceRequestDto> getAbsenceRequestByModuleIDAndLessonID(String moduleID, int lessonID) {
        List<AbsenceRequest> absenceRequests = absenceRequestRepo.findByModuleIDAndLessonID(moduleID,lessonID);
        return absenceRequests.stream().map(AbsenceRequestMapper::mapToAbsenceRequestDto).collect(Collectors.toList());
    }

    @Override
    public List<AbsenceRequestDto> getAbsenceRequestByStudentID(String studentID) {
        List<AbsenceRequest> absenceRequests = absenceRequestRepo.findByStudentID(studentID);
        return absenceRequests.stream().map(AbsenceRequestMapper::mapToAbsenceRequestDto).collect(Collectors.toList());
    }

    @Override
    public List<AbsenceRequestDto> getAbsenceRequestByModuleIDAndLessonIDAndStudentID(String moduleID, int lessonID, String studentID) {
        List<AbsenceRequest> absenceRequests = absenceRequestRepo.findByModuleIDAndLessonIDAndStudentID(moduleID,lessonID,studentID);
        return absenceRequests.stream().map(AbsenceRequestMapper::mapToAbsenceRequestDto).collect(Collectors.toList());
    }

    @Override
    public AbsenceRequestDto updateAbsenceRequest(AbsenceRequestCompositeKey targetKey, AbsenceRequestDto updateDto) {
        AbsenceRequest updateAbsenceRequest = AbsenceRequestMapper.mapToAbsenceRequest(updateDto);
        AbsenceRequest existingAbsenceRequest = absenceRequestRepo.findById(targetKey)
                .orElseThrow(() -> new ResourceNotFoundException("AbsenceRequest record not found with primary key provided")
                );
        existingAbsenceRequest.setRequestStatus(updateAbsenceRequest.getRequestStatus());
        AbsenceRequest savedData = absenceRequestRepo.save(existingAbsenceRequest);
        return AbsenceRequestMapper.mapToAbsenceRequestDto(savedData);
    }

    @Override
    public void deleteAbsenceRequest(AbsenceRequestCompositeKey targetKey) {
        AbsenceRequest existingAbsenceRequest = absenceRequestRepo.findById(targetKey)
                .orElseThrow(() -> new ResourceNotFoundException("AbsenceRequest record not found with primary key provided")
                );
        absenceRequestRepo.deleteById(targetKey);
    }
}
