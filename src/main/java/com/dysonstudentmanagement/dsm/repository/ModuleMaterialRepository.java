package com.dysonstudentmanagement.dsm.repository;

import com.dysonstudentmanagement.dsm.entity.modulematerial.ModuleMaterial;
import com.dysonstudentmanagement.dsm.entity.modulematerial.ModuleMaterialCompositeKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ModuleMaterialRepository extends JpaRepository<ModuleMaterial, ModuleMaterialCompositeKey> {
    List<ModuleMaterial> findByModuleID(String moduleId);
}
