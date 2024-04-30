package com.dysonstudentmanagement.dsm.repository;

import com.dysonstudentmanagement.dsm.entity.studentexamgrade.StudentExamGrade;
import com.dysonstudentmanagement.dsm.entity.studentexamgrade.StudentExamGradeCompositeKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository interface for accessing StudentExamGrade entities in the database.
 */
public interface StudentExamGradeRepository extends JpaRepository<StudentExamGrade, StudentExamGradeCompositeKey> {
    List<StudentExamGrade> findByModuleIDAndExamID(String moduleID, int examID);
}
