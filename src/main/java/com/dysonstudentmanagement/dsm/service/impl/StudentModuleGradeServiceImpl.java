package com.dysonstudentmanagement.dsm.service.impl;

import com.dysonstudentmanagement.dsm.dto.ModuleDetailsDto;
import com.dysonstudentmanagement.dsm.dto.StudentModuleGradeDto;
import com.dysonstudentmanagement.dsm.dto.StudentModuleInfoDto;
import com.dysonstudentmanagement.dsm.entity.moduledetails.ModuleDetails;
import com.dysonstudentmanagement.dsm.entity.studentmodulegrade.StudentModuleGrade;
import com.dysonstudentmanagement.dsm.entity.studentmodulegrade.StudentModuleGradeCompositeKey;
import com.dysonstudentmanagement.dsm.entity.user.UserPrimaryData;
import com.dysonstudentmanagement.dsm.mapper.ModuleDetailsMapper;
import com.dysonstudentmanagement.dsm.mapper.StudentModuleGradeMapper;
import com.dysonstudentmanagement.dsm.repository.ModuleDetailsRepository;
import com.dysonstudentmanagement.dsm.repository.StudentModuleGradeRepository;
import com.dysonstudentmanagement.dsm.repository.UserPrimaryDataRepository;
import com.dysonstudentmanagement.dsm.service.StudentModuleGradeService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
/*
StudentModuleGradeServiceImpl

Class that implements service methods that act upon StudentModuleGrade table in database

Original Author: Grace Liu 25/04/2024
 */
public class StudentModuleGradeServiceImpl implements StudentModuleGradeService {
    private ModuleDetailsRepository moduleDetailsRepository;
    private StudentModuleGradeRepository studentModuleGradeRepository;
    private UserPrimaryDataRepository primaryDataRepository;

    @Override
    public StudentModuleGradeDto createStudentModuleGrade(StudentModuleGradeDto studentModuleGradeDto) {
        StudentModuleGrade studentModuleGrade = StudentModuleGradeMapper.mapToStudentModuleGrade(studentModuleGradeDto);
        ModuleDetails moduleDetails = moduleDetailsRepository.findById(studentModuleGradeDto.getModuleID())
                .orElseThrow(() -> new DataIntegrityViolationException("Module Not Found"));
        UserPrimaryData student = primaryDataRepository.findById(studentModuleGradeDto.getStudentID())
                .orElseThrow(() -> new DataIntegrityViolationException("Student Not Found"));

        StudentModuleGradeCompositeKey pk = new StudentModuleGradeCompositeKey(studentModuleGradeDto.getStudentID(),
                studentModuleGradeDto.getModuleID());
        Optional<StudentModuleGrade> studentModuleGradeOptional = studentModuleGradeRepository.findById(pk);
        if (studentModuleGradeOptional.isPresent()) {
            throw new DataIntegrityViolationException("Student Module Grade Already Exists");
        }else {
            studentModuleGrade.setModuleDetails(moduleDetails);
            studentModuleGrade.setUserPrimaryData(student);
            StudentModuleGrade savedStudentModuleGrade = studentModuleGradeRepository.save(studentModuleGrade);
            return StudentModuleGradeMapper.mapToStudentModuleGradeDto(savedStudentModuleGrade);
        }
    }

    @Override
    public StudentModuleGradeDto getStudentModuleGrade(StudentModuleGradeCompositeKey studentModuleGradeCompositeKey) {
        StudentModuleGrade studentModuleGrade = studentModuleGradeRepository.findById(studentModuleGradeCompositeKey)
                .orElseThrow(() -> new DataIntegrityViolationException("Student Module Grade Not Found"));
        return StudentModuleGradeMapper.mapToStudentModuleGradeDto(studentModuleGrade);
    }

    @Override
    public List<StudentModuleGradeDto> getStudentModuleGradeByModuleID(String moduleID) {
        List<StudentModuleGrade> moduleGradeList = studentModuleGradeRepository.findByModuleID(moduleID);
        return moduleGradeList.stream().map(StudentModuleGradeMapper::mapToStudentModuleGradeDto).collect(Collectors.toList());
    }

    @Override
    public List<ModuleDetailsDto> getModuleDetailsByStudentID(String studentID) {
        List<StudentModuleGrade> studentModuleGrades = studentModuleGradeRepository.findByStudentID(studentID);
        List<ModuleDetails> moduleDetailsList = new LinkedList<>();
        for(StudentModuleGrade studentModuleGrade:studentModuleGrades){
            moduleDetailsList.add(studentModuleGrade.getModuleDetails());
        }

        return moduleDetailsList.stream().map(ModuleDetailsMapper::mapToModuleDetailsDto).collect(Collectors.toList());
    }

    @Override
    public List<StudentModuleInfoDto> getStudentModuleInfoByStudentID(String studentID) {
        List<StudentModuleGrade> studentModuleGrades = studentModuleGradeRepository.findByStudentID(studentID);
        if(!studentModuleGrades.isEmpty()){
            return studentModuleGrades.stream().map(this::MapperToStudentModuleInfoDto).collect(Collectors.toList());
        }else {
            return null;
        }
    }

    @Override
    public List<StudentModuleGradeDto> getStudentModuleGradeByStudentID(String studentID) {
        List<StudentModuleGrade> studentGradeList = studentModuleGradeRepository.findByStudentID(studentID);
        return studentGradeList.stream().map(StudentModuleGradeMapper::mapToStudentModuleGradeDto).collect(Collectors.toList());
    }

    @Override
    public StudentModuleGradeDto updateStudentModuleGrade(StudentModuleGradeCompositeKey studentModuleGradeCompositeKey, StudentModuleGradeDto studentModuleGradeDto) {
        StudentModuleGrade studentModuleGrade = studentModuleGradeRepository.findById(studentModuleGradeCompositeKey)
                .orElseThrow(() -> new DataIntegrityViolationException("Student Module Grade Not Found"));

        studentModuleGrade.setGrade(studentModuleGradeDto.getGrade());
        studentModuleGrade.setPercentageAttendance(studentModuleGrade.getPercentageAttendance());
        StudentModuleGrade savedStudentModuleGrade = studentModuleGradeRepository.save(studentModuleGrade);
        return StudentModuleGradeMapper.mapToStudentModuleGradeDto(savedStudentModuleGrade);
    }

    @Override
    public void deleteStudentModuleGrade(StudentModuleGradeCompositeKey studentModuleGradeCompositeKey) {
        StudentModuleGrade studentModuleGrade = studentModuleGradeRepository.findById(studentModuleGradeCompositeKey)
                .orElseThrow(() -> new DataIntegrityViolationException("Student Module Grade Not Found"));
        studentModuleGradeRepository.deleteById(studentModuleGradeCompositeKey);
    }

    private StudentModuleInfoDto MapperToStudentModuleInfoDto(StudentModuleGrade studentModuleGrade) {
        StudentModuleInfoDto studentModuleInfoDto = new StudentModuleInfoDto();

        studentModuleInfoDto.setStudentID(studentModuleGrade.getStudentID());
        studentModuleInfoDto.setStatus(studentModuleGrade.getStatus());
        studentModuleInfoDto.setGrade(studentModuleGrade.getGrade());
        studentModuleInfoDto.setModuleID(studentModuleGrade.getModuleID());

        ModuleDetails moduleDetails = moduleDetailsRepository.findById(studentModuleGrade.getModuleID()).orElse(null);
        if (moduleDetails != null) {
            studentModuleInfoDto.setModuleName(moduleDetails.getModuleName());
        }
        return studentModuleInfoDto;
    }


}
