package com.dysonstudentmanagement.dsm.dto;

import com.dysonstudentmanagement.dsm.entity.lesson.Lesson;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDateTime;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
/*
LessonDto

Defines fields/data that will be transmitted to/from the API client when requesting/sending lesson data.

Original Author: Billy Peters 26/04/2024
 */
public class LessonDto {
    private String moduleID;
    private int lessonID;
    private Lesson.LessonType lessonType;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private boolean isAttendanceRequired;
}
