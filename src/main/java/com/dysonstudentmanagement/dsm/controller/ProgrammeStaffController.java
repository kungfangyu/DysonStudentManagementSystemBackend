package com.dysonstudentmanagement.dsm.controller;

import com.dysonstudentmanagement.dsm.dto.ProgrammeStaffDto;
import com.dysonstudentmanagement.dsm.entity.programmestaff.ProgrammeStaffCompositeKey;
import com.dysonstudentmanagement.dsm.service.ProgrammeStaffService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@AllArgsConstructor
@RestController
@RequestMapping("/api/programmeStaff")
public class ProgrammeStaffController {

    private final ProgrammeStaffService programmeStaffService;

    @PostMapping
    public ResponseEntity<ProgrammeStaffDto> createProgrammeStaff(@RequestBody ProgrammeStaffDto programmeStaffDto){
        System.out.println("in controller, are we null?");
        System.out.println(programmeStaffDto);
        ProgrammeStaffDto savedStaff = programmeStaffService.createProgrammeStaff(programmeStaffDto);
        return new ResponseEntity<>(savedStaff, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProgrammeStaffDto>> getAllProgrammeStaff() {
        List<ProgrammeStaffDto> programmesStaff = programmeStaffService.getAllProgrammeStaff();
        return ResponseEntity.ok(programmesStaff);
    }

    @GetMapping("/{programmeID}/{staffID}")
    public ResponseEntity<ProgrammeStaffDto> getProgrammeStaff(@PathVariable("programmeID") String programmeID, @PathVariable("staffID") String staffID){
        ProgrammeStaffCompositeKey targetKey = new ProgrammeStaffCompositeKey(programmeID, staffID);
        ProgrammeStaffDto programmeStaffDto = programmeStaffService.getProgrammeStaff(targetKey);
        return ResponseEntity.ok(programmeStaffDto);
    }

    @GetMapping("/by-staff/{staffID}")
    public ResponseEntity<List<ProgrammeStaffDto>> getProgrammeStaffByStaffID(@PathVariable("staffID") String staffID){
        List<ProgrammeStaffDto> programmeStaffDtos = programmeStaffService.getProgrammeStaffByStaffID(staffID);
        return ResponseEntity.ok(programmeStaffDtos);
    }

    @GetMapping("/by-programme/{programmeID}")
    public ResponseEntity<List<ProgrammeStaffDto>> getProgrammeStaffByProgrammeID(@PathVariable("programmeID") String programmeID){
        List<ProgrammeStaffDto> programmeStaffDtos = programmeStaffService.getProgrammeStaffDtoByProgrammeID(programmeID);
        return ResponseEntity.ok(programmeStaffDtos);
    }

    @PutMapping("/{programmeID}/{staffID}")
    public ResponseEntity<ProgrammeStaffDto> updateProgrammeStaff(@PathVariable("programmeID") String programmeID, @PathVariable("staffID") String staffID, @RequestBody ProgrammeStaffDto updatedProgrammeStaffDto){
        System.out.println(updatedProgrammeStaffDto);
        ProgrammeStaffCompositeKey targetKey = new ProgrammeStaffCompositeKey(programmeID, staffID);
        System.out.println("Target key:");
        System.out.println(targetKey.getProgrammeID());
        System.out.println(targetKey.getStaffID());
        ProgrammeStaffDto savedProgrammeStaff = programmeStaffService.updateProgrammeStaff(targetKey, updatedProgrammeStaffDto);
        System.out.println("this is saved progstaff");
        System.out.println(savedProgrammeStaff);
        return ResponseEntity.ok(savedProgrammeStaff);
    }

    @DeleteMapping("/{programmeID}/{staffID}")
    public ResponseEntity<String> deleteProgrammeStaff(@PathVariable("programmeID") String programmeID, @PathVariable("staffID") String staffID){
       ProgrammeStaffCompositeKey targetKey = new ProgrammeStaffCompositeKey(programmeID, staffID);
        programmeStaffService.deleteProgrammeStaff(targetKey);
        return ResponseEntity.ok("programmeStaff record deleted successfully");
    }
}
