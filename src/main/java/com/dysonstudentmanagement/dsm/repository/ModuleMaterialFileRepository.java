package com.dysonstudentmanagement.dsm.repository;

import com.dysonstudentmanagement.dsm.entity.modulematerilafile.ModuleMaterialFileCompositeKey;
import com.dysonstudentmanagement.dsm.entity.modulematerilafile.ModuleMaterialFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ModuleMaterialFileRepository extends JpaRepository<ModuleMaterialFile, ModuleMaterialFileCompositeKey> {
    List<ModuleMaterialFile> findByModuleIDAndMaterialNumber(String moduleID, int materialNumber);
}
