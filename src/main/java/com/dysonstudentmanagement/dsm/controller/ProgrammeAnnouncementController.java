package com.dysonstudentmanagement.dsm.controller;

import com.dysonstudentmanagement.dsm.dto.ProgrammeAnnouncementDto;
import com.dysonstudentmanagement.dsm.entity.programmeannouncement.ProgrammeAnnouncementCompositeKey;
import com.dysonstudentmanagement.dsm.service.ProgrammeAnnouncementService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/programmeAnnouncement")
/*
ProgrammeAnnouncementController

Provides api mappings to access programmeAnnouncementService methods.

Original Author: Tianpu Li 25/04/2024
 */
public class ProgrammeAnnouncementController {
    private ProgrammeAnnouncementService programmeAnnouncementService;

    @PostMapping
    public ResponseEntity<ProgrammeAnnouncementDto> createProgrammeAnnouncement(@RequestBody ProgrammeAnnouncementDto programmeAnnouncementDto) {
        ProgrammeAnnouncementDto savedAnnouncement = programmeAnnouncementService.createProgrammeAnnouncement(programmeAnnouncementDto);
        return new ResponseEntity<>(savedAnnouncement, HttpStatus.CREATED);
    }

    @GetMapping("{programmeId}")
    public ResponseEntity<List<ProgrammeAnnouncementDto>> getProgrammeAnnouncementByProgrammeId(@PathVariable("programmeId") String programmeId) {
        return ResponseEntity.ok(programmeAnnouncementService.getProgrammeAnnouncementsByProgrammeId(programmeId));
    }

    @GetMapping("{programmeId}/{announcementId}")
    public ResponseEntity<ProgrammeAnnouncementDto> getIndividualProgrammeAnnouncement(@PathVariable("programmeId") String programmeId, @PathVariable("announcementId") int announcementId) {
        ProgrammeAnnouncementCompositeKey pk = new ProgrammeAnnouncementCompositeKey(programmeId, announcementId);
        return ResponseEntity.ok(programmeAnnouncementService.getProgrammeAnnouncement(pk));
    }

    @PutMapping("{programmeId}/{announcementId}")
    public ResponseEntity<ProgrammeAnnouncementDto> updateProgrammeAnnouncement(@PathVariable("programmeId") String programmeId,
                                                                                @PathVariable("announcementId") int announcementId,
                                                                                @RequestBody ProgrammeAnnouncementDto programmeAnnouncementDto) {
        ProgrammeAnnouncementCompositeKey pk = new ProgrammeAnnouncementCompositeKey(programmeId, announcementId);
        return ResponseEntity.ok(programmeAnnouncementService.updateProgrammeAnnouncement(pk, programmeAnnouncementDto));
    }

    @DeleteMapping("{programmeId}/{announcementId}")
    public ResponseEntity<String> deleteProgrammeAnnouncement(@PathVariable("programmeId") String programmeId,
                                                              @PathVariable("announcementId") int announcementId) {
        ProgrammeAnnouncementCompositeKey pk = new ProgrammeAnnouncementCompositeKey(programmeId, announcementId);
        programmeAnnouncementService.deleteProgrammeAnnouncement(pk);
        return new ResponseEntity<>("Programme announcement deleted", HttpStatus.OK);
    }
}
