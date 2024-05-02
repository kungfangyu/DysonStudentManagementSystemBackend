package com.dysonstudentmanagement.dsm.controller;


import com.dysonstudentmanagement.dsm.dto.ModuleMaterialFileDto;
import com.dysonstudentmanagement.dsm.entity.modulematerilafile.ModuleMaterialFileCompositeKey;
import com.dysonstudentmanagement.dsm.service.ModuleMaterialFileService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/moduleMaterialFile")
/*
ModuleMaterialFileController

Provides api mappings/handles api requests to access moduleMaterialFileService methods.

Original Author: Grace Liu 25/04/2024
 */
public class ModuleMaterialFileController {
    private ModuleMaterialFileService moduleMaterialFileService;

    @PostMapping
    public ResponseEntity<ModuleMaterialFileDto> createModuleMaterialFile(@RequestBody ModuleMaterialFileDto moduleMaterialFileDto) {
        ModuleMaterialFileDto savedMaterialFile = moduleMaterialFileService.createModuleMaterialFile(moduleMaterialFileDto);
        return new ResponseEntity<>(savedMaterialFile, HttpStatus.CREATED);
    }

    @GetMapping("{moduleID}/{MaterialNumber}/{fileNumber}")
    public ResponseEntity<ModuleMaterialFileDto> getModuleMaterial(@PathVariable("moduleID") String moduleID,
                                                               @PathVariable("MaterialNumber") int MaterialNumber,
                                                               @PathVariable("fileNumber") int fileNumber) {
        ModuleMaterialFileCompositeKey pk = new ModuleMaterialFileCompositeKey(moduleID, MaterialNumber, fileNumber);
        return ResponseEntity.ok(moduleMaterialFileService.getModuleMaterialFileById(pk));
    }

    @GetMapping("{moduleID}/{materialID}")
    public ResponseEntity<List<ModuleMaterialFileDto>> getModuleMaterialsByModuleID(@PathVariable("moduleID") String moduleID,
                                                                                 @PathVariable("materialID") int materialID) {
        return ResponseEntity.ok(moduleMaterialFileService.getMaterialsByModuleMaterialId(moduleID, materialID));
    }

    @PutMapping("{moduleID}/{materialNumber}/{fileNumber}")
    public ResponseEntity<ModuleMaterialFileDto> updateModuleMaterialFile(@PathVariable("moduleID") String moduleID,
                                                                          @PathVariable("materialNumber") int materialNumber,
                                                                          @PathVariable("fileNumber") int fileNumber,
                                                                          @RequestBody ModuleMaterialFileDto moduleMaterialFileDto) {
        ModuleMaterialFileCompositeKey pk = new ModuleMaterialFileCompositeKey(moduleID, materialNumber, fileNumber);
        ModuleMaterialFileDto savedMaterial = moduleMaterialFileService.updateModuleMaterialFile(pk, moduleMaterialFileDto);
        return ResponseEntity.ok(savedMaterial);
    }

    @DeleteMapping("{moduleID}/{materialNumber}/{fileNumber}")
    public ResponseEntity<String> deleteModuleMaterialFile(@PathVariable("moduleID") String moduleID,
                                                                          @PathVariable("materialNumber") int materialNumber,
                                                                          @PathVariable("fileNumber") int fileNumber) {
        ModuleMaterialFileCompositeKey pk = new ModuleMaterialFileCompositeKey(moduleID, materialNumber, fileNumber);
        moduleMaterialFileService.deleteModuleMaterialFile(pk);
        return new ResponseEntity<>("Module material file Deleted", HttpStatus.OK);
    }


}
