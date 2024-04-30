package com.dysonstudentmanagement.dsm.controller;

import com.dysonstudentmanagement.dsm.dto.programmeStaffDto;
import com.dysonstudentmanagement.dsm.entity.ProgrammeStaff.programmeStaffCompositeKey;
import com.dysonstudentmanagement.dsm.service.ProgrammeStaffService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@AllArgsConstructor
@RestController
@RequestMapping("/api/programmeStaff")
public class programmeStaffController {

    private final ProgrammeStaffService programmeStaffService;

    @PostMapping
    public ResponseEntity<programmeStaffDto> createProgrammeStaff(@RequestBody programmeStaffDto programmeStaffDto){
        System.out.println("in controller, are we null?");
        System.out.println(programmeStaffDto);
        com.dysonstudentmanagement.dsm.dto.programmeStaffDto savedStaff = programmeStaffService.createProgrammeStaff(programmeStaffDto);
        return new ResponseEntity<>(savedStaff, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<programmeStaffDto>> getAllProgrammeStaff() {
        List<programmeStaffDto> programmesStaff = programmeStaffService.getAllProgrammesStaff();
        return ResponseEntity.ok(programmesStaff);
    }

    @GetMapping("/{programmeID}/{staffID}")
    public ResponseEntity<programmeStaffDto> getProgrammeStaff(@PathVariable("programmeID") String programmeID, @PathVariable("staffID") String staffID){
        programmeStaffCompositeKey targetKey = new programmeStaffCompositeKey(programmeID, staffID);
        programmeStaffDto programmeStaffDto = programmeStaffService.getProgrammeStaff(targetKey);
        return ResponseEntity.ok(programmeStaffDto);
    }

    @GetMapping("/by-staff/{staffID}")
    public ResponseEntity<List<programmeStaffDto>> getProgrammeStaffByStaffID(@PathVariable("staffID") String staffID){
        List<programmeStaffDto> programmeStaffDtos = programmeStaffService.getProgrammeStaffByStaffID(staffID);
        return ResponseEntity.ok(programmeStaffDtos);
    }

    @GetMapping("/by-programme/{programmeID}")
    public ResponseEntity<List<programmeStaffDto>> getProgrammeStaffByProgrammeID(@PathVariable("programmeID") String programmeID){
        List<programmeStaffDto> programmeStaffDtos = programmeStaffService.getProgrammeStaffDtoByProgrammeID(programmeID);
        return ResponseEntity.ok(programmeStaffDtos);
    }

    @PutMapping("/{programmeID}/{staffID}")
    public ResponseEntity<programmeStaffDto> updateProgrammeStaff(@PathVariable("programmeID") String programmeID, @PathVariable("staffID") String staffID, @RequestBody programmeStaffDto updatedProgrammeStaffDto){
        System.out.println(updatedProgrammeStaffDto);
        programmeStaffCompositeKey targetKey = new programmeStaffCompositeKey(programmeID, staffID);
        System.out.println("Target key:");
        System.out.println(targetKey.getProgrammeID());
        System.out.println(targetKey.getStaffID());
        programmeStaffDto savedProgrammeStaff = programmeStaffService.updateProgrammeStaff(targetKey, updatedProgrammeStaffDto);
        System.out.println("this is saved progstaff");
        System.out.println(savedProgrammeStaff);
        return ResponseEntity.ok(savedProgrammeStaff);
    }

    @DeleteMapping("/{programmeID}/{staffID}")
    public ResponseEntity<String> deleteProgrammeStaff(@PathVariable("programmeID") String programmeID, @PathVariable("staffID") String staffID){
        programmeStaffCompositeKey targetKey = new programmeStaffCompositeKey(programmeID, staffID);
        programmeStaffService.deleteProgrammeStaff(targetKey);
        return ResponseEntity.ok("programmeStaff record deleted successfully");
    }
}
