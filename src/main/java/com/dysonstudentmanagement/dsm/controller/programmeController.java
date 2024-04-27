package com.dysonstudentmanagement.dsm.controller;

import com.dysonstudentmanagement.dsm.dto.programmeDto;
import com.dysonstudentmanagement.dsm.service.ProgrammeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@AllArgsConstructor
@RestController
@RequestMapping("/api/programmes")
public class programmeController {
    private ProgrammeService programmeService;

    @PostMapping
    public ResponseEntity<programmeDto> createProgramme(@RequestBody programmeDto programmeDto) {
        com.dysonstudentmanagement.dsm.dto.programmeDto createdProgramme = programmeService.createProgramme(programmeDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProgramme);
    }


    @GetMapping("/{programmeID}")
    public ResponseEntity<programmeDto> getProgramme(@PathVariable String programmeID) {
        programmeDto programmeDto = programmeService.getProgramme(programmeID);
        return ResponseEntity.ok(programmeDto);
    }

    @GetMapping
    public ResponseEntity<List<programmeDto>> getAllProgrammes() {
        List<programmeDto> programmes = programmeService.getAllProgrammes();
        return ResponseEntity.ok(programmes);
    }

    @PutMapping("/{programmeID}")
    public ResponseEntity<programmeDto> updateProgramme(@PathVariable String programmeID, @RequestBody programmeDto updatedProgrammeDto) {
        programmeDto updatedProgramme = programmeService.updateProgramme(programmeID, updatedProgrammeDto);
        return ResponseEntity.ok(updatedProgramme);
    }

    @DeleteMapping("/{programmeID}")
    public ResponseEntity<Void> deleteProgramme(@PathVariable String programmeID) {
        programmeService.deleteProgramme(programmeID);
        return ResponseEntity.noContent().build();
    }
}
