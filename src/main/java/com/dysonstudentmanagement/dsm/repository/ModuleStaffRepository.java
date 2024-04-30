package com.dysonstudentmanagement.dsm.repository;

import com.dysonstudentmanagement.dsm.entity.modulestaff.ModuleStaff;
import com.dysonstudentmanagement.dsm.entity.modulestaff.ModuleStaffCompositeKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ModuleStaffRepository extends JpaRepository<ModuleStaff, ModuleStaffCompositeKey> {
    List<ModuleStaff> findByStaffID(String staffId);

    List<ModuleStaff> findByModuleID(String moduleId);
}
