package com.dysonstudentmanagement.dsm.controller;

import com.dysonstudentmanagement.dsm.dto.ModuleAnnouncementDto;
import com.dysonstudentmanagement.dsm.entity.moduleannouncement.ModuleAnnouncementCompositeKey;
import com.dysonstudentmanagement.dsm.service.ModuleAnnouncementService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/moduleAnnouncement")
public class ModuleAnnouncementController {
    private ModuleAnnouncementService moduleAnnouncementService;

    @PostMapping
    public ResponseEntity<ModuleAnnouncementDto> createPreviousQualification(@RequestBody ModuleAnnouncementDto moduleAnnouncementDto) {
        ModuleAnnouncementDto savedAnnouncement = moduleAnnouncementService.createModuleAnnouncement(moduleAnnouncementDto);
        return new ResponseEntity<>(savedAnnouncement, HttpStatus.CREATED);
    }

    @GetMapping("{moduleID}")
    public ResponseEntity<List<ModuleAnnouncementDto>> getModuleAnnouncementsByModuleId(@PathVariable("moduleID") String moduleID) {
        return ResponseEntity.ok(moduleAnnouncementService.getModuleAnnouncementsByModuleID(moduleID));
    }

    @GetMapping("{moduleID}/{announcementID}")
    public ResponseEntity<ModuleAnnouncementDto> getIndividualModuleAnnouncement(@PathVariable("moduleID") String moduleID,
                                                                             @PathVariable("announcementID") int announcementID){
        ModuleAnnouncementCompositeKey newAnnouncementCompositeKey = new ModuleAnnouncementCompositeKey(moduleID, announcementID);
        ModuleAnnouncementDto announcementDto = moduleAnnouncementService.getModuleAnnouncement(newAnnouncementCompositeKey);
        return ResponseEntity.ok(announcementDto);
    }

    @PutMapping("{moduleID}/{announcementID}")
    public ResponseEntity<ModuleAnnouncementDto> updateModuleAnnouncement(@PathVariable("moduleID") String moduleID,
                                                                                @PathVariable("announcementID") int announcementID,
                                                                                @RequestBody ModuleAnnouncementDto updateDto) {
        ModuleAnnouncementCompositeKey newAnnouncementCompositeKey = new ModuleAnnouncementCompositeKey(moduleID, announcementID);
        ModuleAnnouncementDto announcementDto = moduleAnnouncementService.updateModuleAnnouncement(newAnnouncementCompositeKey, updateDto);
        return ResponseEntity.ok(announcementDto);
    }

    @DeleteMapping("{moduleID}/{announcementID}")
    public ResponseEntity<String> deletePreviousQualification(@PathVariable("moduleID") String moduleID,
                                                              @PathVariable("announcementID") int announcementID) {
        ModuleAnnouncementCompositeKey newAnnouncementCompositeKey = new ModuleAnnouncementCompositeKey(moduleID, announcementID);
        moduleAnnouncementService.deleteModuleDetails(newAnnouncementCompositeKey);
        return ResponseEntity.ok(moduleID + " " + announcementID + ": Deleted Succesfully");
    }
}
