package com.dysonstudentmanagement.dsm.service.impl;

import com.dysonstudentmanagement.dsm.dto.ProgrammeAnnouncementDto;
import com.dysonstudentmanagement.dsm.entity.programme.Programme;
import com.dysonstudentmanagement.dsm.entity.programmeannouncement.ProgrammeAnnouncement;
import com.dysonstudentmanagement.dsm.entity.programmeannouncement.ProgrammeAnnouncementCompositeKey;
import com.dysonstudentmanagement.dsm.entity.user.UserPrimaryData;
import com.dysonstudentmanagement.dsm.exception.ResourceNotFoundException;
import com.dysonstudentmanagement.dsm.mapper.ProgrammeAnnouncementMapper;
import com.dysonstudentmanagement.dsm.repository.ProgrammeAnnouncementRepository;
import com.dysonstudentmanagement.dsm.repository.ProgrammeRepository;
import com.dysonstudentmanagement.dsm.repository.UserPrimaryDataRepository;
import com.dysonstudentmanagement.dsm.service.ProgrammeAnnouncementService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ProgrammeAnnouncementServiceImpl implements ProgrammeAnnouncementService {
    private ProgrammeAnnouncementRepository programmeAnnouncementRepo;
    private ProgrammeRepository programmeRepo;
    private UserPrimaryDataRepository primaryDataRepo;

    @Override
    public ProgrammeAnnouncementDto createProgrammeAnnouncement(ProgrammeAnnouncementDto programmeAnnouncementDto) {
        ProgrammeAnnouncement programmeAnnouncement = ProgrammeAnnouncementMapper.mapToProgrammeAnnouncement(programmeAnnouncementDto);

        Programme programme = programmeRepo.findById(programmeAnnouncement.getProgrammeID())
                .orElseThrow(() -> new DataIntegrityViolationException("Failed... ModuleID does not exist in target table Programme"));
        UserPrimaryData userPrimaryData = primaryDataRepo.findById(programmeAnnouncement.getStaffID())
                .orElseThrow(() -> new DataIntegrityViolationException("Failed... StaffID does not exist in target table StaffDetails"));

        ProgrammeAnnouncementCompositeKey newRecordCompositeKey = new ProgrammeAnnouncementCompositeKey(
                programmeAnnouncement.getProgrammeID(),
                programmeAnnouncement.getAnnouncementID()
        );

        int announcementID = programmeAnnouncementRepo.findByProgrammeID(programmeAnnouncement.getProgrammeID()).size()+1;
        programmeAnnouncement.setAnnouncementID(announcementID); //manual incrementing of announcement ID as hibernate does not support autoincrementing of composite key columns

        Optional<ProgrammeAnnouncement> ProgrammeAnnouncementOptional = programmeAnnouncementRepo.findById(newRecordCompositeKey);
        if (ProgrammeAnnouncementOptional.isPresent()) {
            throw new DataIntegrityViolationException("Failed...Record already exists for given primary key");
        }else {
            programmeAnnouncement.setProgramme(programme);
            programmeAnnouncement.setUserPrimaryData(userPrimaryData);
            ProgrammeAnnouncement saveData = programmeAnnouncementRepo.save(programmeAnnouncement);
            return ProgrammeAnnouncementMapper.mapToProgrammeAnnouncementDto(saveData);
        }
    }

    @Override
    public ProgrammeAnnouncementDto getProgrammeAnnouncement(ProgrammeAnnouncementCompositeKey targetKey) {
        ProgrammeAnnouncement programmeAnnouncement = programmeAnnouncementRepo.findById(targetKey)
                .orElseThrow(() -> new ResourceNotFoundException("Programme Announcement record not found with primary key provided"));
        return ProgrammeAnnouncementMapper.mapToProgrammeAnnouncementDto(programmeAnnouncement);
    }

    @Override
    public List<ProgrammeAnnouncementDto> getProgrammeAnnouncementsByProgrammeId(String programmeId) {
        List<ProgrammeAnnouncement> programmeAnnouncements = programmeAnnouncementRepo.findByProgrammeID(programmeId);
        return programmeAnnouncements.stream().map(ProgrammeAnnouncementMapper::mapToProgrammeAnnouncementDto).collect(Collectors.toList());
    }

    @Override
    public ProgrammeAnnouncementDto updateProgrammeAnnouncement(ProgrammeAnnouncementCompositeKey targetKey, ProgrammeAnnouncementDto programmeAnnouncementDto) {
        ProgrammeAnnouncement programmeAnnouncement = programmeAnnouncementRepo.findById(targetKey)
                .orElseThrow(() -> new ResourceNotFoundException("Programme Announcement record not found with primary key provided"));
        programmeAnnouncement.setDescription(programmeAnnouncementDto.getDescription());
        programmeAnnouncement.setTitle(programmeAnnouncementDto.getTitle());
        ProgrammeAnnouncement saveData = programmeAnnouncementRepo.save(programmeAnnouncement);
        return ProgrammeAnnouncementMapper.mapToProgrammeAnnouncementDto(saveData);
    }

    @Override
    public void deleteProgrammeAnnouncement(ProgrammeAnnouncementCompositeKey targetKey) {
        ProgrammeAnnouncement programmeAnnouncement = programmeAnnouncementRepo.findById(targetKey)
                .orElseThrow(() -> new ResourceNotFoundException("Programme Announcement record not found with primary key provided"));
        programmeAnnouncementRepo.deleteById(targetKey);
    }
}
