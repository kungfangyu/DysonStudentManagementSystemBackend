package com.dysonstudentmanagement.dsm.controller;


import com.dysonstudentmanagement.dsm.dto.programmeModulesDto;
import com.dysonstudentmanagement.dsm.entity.ProgrammModules.programmeModulesCompositeKey;
import com.dysonstudentmanagement.dsm.service.ProgrammeModulesService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/programmeModule")
public class programmeModulesController {

    private ProgrammeModulesService programmeModulesService;

    @PostMapping
    public ResponseEntity<programmeModulesDto> createProgrammeModules(@RequestBody programmeModulesDto programmeModulesDto) {
        programmeModulesDto savedProgrammeModules = programmeModulesService.createProgrammeModules(programmeModulesDto);
        return new ResponseEntity<>(savedProgrammeModules, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<programmeModulesDto>> getAllProgrammeModules() {
        List<programmeModulesDto> programmeModulesList = programmeModulesService.getAllProgrammeModules();
        return ResponseEntity.ok(programmeModulesList);
    }

    @GetMapping("/{moduleID}/{programmeID}")
    public ResponseEntity<programmeModulesDto> getProgrammeModules(@PathVariable("moduleID") String moduleID, @PathVariable("programmeID") String programmeID) {
        programmeModulesCompositeKey targetKey = new programmeModulesCompositeKey(moduleID, programmeID);
        programmeModulesDto programmeModulesDto = programmeModulesService.getProgrammeModules(targetKey);
        return ResponseEntity.ok(programmeModulesDto);
    }

    @GetMapping("/by-modules/{moduleID}")
    public ResponseEntity<List<programmeModulesDto>> getProgrammeModulesByModulesID(@PathVariable("moduleID") String moduleID) {
        List<programmeModulesDto> programmeModulesDtos = programmeModulesService.getProgrammeModulesDtoByModulesID(moduleID);
        return ResponseEntity.ok(programmeModulesDtos);
    }

    @GetMapping("/by-programme/{programmeID}")
    public ResponseEntity<List<programmeModulesDto>> getProgrammeModulesByProgrammeID(@PathVariable("programmeID") String programmeID) {
        List<programmeModulesDto> programmeModulesDtos = programmeModulesService.getProgrammeModulesDtoByProgrammeID(programmeID);
        return ResponseEntity.ok(programmeModulesDtos);
    }

    @PutMapping("/{moduleID}/{programmeID}")
    public ResponseEntity<programmeModulesDto> updateProgrammeModules(@PathVariable("moduleID") String moduleID, @PathVariable("programmeID") String programmeID, @RequestBody programmeModulesDto updatedProgrammeModulesDto) {
        programmeModulesCompositeKey targetKey = new programmeModulesCompositeKey(moduleID, programmeID);
        programmeModulesDto savedProgrammeModules = programmeModulesService.updateProgrammeModules(targetKey, updatedProgrammeModulesDto);
        return ResponseEntity.ok(savedProgrammeModules);
    }

    @DeleteMapping("/{moduleID}/{programmeID}")
    public ResponseEntity<String> deleteProgrammeModules(@PathVariable("moduleID") String moduleID, @PathVariable("programmeID") String programmeID) {
        programmeModulesCompositeKey targetKey = new programmeModulesCompositeKey(moduleID, programmeID);
        programmeModulesService.deleteProgrammeModules(targetKey);
        return ResponseEntity.ok("programmeModules record deleted successfully");
    }
}
