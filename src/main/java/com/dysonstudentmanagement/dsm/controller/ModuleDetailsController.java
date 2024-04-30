package com.dysonstudentmanagement.dsm.controller;

import com.dysonstudentmanagement.dsm.dto.ModuleDetailsDto;
import com.dysonstudentmanagement.dsm.service.ModuleDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/moduleDetails")
public class ModuleDetailsController {
    private ModuleDetailsService moduleDetailsService;

    @PostMapping
    public ResponseEntity<ModuleDetailsDto> createModuleDetails(@RequestBody ModuleDetailsDto moduleDetailsDto) {
        ModuleDetailsDto savedModule = moduleDetailsService.createModuleDetails(moduleDetailsDto);
        return new ResponseEntity<>(savedModule, HttpStatus.CREATED);
    }

    @GetMapping("{moduleId}")
    public ResponseEntity<ModuleDetailsDto> getModuleDetails(@PathVariable("moduleId") String moduleId) {
        return ResponseEntity.ok(moduleDetailsService.getModuleDetails(moduleId));
    }

    @GetMapping
    public ResponseEntity<List<ModuleDetailsDto>> getAllModuleDetails() {
        return ResponseEntity.ok(moduleDetailsService.getAllModuleDetails());
    }

    @PutMapping("{moduleId}")
    public ResponseEntity<ModuleDetailsDto> updateModuleDetails(@PathVariable("moduleId") String moduleId,
                                                                @RequestBody ModuleDetailsDto moduleDetailsDto) {
        return ResponseEntity.ok(moduleDetailsService.updateModuleDetails(moduleId, moduleDetailsDto));
    }

    @DeleteMapping("{moduleId}")
    public ResponseEntity<String > deleteModuleDetails(@PathVariable("moduleId") String moduleId) {
        moduleDetailsService.deleteModuleDetails(moduleId);
        return ResponseEntity.ok("Deleted Module Details");
    }
}
