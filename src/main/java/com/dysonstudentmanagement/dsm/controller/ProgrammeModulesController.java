package com.dysonstudentmanagement.dsm.controller;


import com.dysonstudentmanagement.dsm.dto.ProgrammeModulesDto;
import com.dysonstudentmanagement.dsm.entity.programmmodules.ProgrammeModulesCompositeKey;
import com.dysonstudentmanagement.dsm.service.ProgrammeModulesService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/programmeModule")
public class ProgrammeModulesController {

    private ProgrammeModulesService programmeModulesService;

    @PostMapping
    public ResponseEntity<ProgrammeModulesDto> createProgrammeModules(@RequestBody ProgrammeModulesDto programmeModulesDto) {
        ProgrammeModulesDto savedProgrammeModules = programmeModulesService.createProgrammeModules(programmeModulesDto);
        return new ResponseEntity<>(savedProgrammeModules, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProgrammeModulesDto>> getAllProgrammeModules() {
        List<ProgrammeModulesDto> programmeModulesList = programmeModulesService.getAllProgrammeModules();
        return ResponseEntity.ok(programmeModulesList);
    }

    @GetMapping("/{moduleID}/{programmeID}")
    public ResponseEntity<ProgrammeModulesDto> getProgrammeModules(@PathVariable("moduleID") String moduleID, @PathVariable("programmeID") String programmeID) {
        ProgrammeModulesCompositeKey targetKey = new ProgrammeModulesCompositeKey(moduleID, programmeID);
        ProgrammeModulesDto programmeModulesDto = programmeModulesService.getProgrammeModules(targetKey);
        return ResponseEntity.ok(programmeModulesDto);
    }

    @GetMapping("/by-modules/{moduleID}")
    public ResponseEntity<List<ProgrammeModulesDto>> getProgrammeModulesByModulesID(@PathVariable("moduleID") String moduleID) {
        List<ProgrammeModulesDto> programmeModulesDtos = programmeModulesService.getProgrammeModulesDtoByModulesID(moduleID);
        return ResponseEntity.ok(programmeModulesDtos);
    }

    @GetMapping("/by-programme/{programmeID}")
    public ResponseEntity<List<ProgrammeModulesDto>> getProgrammeModulesByProgrammeID(@PathVariable("programmeID") String programmeID) {
        List<ProgrammeModulesDto> programmeModulesDtos = programmeModulesService.getProgrammeModulesDtoByProgrammeID(programmeID);
        return ResponseEntity.ok(programmeModulesDtos);
    }

    @PutMapping("/{moduleID}/{programmeID}")
    public ResponseEntity<ProgrammeModulesDto> updateProgrammeModules(@PathVariable("moduleID") String moduleID, @PathVariable("programmeID") String programmeID, @RequestBody ProgrammeModulesDto updatedProgrammeModulesDto) {
        ProgrammeModulesCompositeKey targetKey = new ProgrammeModulesCompositeKey(moduleID, programmeID);
        ProgrammeModulesDto savedProgrammeModules = programmeModulesService.updateProgrammeModules(targetKey, updatedProgrammeModulesDto);
        return ResponseEntity.ok(savedProgrammeModules);
    }

    @DeleteMapping("/{moduleID}/{programmeID}")
    public ResponseEntity<String> deleteProgrammeModules(@PathVariable("moduleID") String moduleID, @PathVariable("programmeID") String programmeID) {
        ProgrammeModulesCompositeKey targetKey = new ProgrammeModulesCompositeKey(moduleID, programmeID);
        programmeModulesService.deleteProgrammeModules(targetKey);
        return ResponseEntity.ok("programmeModules record deleted successfully");
    }
}
