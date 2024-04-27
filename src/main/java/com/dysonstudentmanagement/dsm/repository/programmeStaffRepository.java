package com.dysonstudentmanagement.dsm.repository;

import com.dysonstudentmanagement.dsm.entity.ProgrammeStaff.programmeStaff;
import com.dysonstudentmanagement.dsm.entity.ProgrammeStaff.programmeStaffCompositeKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface programmeStaffRepository extends JpaRepository<programmeStaff, programmeStaffCompositeKey> {

      public List<programmeStaff> findByStaffID(String staffID);

      public List<programmeStaff> findByProgrammeID(String ProgrammeID);

      public List<programmeStaff> findAll();


}
