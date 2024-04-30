package com.dysonstudentmanagement.dsm.controller;

import com.dysonstudentmanagement.dsm.dto.ProgrammeDto;
import com.dysonstudentmanagement.dsm.service.ProgrammeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@AllArgsConstructor
@RestController
@RequestMapping("/api/programmes")
public class ProgrammeController {
    private ProgrammeService programmeService;

    @PostMapping
    public ResponseEntity<ProgrammeDto> createProgramme(@RequestBody ProgrammeDto programmeDto) {
        ProgrammeDto createdProgramme = programmeService.createProgramme(programmeDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProgramme);
    }


    @GetMapping("/{programmeID}")
    public ResponseEntity<ProgrammeDto> getProgramme(@PathVariable String programmeID) {
        ProgrammeDto programmeDto = programmeService.getProgramme(programmeID);
        return ResponseEntity.ok(programmeDto);
    }

    @GetMapping
    public ResponseEntity<List<ProgrammeDto>> getAllProgrammes() {
        List<ProgrammeDto> programmes = programmeService.getAllProgrammes();
        return ResponseEntity.ok(programmes);
    }

    @PutMapping("/{programmeID}")
    public ResponseEntity<ProgrammeDto> updateProgramme(@PathVariable String programmeID, @RequestBody ProgrammeDto updatedProgrammeDto) {
        ProgrammeDto updatedProgramme = programmeService.updateProgramme(programmeID, updatedProgrammeDto);
        return ResponseEntity.ok(updatedProgramme);
    }

    @DeleteMapping("/{programmeID}")
    public ResponseEntity<Void> deleteProgramme(@PathVariable String programmeID) {
        programmeService.deleteProgramme(programmeID);
        return ResponseEntity.noContent().build();
    }
}
