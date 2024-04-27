package com.dysonstudentmanagement.dsm.repository;

import com.dysonstudentmanagement.dsm.entity.ProgrammeStaff.ProgrammeStaff;
import com.dysonstudentmanagement.dsm.entity.ProgrammeStaff.ProgrammeStaffCompositeKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProgrammeStaffRepository extends JpaRepository<ProgrammeStaff, ProgrammeStaffCompositeKey> {

      public List<ProgrammeStaff> findByStaffID(String staffID);

      public List<ProgrammeStaff> findByProgrammeID(String ProgrammeID);

      public List<ProgrammeStaff> findAll();


}
