package com.dysonstudentmanagement.dsm.repository;

import com.dysonstudentmanagement.dsm.entity.studentcourseworkgrade.StudentCourseworkGrade;
import com.dysonstudentmanagement.dsm.entity.studentcourseworkgrade.StudentCourseworkGradeCompositeKey;
import org.springframework.data.jpa.repository.JpaRepository;

/*
    Interface: StudentCourseworkGradeRepository
    Interface which provides the StudentCourseworkGrade Entity with the relevant JPARepo methods.
    Original Author: Jack Burnett, 27/04/2024
*/
public interface StudentCourseworkGradeRepository extends JpaRepository<StudentCourseworkGrade, StudentCourseworkGradeCompositeKey> {
}
