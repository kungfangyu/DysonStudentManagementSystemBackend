package com.dysonstudentmanagement.dsm.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class CourseworkDto {
    private String moduleId;
    private int courseworkId;
    private String description;
    private LocalDateTime deadline;
    private Double percentageOfModule;
    private boolean isCourseworkPublished;
    private boolean isGradePublished;
}
