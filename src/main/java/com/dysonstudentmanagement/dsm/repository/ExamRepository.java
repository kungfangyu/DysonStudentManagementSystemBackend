package com.dysonstudentmanagement.dsm.repository;

import com.dysonstudentmanagement.dsm.entity.exam.Exam;
import com.dysonstudentmanagement.dsm.entity.exam.ExamCompositeKey;
import com.dysonstudentmanagement.dsm.entity.previousqualification.PreviousQualification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository interface for accessing Exam entities in the database.
 */

public interface ExamRepository extends JpaRepository<Exam, ExamCompositeKey> {
    public List<Exam> findByModuleID(String moduleID);
}
