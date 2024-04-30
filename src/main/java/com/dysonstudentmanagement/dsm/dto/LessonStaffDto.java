package com.dysonstudentmanagement.dsm.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LessonStaffDto {
    private String moduleID;
    private int lessonID;
    private String staffID;
}
