package com.dysonstudentmanagement.dsm.repository;

import com.dysonstudentmanagement.dsm.entity.absencerequest.AbsenceRequest;
import com.dysonstudentmanagement.dsm.entity.absencerequest.AbsenceRequestCompositeKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AbsenceRequestRepository extends JpaRepository<AbsenceRequest, AbsenceRequestCompositeKey> {
    List<AbsenceRequest> findByModuleID(String moduleID);

    List<AbsenceRequest> findByModuleIDAndLessonID(String moduleID, int lessonID);

    List<AbsenceRequest> findByStudentID(String studentID);

    List<AbsenceRequest> findByModuleIDAndLessonIDAndStudentID(String moduleID, int lessonID, String studentID);
}
