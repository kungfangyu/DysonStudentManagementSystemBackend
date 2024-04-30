package com.dysonstudentmanagement.dsm.mapper;

import com.dysonstudentmanagement.dsm.dto.LessonStaffDto;
import com.dysonstudentmanagement.dsm.entity.lessonstaff.LessonStaff;

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