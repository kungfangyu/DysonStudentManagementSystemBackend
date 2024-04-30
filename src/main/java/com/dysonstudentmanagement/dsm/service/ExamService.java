package com.dysonstudentmanagement.dsm.service;

import com.dysonstudentmanagement.dsm.dto.ExamDto;
import com.dysonstudentmanagement.dsm.dto.PreviousQualificationDto;
import com.dysonstudentmanagement.dsm.entity.exam.Exam;
import com.dysonstudentmanagement.dsm.entity.exam.ExamCompositeKey;
import com.dysonstudentmanagement.dsm.exception.ResourceNotFoundException;

import java.util.List;

/**
 * Service interface for managing exams.
 */
public interface ExamService {

    /**
     * Creating an Exam
     */
    ExamDto createExam(ExamDto examDto);

    ExamDto getExam(ExamCompositeKey targetKey);

    List<ExamDto> getExamByModuleID(String moduleID);

    ExamDto updateExam(ExamCompositeKey targetKey, ExamDto updateExamDto);

    void deleteExam(ExamCompositeKey targetKey);

}

//public interface StudentTutorService {
//    StudentTutorDto createStudentTutor(StudentTutorDto studentTutorDto);
//
//    StudentTutorDto createStudentTutorRandomAssign(String studentID);
//
//    StudentTutorDto getStudentTutor(StudentTutorCompositeKey targetKey);
//
//    List<StudentTutorDto> getStudentTutorByStaffID(String staffID);
//
//    List<StudentTutorDto> getStudentTutorByStudentID(String studentID); // On creation of method (24/04/2024) a student should only have one tutor, but this method provides a list in case requirements are changed in the future.
//
//    StudentTutorDto updateStudentTutor(StudentTutorCompositeKey targetKey, StudentTutorDto updatedStudentTutorDto);
//
//    void deleteStudentTutor(StudentTutorCompositeKey targetKey);
//}
