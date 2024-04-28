package com.dysonstudentmanagement.dsm.service.impl;


import com.dysonstudentmanagement.dsm.dto.ExamDto;
import com.dysonstudentmanagement.dsm.entity.exam.Exam;
import com.dysonstudentmanagement.dsm.entity.exam.ExamCompositeKey;
import com.dysonstudentmanagement.dsm.entity.moduledetails.ModuleDetails;
import com.dysonstudentmanagement.dsm.exception.ResourceNotFoundException;
import com.dysonstudentmanagement.dsm.mapper.ExamMapper;
import com.dysonstudentmanagement.dsm.repository.ExamRepository;
import com.dysonstudentmanagement.dsm.repository.ModuleDetailsRepository;
import com.dysonstudentmanagement.dsm.service.ExamService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ExamServiceImpl implements ExamService {
    private ExamRepository examRepository;
    private ModuleDetailsRepository moduleDetailsRepo;

    @Override
    public ExamDto createExam(ExamDto examDto) {
        Exam exam = ExamMapper.mapToExam(examDto);
        ModuleDetails moduleDetails = moduleDetailsRepo.findById(exam.getModuleID())
                .orElseThrow(() -> new DataIntegrityViolationException("Failed... ModuleID does not exist in target table ModuleDetails")
                );
        exam.setModuleDetails(moduleDetails);
        Exam savedData = examRepository.save(exam);
        return ExamMapper.mapToExamDto(savedData);
    }

    @Override
    public ExamDto getExam(ExamCompositeKey targetKey) {
        Exam exam = examRepository.findById(targetKey)
                .orElseThrow(() -> new ResourceNotFoundException("Exam record not found with primary key provided")
                );
        return ExamMapper.mapToExamDto(exam);
    }

    @Override
    public List<ExamDto> getExamByModuleID(String moduleID) {
        List<Exam> exams= examRepository.findByModuleID(moduleID);
        return exams.stream().map(ExamMapper::mapToExamDto).collect(Collectors.toList());
    }

    @Override
    public ExamDto updateExam(ExamCompositeKey targetKey, ExamDto updateExamDto) {
        Exam updateExam = ExamMapper.mapToExam(updateExamDto);
        Exam exam = examRepository.findById(targetKey)
                .orElseThrow(() -> new ResourceNotFoundException("Exam record not found with primary key provided")
                );
        exam.setStartTime(updateExam.getStartTime());
        exam.setEndTime(updateExam.getEndTime());
        exam.setPercentageOfModule(updateExam.getPercentageOfModule());
        Exam savedData = examRepository.save(exam);
        return ExamMapper.mapToExamDto(savedData);
    }

    @Override
    public void deleteExam(ExamCompositeKey targetKey) {
        Exam exam = examRepository.findById(targetKey)
                .orElseThrow(() -> new ResourceNotFoundException("ExamID not exists!")
                );
        examRepository.deleteById(targetKey);
    }
}
