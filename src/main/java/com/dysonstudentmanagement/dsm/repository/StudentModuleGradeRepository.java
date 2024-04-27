package com.dysonstudentmanagement.dsm.repository;

import com.dysonstudentmanagement.dsm.entity.studentmodulegrade.StudentModuleGrade;
import com.dysonstudentmanagement.dsm.entity.studentmodulegrade.StudentModuleGradeCompositeKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentModuleGradeRepository extends JpaRepository<StudentModuleGrade, StudentModuleGradeCompositeKey> {
    List<StudentModuleGrade> findByModuleID(String moduleID);

    List<StudentModuleGrade> findByStudentID(String studentID);
}
