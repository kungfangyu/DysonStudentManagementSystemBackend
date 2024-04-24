package com.dysonstudentmanagement.dsm.repository;

import com.dysonstudentmanagement.dsm.entity.studenttutor.StudentTutor;
import com.dysonstudentmanagement.dsm.entity.studenttutor.StudentTutorCompositeKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentTutorRepository extends JpaRepository<StudentTutor, StudentTutorCompositeKey> {
    public List<StudentTutor> findByStaffID(String staffID);

    public List<StudentTutor> findByStudentID(String studentID);
}
