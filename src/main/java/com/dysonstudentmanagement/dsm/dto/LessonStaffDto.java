package com.dysonstudentmanagement.dsm.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
/*
LessonStaffDto

Defines fields/data that will be transmitted to/from the API client when requesting/sending lessonStaff data.

Original Author: Billy Peters 26/04/2024
 */
public class LessonStaffDto {
    private String moduleID;
    private int lessonID;
    private String staffID;
}
