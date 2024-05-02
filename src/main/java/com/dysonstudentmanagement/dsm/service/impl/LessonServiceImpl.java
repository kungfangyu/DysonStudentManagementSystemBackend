package com.dysonstudentmanagement.dsm.service.impl;

import com.dysonstudentmanagement.dsm.dto.LessonDto;
import com.dysonstudentmanagement.dsm.entity.lesson.Lesson;
import com.dysonstudentmanagement.dsm.entity.lesson.LessonCompositeKey;
import com.dysonstudentmanagement.dsm.entity.moduledetails.ModuleDetails;
import com.dysonstudentmanagement.dsm.exception.ResourceNotFoundException;
import com.dysonstudentmanagement.dsm.mapper.LessonMapper;
import com.dysonstudentmanagement.dsm.repository.LessonRepository;
import com.dysonstudentmanagement.dsm.repository.ModuleDetailsRepository;
import com.dysonstudentmanagement.dsm.service.LessonService;
import lombok.AllArgsConstructor;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class LessonServiceImpl implements LessonService {
    private ModuleDetailsRepository moduleDetailsRepo;
    private LessonRepository lessonRepo;

    @Override
    public LessonDto createLesson(LessonDto lessonDto) {
        Lesson lesson = LessonMapper.mapToLesson(lessonDto);
        ModuleDetails associatedModule = moduleDetailsRepo.findById(lesson.getModuleID())
                .orElseThrow(() -> new DataIntegrityViolationException("Failed... ModuleID does not exist in target table ModuleDetails")
                );

        int lessonID = lessonRepo.findByModuleID(lesson.getModuleID()).size()+1; // manual handling of autoincrementing, as hibernate does not support autoincrementing for one column of a composite key
        lesson.setLessonID(lessonID);
        lesson.setModuleDetails(associatedModule);
        Lesson savedLesson = lessonRepo.save(lesson);
        return LessonMapper.mapToLessonDto(savedLesson);
    }

    @Override
    public LessonDto getLesson(LessonCompositeKey targetKey) {
        Lesson lesson = lessonRepo.findById(targetKey)
                .orElseThrow(() -> new ResourceNotFoundException("Lesson record not found with primary key provided")
                );
        return LessonMapper.mapToLessonDto(lesson);
    }

    @Override
    public List<LessonDto> getLessonByModule(String moduleID) {
        List<Lesson> lessons = lessonRepo.findByModuleID(moduleID);
        return lessons.stream().map(LessonMapper::mapToLessonDto).collect(Collectors.toList());
    }

    @Override
    public LessonDto updateLesson(LessonCompositeKey targetKey, LessonDto updatedLessonDto) {
        Lesson updatedLesson = LessonMapper.mapToLesson(updatedLessonDto);
        Lesson existingLesson = lessonRepo.findById(targetKey)
                .orElseThrow(() -> new ResourceNotFoundException("Lesson record not found with primary key provided")
                );
        existingLesson.setLessonType(updatedLesson.getLessonType());
        existingLesson.setStartTime(updatedLesson.getStartTime());
        existingLesson.setEndTime(updatedLesson.getEndTime());
        existingLesson.setAttendanceRequired(updatedLesson.isAttendanceRequired());
        Lesson savedLesson = lessonRepo.save(existingLesson);
        return LessonMapper.mapToLessonDto(savedLesson);
    }

    @Override
    public void deleteLesson(LessonCompositeKey targetKey) {
        Lesson existingLesson = lessonRepo.findById(targetKey)
                .orElseThrow(() -> new ResourceNotFoundException("Lesson record not found with primary key provided")
                );
        lessonRepo.deleteById(targetKey);
    }
}
