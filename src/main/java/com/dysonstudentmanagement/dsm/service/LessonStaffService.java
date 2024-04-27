package com.dysonstudentmanagement.dsm.service;

import com.dysonstudentmanagement.dsm.dto.LessonStaffDto;
import com.dysonstudentmanagement.dsm.entity.lessonstaff.LessonStaffCompositeKey;

import java.util.List;

public interface LessonStaffService {
    LessonStaffDto createLessonStaff(LessonStaffDto lessonStaffDto);
    List<LessonStaffDto> getLessonStaffByModuleID(String moduleID);
    List<LessonStaffDto> getLessonStaffByStaffID(String StaffID);
    LessonStaffDto updateLessonStaff(LessonStaffCompositeKey targetKey, LessonStaffDto updatedLessonStaffDto);
    void deleteLessonStaff(LessonStaffCompositeKey targetKey);


}
