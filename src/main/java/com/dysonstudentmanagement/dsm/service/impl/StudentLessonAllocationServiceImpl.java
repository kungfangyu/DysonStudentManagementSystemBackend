package com.dysonstudentmanagement.dsm.service.impl;

import com.dysonstudentmanagement.dsm.dto.LessonDto;
import com.dysonstudentmanagement.dsm.dto.StudentLessonAllocationDto;
import com.dysonstudentmanagement.dsm.entity.lesson.Lesson;
import com.dysonstudentmanagement.dsm.entity.lesson.LessonCompositeKey;
import com.dysonstudentmanagement.dsm.entity.studentlessonallocation.StudentLessonAllocation;
import com.dysonstudentmanagement.dsm.entity.studentlessonallocation.StudentLessonAllocationCompositeKey;
import com.dysonstudentmanagement.dsm.entity.user.UserPrimaryData;
import com.dysonstudentmanagement.dsm.exception.ResourceNotFoundException;
import com.dysonstudentmanagement.dsm.mapper.LessonMapper;
import com.dysonstudentmanagement.dsm.mapper.StudentLessonAllocationMapper;
import com.dysonstudentmanagement.dsm.repository.LessonRepository;
import com.dysonstudentmanagement.dsm.repository.StudentLessonAllocationRepository;
import com.dysonstudentmanagement.dsm.repository.UserPrimaryDataRepository;
import com.dysonstudentmanagement.dsm.service.StudentLessonAllocationService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@AllArgsConstructor
@Service
/*
StudentLessonAllocationServiceImpl

Class that implements service methods that act upon StudentLessonAllocation table in database

Original Author: Billy Peters 26/04/2024
 */
public class StudentLessonAllocationServiceImpl implements StudentLessonAllocationService {
    private LessonRepository lessonRepo;
    private UserPrimaryDataRepository userPrimaryDataRepo;
    private StudentLessonAllocationRepository studentLessonAllocationRepo;

    @Override
    public StudentLessonAllocationDto createStudentLessonAllocation(StudentLessonAllocationDto studentLessonAllocationDto) {
        StudentLessonAllocation studentLessonAllocation = StudentLessonAllocationMapper.mapToStudentLessonAllocation(studentLessonAllocationDto);

        UserPrimaryData studentData = userPrimaryDataRepo.findById(studentLessonAllocation.getStudentID())
                .orElseThrow(() -> new DataIntegrityViolationException("Failed...StudentID does not exist in foreign key table 'UserPrimaryData'"));
        studentLessonAllocation.setStudentPrimaryData(studentData);

        Lesson lesson = lessonRepo.findById(new LessonCompositeKey(studentLessonAllocation.getModuleID(), studentLessonAllocation.getLessonID()))
                .orElseThrow(() -> new DataIntegrityViolationException("Failed...Lesson does not exist in foreign key table 'Lesson'"));
        studentLessonAllocation.setLesson(lesson);

        StudentLessonAllocationCompositeKey newKey = new StudentLessonAllocationCompositeKey(studentLessonAllocation.getModuleID(),studentLessonAllocation.getLessonID(),studentLessonAllocation.getStudentID());
        Optional<StudentLessonAllocation> studentLessonAllocationExists = studentLessonAllocationRepo.findById(newKey);
        if(studentLessonAllocationExists.isPresent()){
            throw new DataIntegrityViolationException("Failed...Record already exists for given primary key");
        } else {
            StudentLessonAllocation savedData = studentLessonAllocationRepo.save(studentLessonAllocation);
            return StudentLessonAllocationMapper.mapToStudentLessonAllocationDto(savedData);
        }

    }

    @Override
    public List<StudentLessonAllocationDto> getStudentLessonAllocationByStudentID(String studentID) {
        List<StudentLessonAllocation> studentLessonAllocations = studentLessonAllocationRepo.findByStudentID(studentID);
        return studentLessonAllocations.stream().map(StudentLessonAllocationMapper::mapToStudentLessonAllocationDto).collect(Collectors.toList());
    }

    @Override
    public List<StudentLessonAllocationDto> getStudentLessonAllocationByModuleID(String moduleID) {
        List<StudentLessonAllocation> studentLessonAllocations = studentLessonAllocationRepo.findByModuleID(moduleID);
        return studentLessonAllocations.stream().map(StudentLessonAllocationMapper::mapToStudentLessonAllocationDto).collect(Collectors.toList());
    }

    @Override
    public List<StudentLessonAllocationDto> getStudentLessonAllocationByModuleIDAndStudentID(String moduleID, String studentID) {
        List<StudentLessonAllocation> studentLessonAllocations = studentLessonAllocationRepo.findByModuleIDAndStudentID(moduleID,studentID);
        return studentLessonAllocations.stream().map(StudentLessonAllocationMapper::mapToStudentLessonAllocationDto).collect(Collectors.toList());
    }

    @Override
    public List<StudentLessonAllocationDto> getStudentLessonAllocationByModuleIDAndLessonID(String moduleID, int lessonID) {
        List<StudentLessonAllocation> studentLessonAllocations = studentLessonAllocationRepo.findByModuleIDAndLessonID(moduleID, lessonID);
        return studentLessonAllocations.stream().map(StudentLessonAllocationMapper::mapToStudentLessonAllocationDto).collect(Collectors.toList());
    }

    @Override
    public List<LessonDto> getLessonInformationByStudentID(String studentID) {
        List<StudentLessonAllocation> studentLessonAllocations = studentLessonAllocationRepo.findByStudentID(studentID);
        List<Lesson> lessonList = new ArrayList<>();
        for(StudentLessonAllocation studentLessonAllocation:studentLessonAllocations){
            lessonList.add(studentLessonAllocation.getLesson());
        }
        return lessonList.stream().map(LessonMapper::mapToLessonDto).collect(Collectors.toList());
    }

    @Override
    public StudentLessonAllocationDto updateStudentLessonAllocation(StudentLessonAllocationCompositeKey targetKey, StudentLessonAllocationDto updatedDto) {
        StudentLessonAllocation updateStudentLessonAllocation = StudentLessonAllocationMapper.mapToStudentLessonAllocation(updatedDto);
        StudentLessonAllocation existingRecord = studentLessonAllocationRepo.findById(targetKey)
                .orElseThrow(() -> new ResourceNotFoundException("StudentLessonAllocation record not found with provided key"));

        existingRecord.setIsAttended(updateStudentLessonAllocation.getIsAttended());
        StudentLessonAllocation savedData = studentLessonAllocationRepo.save(existingRecord);
        return StudentLessonAllocationMapper.mapToStudentLessonAllocationDto(savedData);
    }

    @Override
    public void deleteStudentLessonAllocation(StudentLessonAllocationCompositeKey targetKey) {
        StudentLessonAllocation existingRecord = studentLessonAllocationRepo.findById(targetKey)
                .orElseThrow(() -> new ResourceNotFoundException("StudentLessonAllocation record not found with provided key"));
        studentLessonAllocationRepo.deleteById(targetKey);

    }
}
