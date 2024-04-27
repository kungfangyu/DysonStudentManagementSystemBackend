package com.dysonstudentmanagement.dsm.repository;

import com.dysonstudentmanagement.dsm.entity.ModuleMaterial.ModuleMaterial;
import com.dysonstudentmanagement.dsm.entity.ModuleMaterial.ModuleMaterialCompositeKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ModuleMaterialRepository extends JpaRepository<ModuleMaterial, ModuleMaterialCompositeKey> {
    List<ModuleMaterial> findByModuleID(String moduleId);
}
