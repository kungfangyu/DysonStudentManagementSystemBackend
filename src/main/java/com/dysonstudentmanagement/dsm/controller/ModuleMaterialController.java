package com.dysonstudentmanagement.dsm.controller;

import com.dysonstudentmanagement.dsm.dto.ModuleMaterialDto;
import com.dysonstudentmanagement.dsm.entity.modulematerial.ModuleMaterialCompositeKey;
import com.dysonstudentmanagement.dsm.service.ModuleMaterialService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/moduleMaterial")
/*
ModuleMaterialController

Provides api mappings to access moduleMaterialService methods.

Original Author: Tianpu Li 25/04/2024
 */
public class ModuleMaterialController {
    private ModuleMaterialService moduleMaterialService;

    @PostMapping
    public ResponseEntity<ModuleMaterialDto> createModuleMaterial(@RequestBody ModuleMaterialDto moduleMaterialDto) {
        ModuleMaterialDto savedMaterial = moduleMaterialService.createModuleMaterial(moduleMaterialDto);
        return new ResponseEntity<>(savedMaterial, HttpStatus.CREATED);
    }

    @GetMapping("{moduleID}/{MaterialNumber}")
    public ResponseEntity<ModuleMaterialDto> getModuleMaterial(@PathVariable("moduleID") String moduleID,
                                                               @PathVariable("MaterialNumber") int MaterialNumber) {
        ModuleMaterialCompositeKey pk = new ModuleMaterialCompositeKey(moduleID, MaterialNumber);
        return ResponseEntity.ok(moduleMaterialService.getModuleMaterialById(pk));
    }

    @GetMapping("{moduleID}")
    public ResponseEntity<List<ModuleMaterialDto>> getModuleMaterialsByModuleID(@PathVariable("moduleID") String moduleID) {
        return ResponseEntity.ok(moduleMaterialService.getMaterialsByModuleId(moduleID));
    }

    @PutMapping("{moduleID}/{materialNumber}")
    public ResponseEntity<ModuleMaterialDto> updateModuleMaterial(@PathVariable("moduleID") String moduleID,
                                                                  @PathVariable("materialNumber") int materialNumber,
                                                                  @RequestBody ModuleMaterialDto moduleMaterialDto) {
        ModuleMaterialCompositeKey pk = new ModuleMaterialCompositeKey(moduleID, materialNumber);
        ModuleMaterialDto savedMaterial = moduleMaterialService.updateModuleMaterial(pk, moduleMaterialDto);
        return ResponseEntity.ok(savedMaterial);
    }

    @DeleteMapping("{moduleID}/{materialNumber}")
    public ResponseEntity<String> deleteModuleMaterial(@PathVariable("moduleID") String moduleId,
                                                       @PathVariable("materialNumber") int materialNumber){
        ModuleMaterialCompositeKey pk = new ModuleMaterialCompositeKey(moduleId, materialNumber);
        moduleMaterialService.deleteModuleMaterial(pk);
        return new ResponseEntity<>("ModuleMaterial deleted", HttpStatus.OK);
    }
}
