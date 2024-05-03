package com.dysonstudentmanagement.dsm.service.impl;


import com.dysonstudentmanagement.dsm.dto.StudentExamGradeDto;
import com.dysonstudentmanagement.dsm.entity.exam.Exam;
import com.dysonstudentmanagement.dsm.entity.exam.ExamCompositeKey;
import com.dysonstudentmanagement.dsm.entity.studentexamgrade.StudentExamGrade;
import com.dysonstudentmanagement.dsm.entity.studentexamgrade.StudentExamGradeCompositeKey;
import com.dysonstudentmanagement.dsm.entity.user.UserPrimaryData;
import com.dysonstudentmanagement.dsm.exception.ResourceNotFoundException;
import com.dysonstudentmanagement.dsm.mapper.StudentExamGradeMapper;
import com.dysonstudentmanagement.dsm.repository.ExamRepository;
import com.dysonstudentmanagement.dsm.repository.StudentExamGradeRepository;
import com.dysonstudentmanagement.dsm.repository.UserPrimaryDataRepository;
import com.dysonstudentmanagement.dsm.service.StudentExamGradeService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
/*
StudentExamGradeServiceImpl

Class that implements service interface for managing student exam grades.

Original Author: Yijie Zhu 27/04/2024
Modifying Author: Billy Peters 28/04/2024 Implemented composite keys
 */
public class StudentExamGradeServiceImpl implements StudentExamGradeService {
    private StudentExamGradeRepository studentExamGradeRepository;
    private UserPrimaryDataRepository studentDataRepo;
    private ExamRepository examRepo;

    @Override
    public StudentExamGradeDto createStudentExamGrade(StudentExamGradeDto  studentExamGradeDto){
        StudentExamGrade studentExamGrade = StudentExamGradeMapper.mapToStudentExamGrade(studentExamGradeDto);

        Exam exam = examRepo.findById(new ExamCompositeKey(studentExamGrade.getModuleID(),studentExamGrade.getExamID()))
                .orElseThrow(() -> new DataIntegrityViolationException("Failed... Exam key does not exist in target table Exam")
                );
        studentExamGrade.setExam(exam);
        UserPrimaryData student = studentDataRepo.findById(studentExamGrade.getStudentID())
                .orElseThrow(() -> new DataIntegrityViolationException("Failed... Exam key does not exist in target table Exam")
                );
        studentExamGrade.setUserPrimaryData(student);
        StudentExamGrade savedData = studentExamGradeRepository.save(studentExamGrade);
        return StudentExamGradeMapper.mapToStudentExamGradeDto(savedData);
    }
    @Override
    public StudentExamGradeDto getStudentExamGrade(StudentExamGradeCompositeKey targetKey) {
        StudentExamGrade studentExamGrade = studentExamGradeRepository.findById(targetKey)
                .orElseThrow(() -> new ResourceNotFoundException("StudentExamGrade record not found with primary key provided")
                );
        return StudentExamGradeMapper.mapToStudentExamGradeDto(studentExamGrade);
    }

    @Override
    public List<StudentExamGradeDto> getStudentExamGradeByModuleIDAndExamID(String moduleID, int examID) {
        List<StudentExamGrade> studentExamGradeList = studentExamGradeRepository.findByModuleIDAndExamID(moduleID,examID);
        return studentExamGradeList.stream().map(StudentExamGradeMapper::mapToStudentExamGradeDto).collect(Collectors.toList());
    }

    @Override
    public StudentExamGradeDto updateStudentExamGrade(StudentExamGradeCompositeKey targetKey,StudentExamGradeDto updateStudentExamGradeDto) {
        StudentExamGrade studentExamGrade = studentExamGradeRepository.findById(targetKey)
                .orElseThrow(() -> new ResourceNotFoundException("StudentExamGrade with given key does not exist in tsble StudentExamGrade")
                );
        StudentExamGrade updateStudentExamGrade = StudentExamGradeMapper.mapToStudentExamGrade(updateStudentExamGradeDto);
        studentExamGrade.setGrade(updateStudentExamGrade.getGrade());
        StudentExamGrade savedData = studentExamGradeRepository.save(studentExamGrade);
        return StudentExamGradeMapper.mapToStudentExamGradeDto(savedData);
    }


    @Override
    public void deleteStudentExamGrade(StudentExamGradeCompositeKey targetKey) {
        studentExamGradeRepository.findById(targetKey)
                .orElseThrow(() -> new ResourceNotFoundException("StudentExamGrade with given key does not exist in tsble StudentExamGrade")
                );
        studentExamGradeRepository.deleteById(targetKey);
    }
}
