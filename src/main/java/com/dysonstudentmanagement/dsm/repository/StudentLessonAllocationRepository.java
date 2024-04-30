package com.dysonstudentmanagement.dsm.repository;

import com.dysonstudentmanagement.dsm.entity.studentlessonallocation.StudentLessonAllocation;
import com.dysonstudentmanagement.dsm.entity.studentlessonallocation.StudentLessonAllocationCompositeKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentLessonAllocationRepository extends JpaRepository<StudentLessonAllocation, StudentLessonAllocationCompositeKey> {
    List<StudentLessonAllocation> findByModuleID(String moduleID);

    List<StudentLessonAllocation> findByStudentID(String studentID);

    List<StudentLessonAllocation> findByModuleIDAndStudentID(String moduleID, String studentID);

    List<StudentLessonAllocation> findByModuleIDAndLessonID(String moduleID, int lessonID);
}
