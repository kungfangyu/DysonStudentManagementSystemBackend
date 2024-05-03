package com.dysonstudentmanagement.dsm.mapper;

import com.dysonstudentmanagement.dsm.dto.CourseworkDto;
import com.dysonstudentmanagement.dsm.entity.coursework.Coursework;

/*
    Class: CourseworkMapper
    Maps Coursework to the Coursework data transfer object class to manage information sent in API requests.
    Original Author: Jack Burnett, 27/04/2024
*/
public class CourseworkMapper {
    public static CourseworkDto mapToCourseworkDto (Coursework coursework) {
        return new CourseworkDto(
                coursework.getModuleID(),
                coursework.getCourseworkID(),
                coursework.getDescription(),
                coursework.getDeadline(),
                coursework.getPercentageOfModule(),
                coursework.isCourseworkPublished(),
                coursework.isGradePublished()
        );
    }

    public static Coursework mapToCoursework (CourseworkDto courseworkDto) {
        Coursework coursework = Coursework.builder()
                .moduleID(courseworkDto.getModuleId())
                .courseworkID(courseworkDto.getCourseworkId())
                .description(courseworkDto.getDescription())
                .deadline(courseworkDto.getDeadline())
                .percentageOfModule(courseworkDto.getPercentageOfModule())
                .isCourseworkPublished(courseworkDto.isCourseworkPublished())
                .isGradePublished(courseworkDto.isGradePublished())
                .build();
        return coursework;
    }
}
