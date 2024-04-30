package com.dysonstudentmanagement.dsm.service.impl;

import com.dysonstudentmanagement.dsm.dto.StudentCourseworkGradeDto;
import com.dysonstudentmanagement.dsm.entity.moduleannouncement.ModuleAnnouncement;
import com.dysonstudentmanagement.dsm.entity.moduledetails.ModuleDetails;
import com.dysonstudentmanagement.dsm.entity.studentcourseworkgrade.StudentCourseworkGrade;
import com.dysonstudentmanagement.dsm.entity.studentcourseworkgrade.StudentCourseworkGradeCompositeKey;
import com.dysonstudentmanagement.dsm.entity.user.UserPrimaryData;
import com.dysonstudentmanagement.dsm.exception.ResourceNotFoundException;
import com.dysonstudentmanagement.dsm.mapper.ModuleAnnouncementMapper;
import com.dysonstudentmanagement.dsm.mapper.StudentCourseworkGradeMapper;
import com.dysonstudentmanagement.dsm.repository.ModuleDetailsRepository;
import com.dysonstudentmanagement.dsm.repository.StudentCourseworkGradeRepository;
import com.dysonstudentmanagement.dsm.repository.UserPrimaryDataRepository;
import com.dysonstudentmanagement.dsm.service.StudentCourseworkGradeService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class StudentCourseworkGradeServiceImpl implements StudentCourseworkGradeService {
    UserPrimaryDataRepository userPrimaryDataRepo;
    StudentCourseworkGradeRepository studentCourseworkGradeRepo;
    ModuleDetailsRepository moduleDetailsRepo;

    @Override
    public StudentCourseworkGradeDto createStudentCourseworkGrade(StudentCourseworkGradeDto studentCourseworkGradeDto) {
        StudentCourseworkGrade studentCourseworkGrade = StudentCourseworkGradeMapper.mapToStudentCourseworkGrade(studentCourseworkGradeDto);

        ModuleDetails moduleDetails = moduleDetailsRepo.findById(studentCourseworkGrade.getModuleID())
                .orElseThrow(() -> new DataIntegrityViolationException("Failed... ModuleID does not exist in target table StudentCourseworkGrade")
                );
        UserPrimaryData userPrimaryData = userPrimaryDataRepo.findById(studentCourseworkGrade.getStudentID())
                .orElseThrow(() -> new DataIntegrityViolationException("Failed... StudentID does not exist in target table StudentCourseworkGrade")
                );
        studentCourseworkGrade.setFeedback(studentCourseworkGradeDto.getFeedback());
        studentCourseworkGrade.setGrade(studentCourseworkGradeDto.getGrade());
        studentCourseworkGrade.setOnTime(studentCourseworkGradeDto.isOnTime());
        studentCourseworkGrade.setSubmissionFilePath(studentCourseworkGradeDto.getSubmissionFilePath());
        studentCourseworkGrade.setSubmissionTime(studentCourseworkGradeDto.getSubmissionTime());

        StudentCourseworkGrade savedData = studentCourseworkGradeRepo.save(studentCourseworkGrade);
        return StudentCourseworkGradeMapper.mapToStudentCourseworkGradeDto(savedData);
    }

    @Override
    public StudentCourseworkGradeDto getStudentCourseworkGrade(StudentCourseworkGradeCompositeKey targetKey) {
        StudentCourseworkGrade studentCourseworkGrade = studentCourseworkGradeRepo.findById(targetKey)
                .orElseThrow(() -> new ResourceNotFoundException("StudentsCourseworkGrade record not found with primary key provided")
                );
        return StudentCourseworkGradeMapper.mapToStudentCourseworkGradeDto(studentCourseworkGrade);
    }

    @Override
    public List<StudentCourseworkGradeDto> getStudentCourseGradeByModuleIdAndCourseworkID(String moduleID, int courseworkID) {
        List<StudentCourseworkGrade> studentCourseworkGrades = studentCourseworkGradeRepo.findByModuleIDAndCourseworkID(moduleID,courseworkID);
        return studentCourseworkGrades.stream().map(StudentCourseworkGradeMapper::mapToStudentCourseworkGradeDto).collect(Collectors.toList());
    }

    @Override
    public StudentCourseworkGradeDto updateStudentCourseworkGrade(StudentCourseworkGradeCompositeKey targetKey, StudentCourseworkGradeDto studentCourseworkGradeDto) {
        StudentCourseworkGrade updatedStudentCourseworkGrade = StudentCourseworkGradeMapper.mapToStudentCourseworkGrade(studentCourseworkGradeDto);
        StudentCourseworkGrade studentCourseworkGrade = studentCourseworkGradeRepo.findById(targetKey)
                .orElseThrow(() -> new ResourceNotFoundException("StudentCourseworkGrade record not found with primary key provided")
                );
        studentCourseworkGrade.setFeedback(studentCourseworkGradeDto.getFeedback());
        studentCourseworkGrade.setGrade(studentCourseworkGradeDto.getGrade());
        studentCourseworkGrade.setOnTime(studentCourseworkGradeDto.isOnTime());
        studentCourseworkGrade.setSubmissionFilePath(studentCourseworkGradeDto.getSubmissionFilePath());
        studentCourseworkGrade.setSubmissionTime(studentCourseworkGradeDto.getSubmissionTime());
        StudentCourseworkGrade savedData = studentCourseworkGradeRepo.save(studentCourseworkGrade);
        return StudentCourseworkGradeMapper.mapToStudentCourseworkGradeDto(savedData);
    }

    @Override
    public void deleteStudentCourseworkGrade(StudentCourseworkGradeCompositeKey targetKey) {
        StudentCourseworkGrade studentCourseworkGrade = studentCourseworkGradeRepo.findById(targetKey)
                .orElseThrow(() -> new ResourceNotFoundException("StudentCourseworkGrade record not found with primary key provided")
                );
        studentCourseworkGradeRepo.deleteById(targetKey);
    }
}
