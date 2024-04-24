package com.dysonstudentmanagement.dsm.repository;

import com.dysonstudentmanagement.dsm.entity.moduledetails.ModuleDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModuleDetailsRepository extends JpaRepository<ModuleDetails,String> {
}
