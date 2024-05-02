package com.dysonstudentmanagement.dsm.controller;

import com.dysonstudentmanagement.dsm.dto.ModuleStaffDto;
import com.dysonstudentmanagement.dsm.entity.modulestaff.ModuleStaffCompositeKey;
import com.dysonstudentmanagement.dsm.service.ModuelStaffService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/moduleStaff")
/*
ModuleStaffController

Provides api mappings to access moduleStaffService methods.

Original Author: Tianpu Li 25/04/2024
 */
public class ModuleStaffController {
    private ModuelStaffService moduelStaffService;

    @PostMapping
    public ResponseEntity<ModuleStaffDto> createModuleStaff(@RequestBody ModuleStaffDto moduleStaffDto) {
        ModuleStaffDto createdModuleStaff = moduelStaffService.createModuleStaff(moduleStaffDto);
        return new ResponseEntity<>(createdModuleStaff, HttpStatus.CREATED);
    }

    @GetMapping("{staffID}")
    public ResponseEntity<List<ModuleStaffDto>> getModuleStaff(@PathVariable("staffID") String staffID) {
        return ResponseEntity.ok(moduelStaffService.getModuleByStaffId(staffID));
    }

    @GetMapping("{moduleID}")
    public ResponseEntity<List<ModuleStaffDto>> getModuleStaffByModule(@PathVariable("moduleID") String moduleID) {
        return ResponseEntity.ok(moduelStaffService.getStaffByModuleId(moduleID));
    }

    @DeleteMapping("{moduleID}/{staffID}")
    public ResponseEntity<String> deleteModuleStaff(@PathVariable("moduleID") String moduleID,
                                                    @PathVariable("staffID") String staffID) {
        ModuleStaffCompositeKey pk = new ModuleStaffCompositeKey(moduleID, staffID);
        moduelStaffService.deleteModuleStaff(pk);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}
