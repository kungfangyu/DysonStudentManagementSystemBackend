package com.dysonstudentmanagement.dsm.service.impl;

import com.dysonstudentmanagement.dsm.dto.CourseworkExtensionRequestDto;
import com.dysonstudentmanagement.dsm.entity.coursework.Coursework;
import com.dysonstudentmanagement.dsm.entity.coursework.CourseworkCompositeKey;
import com.dysonstudentmanagement.dsm.entity.courseworkextensionrequest.CourseworkExtensionRequest;
import com.dysonstudentmanagement.dsm.entity.courseworkextensionrequest.CourseworkExtensionRequestCompositeKey;
import com.dysonstudentmanagement.dsm.exception.ResourceNotFoundException;
import com.dysonstudentmanagement.dsm.mapper.CourseworkExtensionRequestMapper;
import com.dysonstudentmanagement.dsm.repository.CourseworkExtensionRequestRepository;
import com.dysonstudentmanagement.dsm.repository.CourseworkRepository;
import com.dysonstudentmanagement.dsm.service.CourseworkExtensionRequestService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CourseworkExtensionRequestServiceImpl implements CourseworkExtensionRequestService {
    private CourseworkRepository courseworkRepo;
    private CourseworkExtensionRequestRepository courseworkExtensionRequestRepo;

    @Override
    public CourseworkExtensionRequestDto createCourseworkExtensionRequest(CourseworkExtensionRequestDto courseworkExtensionRequestDto) {
        CourseworkExtensionRequest courseworkExtensionRequest = CourseworkExtensionRequestMapper.mapToCourseworkExtensionRequest(courseworkExtensionRequestDto);
        Coursework coursework = courseworkRepo.findById(new CourseworkCompositeKey(
            courseworkExtensionRequest.getModuleID(),
            courseworkExtensionRequestDto.getCourseworkID()
        )).orElseThrow(() -> new DataIntegrityViolationException("Failed... CourseworkID does not exist in target table Coursework"));

        courseworkExtensionRequest.setStudentID(courseworkExtensionRequestDto.getStudentID());
        courseworkExtensionRequest.setModuleID(courseworkExtensionRequestDto.getModuleID());
        courseworkExtensionRequest.setCourseworkID(courseworkExtensionRequestDto.getCourseworkID());
        courseworkExtensionRequest.setRequestNumber(courseworkExtensionRequestDto.getRequestNumber());
        courseworkExtensionRequest.setRequestDate(courseworkExtensionRequestDto.getRequestDate());
        courseworkExtensionRequest.setRequestReason(courseworkExtensionRequestDto.getRequestReason());
        courseworkExtensionRequest.setStatus(courseworkExtensionRequestDto.getStatus());
        courseworkExtensionRequest.setAdjustedDeadline(courseworkExtensionRequestDto.getAdjustedDeadline());

        CourseworkExtensionRequest savedData = courseworkExtensionRequestRepo.save(courseworkExtensionRequest);
        return CourseworkExtensionRequestMapper.mapToCourseworkExtensionRequestDto(savedData);
    }

    @Override
    public CourseworkExtensionRequestDto getCourseworkExtensionRequest(CourseworkExtensionRequestCompositeKey targetKey) {
        CourseworkExtensionRequest courseworkExtensionRequest = courseworkExtensionRequestRepo.findById(targetKey)
                .orElseThrow(() -> new ResourceNotFoundException("CourseworkExtensionRequest record not found with primary key provided")
                );
        return CourseworkExtensionRequestMapper.mapToCourseworkExtensionRequestDto(courseworkExtensionRequest);
    }

    @Override
    public List<CourseworkExtensionRequestDto> getCourseworkExtensionRequests(String studentID, String moduleID, int courseworkID) {
        List<CourseworkExtensionRequest> extensionRequests = courseworkExtensionRequestRepo.findByStudentIDAndModuleIDAndCourseworkID(studentID, moduleID, courseworkID);
        return extensionRequests.stream().map(CourseworkExtensionRequestMapper::mapToCourseworkExtensionRequestDto).collect(Collectors.toList());
    }

    @Override
    public CourseworkExtensionRequestDto updateCourseworkExtensionRequest(CourseworkExtensionRequestCompositeKey targetKey, CourseworkExtensionRequestDto updatedRequestDto) {
        CourseworkExtensionRequest updatedCourseworkExtensionRequest = CourseworkExtensionRequestMapper.mapToCourseworkExtensionRequest(updatedRequestDto);
        CourseworkExtensionRequest courseworkExtensionRequest = courseworkExtensionRequestRepo.findById(targetKey)
                .orElseThrow(() -> new ResourceNotFoundException("CourseworkExtensionRequest record not found with primary key provided")
                );
        courseworkExtensionRequest.setRequestNumber(updatedRequestDto.getRequestNumber());
        courseworkExtensionRequest.setRequestDate(updatedRequestDto.getRequestDate());
        courseworkExtensionRequest.setRequestReason(updatedRequestDto.getRequestReason());
        courseworkExtensionRequest.setStatus(updatedRequestDto.getStatus());
        courseworkExtensionRequest.setAdjustedDeadline(updatedRequestDto.getAdjustedDeadline());

        CourseworkExtensionRequest savedData = courseworkExtensionRequestRepo.save(courseworkExtensionRequest);
        return CourseworkExtensionRequestMapper.mapToCourseworkExtensionRequestDto(savedData);
    }

    @Override
    public void deleteCourseworkExtensionRequest(CourseworkExtensionRequestCompositeKey targetKey) {
        CourseworkExtensionRequest courseworkExtensionRequest = courseworkExtensionRequestRepo.findById(targetKey)
                .orElseThrow(() -> new ResourceNotFoundException("CourseworkExtensionRequest record not found with primary key provided")
                );
        courseworkExtensionRequestRepo.deleteById(targetKey);
    }
}
