package com.dysonstudentmanagement.dsm.repository;

import com.dysonstudentmanagement.dsm.entity.lessonstaff.LessonStaff;
import com.dysonstudentmanagement.dsm.entity.lessonstaff.LessonStaffCompositeKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LessonStaffRepository extends JpaRepository<LessonStaff, LessonStaffCompositeKey> {
    List<LessonStaff> findByModuleID(String moduleID);

    List<LessonStaff> findByStaffID(String staffID);

    List<LessonStaff> findByModuleIDAndLessonID(String moduleID, int lessonID);
}
