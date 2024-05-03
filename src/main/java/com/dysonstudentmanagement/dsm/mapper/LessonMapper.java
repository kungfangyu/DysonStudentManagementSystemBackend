package com.dysonstudentmanagement.dsm.mapper;

import com.dysonstudentmanagement.dsm.dto.LessonDto;
import com.dysonstudentmanagement.dsm.entity.lesson.Lesson;

/*
    Class: LessonMapper
    Mapper class responsible for mapping between Lesson and LessonDto objects.
    Original Author: Billy Peters 26/04/2024
 */
public class LessonMapper {
    public static LessonDto mapToLessonDto(Lesson lesson){
        return new LessonDto(
                lesson.getModuleID(),
                lesson.getLessonID(),
                lesson.getLessonType(),
                lesson.getStartTime(),
                lesson.getEndTime(),
                lesson.isAttendanceRequired()
        );
    }

    public static Lesson mapToLesson(LessonDto lessonDto){
        Lesson lesson = Lesson.builder()
                .moduleID(lessonDto.getModuleID())
                .lessonID(lessonDto.getLessonID())
                .lessonType(lessonDto.getLessonType())
                .startTime(lessonDto.getStartTime())
                .endTime(lessonDto.getEndTime())
                .isAttendanceRequired(lessonDto.isAttendanceRequired())
                .build();
        return lesson;
    }
}
