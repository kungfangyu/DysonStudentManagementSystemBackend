package com.dysonstudentmanagement.dsm.service;

import com.dysonstudentmanagement.dsm.dto.LessonDto;
import com.dysonstudentmanagement.dsm.entity.lesson.LessonCompositeKey;

import java.util.List;

/*
LessonService

Interface that declares service methods that act upon Lesson table in database

Original Author: Billy Peters 26/04/2024
 */
public interface LessonService {

    LessonDto createLesson(LessonDto lessonDto);

    LessonDto getLesson(LessonCompositeKey targetKey);

    List<LessonDto> getLessonByModule(String moduleID);

    LessonDto updateLesson(LessonCompositeKey targetKey, LessonDto updatedLessonDto);

    void deleteLesson(LessonCompositeKey targetKey);

}
