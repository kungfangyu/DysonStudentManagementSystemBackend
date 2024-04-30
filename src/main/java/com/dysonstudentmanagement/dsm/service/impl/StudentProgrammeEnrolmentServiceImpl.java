package com.dysonstudentmanagement.dsm.service.impl;

import com.dysonstudentmanagement.dsm.dto.StudentProgrammeEnrolmentDto;
import com.dysonstudentmanagement.dsm.entity.studentprogrammeenrolment.StudentProgrammeEnrolment;
import com.dysonstudentmanagement.dsm.entity.studentprogrammeenrolment.StudentProgrammeEnrolmentCompositeKey;
import com.dysonstudentmanagement.dsm.entity.user.UserPrimaryData;
import com.dysonstudentmanagement.dsm.exception.ResourceNotFoundException;
import com.dysonstudentmanagement.dsm.mapper.StudentProgrammeEnrolmentMapper;
import com.dysonstudentmanagement.dsm.repository.StudentProgrammeEnrolmentRepository;
import com.dysonstudentmanagement.dsm.repository.UserPrimaryDataRepository;
import com.dysonstudentmanagement.dsm.repository.ProgrammeRepository;
import com.dysonstudentmanagement.dsm.service.StudentProgrammeEnrolmentService;
import com.dysonstudentmanagement.dsm.entity.programme.*;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class StudentProgrammeEnrolmentServiceImpl implements StudentProgrammeEnrolmentService {

    private ProgrammeRepository programmeRepo;

    private UserPrimaryDataRepository userPrimaryDataRepo;

    private StudentProgrammeEnrolmentRepository studentProgrammeEnrolmentRepository;



    @Override
    public StudentProgrammeEnrolmentDto createStudentProgrammeEnrolment(StudentProgrammeEnrolmentDto studentProgrammeEnrolmentDto) {

        StudentProgrammeEnrolment enrolment = StudentProgrammeEnrolmentMapper.mapToStudentProgrammeEnrolment(studentProgrammeEnrolmentDto);

        // Check if StudentID exists
        UserPrimaryData studentData = userPrimaryDataRepo.findById(enrolment.getStudentID())
                .orElseThrow(() -> new DataIntegrityViolationException("Failed...StudentID does not exist in foreign key table 'UserPrimaryData'"));
        enrolment.setStudentPrimaryData(studentData);

        // Check if ProgrammeID exists
        Programme programme = programmeRepo.findById(enrolment.getProgrammeID())
                .orElseThrow(() -> new DataIntegrityViolationException("Failed...ProgrammeID does not exist in foreign key table 'Programme'"));
        enrolment.setProgramme(programme);

        // Check if the enrolment already exists
        StudentProgrammeEnrolmentCompositeKey targetKey = new StudentProgrammeEnrolmentCompositeKey(enrolment.getStudentID(), enrolment.getProgrammeID());
        Optional<StudentProgrammeEnrolment> existingEnrolment = studentProgrammeEnrolmentRepository.findById(targetKey);
        if (existingEnrolment.isPresent()) {
            throw new DataIntegrityViolationException("Failed...Record already exists for given primary key");
        }

        // Save the enrolment
        StudentProgrammeEnrolment savedEnrolment = studentProgrammeEnrolmentRepository.save(enrolment);

        // Mapping the saved enrolment back to DTO and returning it
        return StudentProgrammeEnrolmentMapper.mapToStudentProgrammeEnrolmentDto(savedEnrolment);
    }

    @Override
    public List<StudentProgrammeEnrolmentDto> getAllStudentProgrammeEnrolment() {
        List<StudentProgrammeEnrolment> studentProgrammes = studentProgrammeEnrolmentRepository.findAll();
        return studentProgrammes.stream()
                .map(StudentProgrammeEnrolmentMapper::mapToStudentProgrammeEnrolmentDto) // Fixed method reference
                .collect(Collectors.toList());
    }

    @Override
    public StudentProgrammeEnrolmentDto getStudentProgrammeEnrolmentDto(StudentProgrammeEnrolmentCompositeKey targetKey) {
        Optional<StudentProgrammeEnrolment> studentProgrammeOptional = studentProgrammeEnrolmentRepository.findById(targetKey);
        if (studentProgrammeOptional.isPresent()) {
            return StudentProgrammeEnrolmentMapper.mapToStudentProgrammeEnrolmentDto(studentProgrammeOptional.get());
        } else {
            throw new ResourceNotFoundException("Student Programme Enrolment not found with provided key");
        }
    }


    @Override
    public List<StudentProgrammeEnrolmentDto> getStudentProgrammeEnrolmentDtoByStudentID(String studentID) {
        List<StudentProgrammeEnrolment> StudentIDList = studentProgrammeEnrolmentRepository.findByStudentID(studentID);
        return StudentIDList.stream()
                .map(StudentProgrammeEnrolmentMapper::mapToStudentProgrammeEnrolmentDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentProgrammeEnrolmentDto> getStudentProgrammeEnrolmentDtoByProgrammeID(String programmeID) {
        List<StudentProgrammeEnrolment> StudentProgrammeIDList = studentProgrammeEnrolmentRepository.findByProgrammeID(programmeID);
        return StudentProgrammeIDList.stream()
                .map(StudentProgrammeEnrolmentMapper::mapToStudentProgrammeEnrolmentDto)
                .collect(Collectors.toList());
    }


    @Override
    public StudentProgrammeEnrolmentDto updateStudentProgrammeEnrolment(StudentProgrammeEnrolmentCompositeKey targetKey, StudentProgrammeEnrolmentDto updateStudentProgrammeEnrolmentDto) {
        StudentProgrammeEnrolment studentProgrammeEnrolment = studentProgrammeEnrolmentRepository.findById(targetKey)
                .orElseThrow(() -> new ResourceNotFoundException("Student Programme Enrolment record not found with provided key"));

        studentProgrammeEnrolmentRepository.delete(studentProgrammeEnrolment);

        StudentProgrammeEnrolment NewStudentProgrammeEnrolment = new StudentProgrammeEnrolment();
        NewStudentProgrammeEnrolment.setProgrammeID(updateStudentProgrammeEnrolmentDto.getProgrammeID());
        NewStudentProgrammeEnrolment.setStudentID(updateStudentProgrammeEnrolmentDto.getStudentID());
        NewStudentProgrammeEnrolment.setDateEnrolled(updateStudentProgrammeEnrolmentDto.getDateEnrolled());
        NewStudentProgrammeEnrolment.setDateCompleted(updateStudentProgrammeEnrolmentDto.getDateCompleted());
        NewStudentProgrammeEnrolment.setFinalGrade(updateStudentProgrammeEnrolmentDto.getFinalGrade());
        NewStudentProgrammeEnrolment.setStatus(updateStudentProgrammeEnrolmentDto.getStatus());

        Programme programme = programmeRepo.findById(NewStudentProgrammeEnrolment.getProgrammeID())
                .orElseThrow(() -> new DataIntegrityViolationException("Failed...ProgrammeID does not exist in foreign key table 'programme'"));
                NewStudentProgrammeEnrolment.setProgramme(programme);

        UserPrimaryData studentID = userPrimaryDataRepo.findById(NewStudentProgrammeEnrolment.getStudentID())
                .orElseThrow(() -> new DataIntegrityViolationException("Failed...StudentID does not exist in foreign key table 'User'"));
        NewStudentProgrammeEnrolment.setStudentPrimaryData(studentID);

        StudentProgrammeEnrolment savedData = studentProgrammeEnrolmentRepository.save(NewStudentProgrammeEnrolment);

        return StudentProgrammeEnrolmentMapper.mapToStudentProgrammeEnrolmentDto(savedData);
    }


    @Override
    public void deleteStudentProgrammeEnrolment(StudentProgrammeEnrolmentCompositeKey targetKey) {
        StudentProgrammeEnrolment studentProgrammeEnrolment = studentProgrammeEnrolmentRepository.findById(targetKey)
                .orElseThrow(() -> new ResourceNotFoundException("Student Programme Enrolment record not found with provided key"));
        studentProgrammeEnrolmentRepository.delete(studentProgrammeEnrolment);
    }

}


