package com.dysonstudentmanagement.dsm.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
/*
CourseworkDto

Defines fields/data that will be transmitted to/from the API client when requesting/sending coursework data.

Original Author: Jack Burnett 27/04/2024
 */
public class CourseworkDto {
    private String moduleId;
    private int courseworkId;
    private String description;
    private LocalDateTime deadline;
    private Double percentageOfModule;
    private boolean isCourseworkPublished;
    private boolean isGradePublished;
}
