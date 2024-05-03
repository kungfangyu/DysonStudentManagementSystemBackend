package com.dysonstudentmanagement.dsm.service;

import com.dysonstudentmanagement.dsm.dto.LessonDto;
import com.dysonstudentmanagement.dsm.dto.LessonStaffDto;
import com.dysonstudentmanagement.dsm.entity.lessonstaff.LessonStaffCompositeKey;

import java.util.List;

/*
LessonStaffService

Interface that declares service methods that act upon LessonStaff table in database

Original Author: Billy Peters 27/04/2024
 */
public interface LessonStaffService {
    LessonStaffDto createLessonStaff(LessonStaffDto lessonStaffDto);
    List<LessonStaffDto> getLessonStaffByModuleID(String moduleID);
    List<LessonStaffDto> getLessonStaffByStaffID(String staffID);

    List<LessonStaffDto> getLessonStaffByModuleIDAndLectureID(String moduleID, int lectureID);

    List<LessonDto> getLessonInfoByStaffID(String staffID);
    void deleteLessonStaff(LessonStaffCompositeKey targetKey);


}
