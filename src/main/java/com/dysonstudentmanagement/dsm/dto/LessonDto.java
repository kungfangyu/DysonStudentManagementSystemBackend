package com.dysonstudentmanagement.dsm.dto;

import com.dysonstudentmanagement.dsm.entity.lesson.Lesson;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


import java.sql.Timestamp;
@Getter
@Setter
@AllArgsConstructor
public class LessonDto {
    private String moduleID;
    private int lessonID;
    private Lesson.LessonType lessonType;
    private Timestamp startTime;
    private Timestamp endTime;
    private boolean isAttendanceRequired;
}
