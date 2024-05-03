package com.dysonstudentmanagement.dsm.mapper;

import com.dysonstudentmanagement.dsm.dto.LessonStaffDto;
import com.dysonstudentmanagement.dsm.entity.lessonstaff.LessonStaff;

/*
    Class: LessonStaffMapper
    Mapper class responsible for mapping between LessonStaff and LessonStaffDto objects.
    Original Author: Billy Peters 27/04/2024
 */
public class LessonStaffMapper {
    public static LessonStaffDto mapToLessonStaffDto(LessonStaff lessonStaff) {
        return new LessonStaffDto(lessonStaff.getModuleID(),
                lessonStaff.getLessonID(),
                lessonStaff.getStaffID()
        );

    }
    public static LessonStaff mapToLessonStaff(LessonStaffDto lessonStaffDto){
        LessonStaff lessonStaff = LessonStaff.builder()
                .moduleID(lessonStaffDto.getModuleID())
                .lessonID(lessonStaffDto.getLessonID())
                .staffID(lessonStaffDto.getStaffID())
                .build();
        return lessonStaff;
    }
}