package com.dysonstudentmanagement.dsm.repository;

import com.dysonstudentmanagement.dsm.entity.ProgrammeStaff.programmeStaff;
import com.dysonstudentmanagement.dsm.entity.StudentProgrammeEnrolment.StudentProgrammeEnrolment;
import com.dysonstudentmanagement.dsm.entity.StudentProgrammeEnrolment.StudentProgrammeEnrolmentCompositeKey;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentProgrammeEnrolmentRepository extends JpaRepository<StudentProgrammeEnrolment, StudentProgrammeEnrolmentCompositeKey> {

    public List<StudentProgrammeEnrolment> findByStudentID(String StudentID);

    public List<StudentProgrammeEnrolment> findByProgrammeID(String ProgrammeID);

    public List<StudentProgrammeEnrolment> findAll();



}
