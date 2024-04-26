package com.dysonstudentmanagement.dsm.service.impl;

import com.dysonstudentmanagement.dsm.dto.ModuleAnnouncementDto;
import com.dysonstudentmanagement.dsm.entity.moduleannouncement.ModuleAnnouncement;
import com.dysonstudentmanagement.dsm.entity.moduleannouncement.ModuleAnnouncementCompositeKey;
import com.dysonstudentmanagement.dsm.entity.moduledetails.ModuleDetails;
import com.dysonstudentmanagement.dsm.entity.user.UserPrimaryData;
import com.dysonstudentmanagement.dsm.exception.ResourceNotFoundException;
import com.dysonstudentmanagement.dsm.mapper.ModuleAnnouncementMapper;
import com.dysonstudentmanagement.dsm.repository.ModuleAnnouncementRepository;
import com.dysonstudentmanagement.dsm.repository.ModuleDetailsRepository;
import com.dysonstudentmanagement.dsm.repository.UserPrimaryDataRepository;
import com.dysonstudentmanagement.dsm.service.ModuleAnnouncementService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/*
    Class: ModuleAnnouncementServiceImpl
    Class is intended to handle data management in the database which respond to API methods.
    Original Author: Jack Burnett, 25/04/2024
*/
@AllArgsConstructor
@Service
public class ModuleAnnouncementServiceImpl implements ModuleAnnouncementService {
    private ModuleAnnouncementRepository moduleAnnouncementRepo;
    private ModuleDetailsRepository moduleDetailsRepo;
    private UserPrimaryDataRepository userPrimaryDataRepo;

    /*
        Method: createModuleAnnouncement
        Method intended to validate data before saving to database, returns Dto object to request.
        Original Author: Jack Burnett, 25/04/2024
    */
    @Override
    public ModuleAnnouncementDto createModuleAnnouncement(ModuleAnnouncementDto moduleDetailsDto) {
        ModuleAnnouncement moduleAnnouncement = ModuleAnnouncementMapper.mapToModuleAnnouncement(moduleDetailsDto);


        //checks for valid foreign keys
        ModuleDetails moduleDetails = moduleDetailsRepo.findById(moduleAnnouncement.getModuleID())
                .orElseThrow(() -> new DataIntegrityViolationException("Failed... ModuleID does not exist in target table ModuleDetails")
                );
        UserPrimaryData userPrimaryData = userPrimaryDataRepo.findById(moduleAnnouncement.getStaffId())
                .orElseThrow(() -> new DataIntegrityViolationException("Failed... StaffID does not exist in target table UserPrimaryDataRepository")
                );

//        Commented out when announcement ID was set to autoincrement.
//        Optional<ModuleAnnouncement> moduleAnnouncementExists = moduleAnnouncementRepo.findById(newAnnouncementCompositeKey);
//        if(moduleAnnouncementExists.isPresent()){
//            throw new DataIntegrityViolationException("Failed...Record already exists for given primary key");
//        }else{
        moduleAnnouncement.setModuleDetails(moduleDetails);
        moduleAnnouncement.setUserPrimaryData(userPrimaryData);
        ModuleAnnouncement savedData = moduleAnnouncementRepo.save(moduleAnnouncement);
        return ModuleAnnouncementMapper.mapToModuleAnnouncementDto(savedData);

    }

    @Override
    public ModuleAnnouncementDto getModuleAnnouncement(ModuleAnnouncementCompositeKey targetKey) {
        ModuleAnnouncement moduleAnnouncement = moduleAnnouncementRepo.findById(targetKey)
                .orElseThrow(() -> new ResourceNotFoundException("Module Announcement record not found with primary key provided")
                );
        return ModuleAnnouncementMapper.mapToModuleAnnouncementDto(moduleAnnouncement);
    }

    @Override
    public List<ModuleAnnouncementDto> getModuleAnnouncementsByModuleID(String moduleID) {
        List<ModuleAnnouncement> moduleAnnouncements = moduleAnnouncementRepo.findByModuleID(moduleID);
        return moduleAnnouncements.stream().map(ModuleAnnouncementMapper::mapToModuleAnnouncementDto).collect(Collectors.toList());

    }

    @Override
    public ModuleAnnouncementDto updateModuleAnnouncement(ModuleAnnouncementCompositeKey targetKey, ModuleAnnouncementDto updatedAnnouncementDto) {
        ModuleAnnouncement updatedAnnouncement = ModuleAnnouncementMapper.mapToModuleAnnouncement(updatedAnnouncementDto);
        ModuleAnnouncement moduleAnnouncement = moduleAnnouncementRepo.findById(targetKey)
                .orElseThrow(() -> new ResourceNotFoundException("Module Announcement record not found with primary key provided")
                );
        moduleAnnouncement.setTitle(updatedAnnouncement.getTitle());
        moduleAnnouncement.setDescription(updatedAnnouncement.getDescription());
        moduleAnnouncement.setDatePosted(updatedAnnouncement.getDatePosted());
        ModuleAnnouncement savedData = moduleAnnouncementRepo.save(moduleAnnouncement);
        return ModuleAnnouncementMapper.mapToModuleAnnouncementDto(savedData);
    }

    @Override
    public void deleteModuleDetails(ModuleAnnouncementCompositeKey targetKey) {
        ModuleAnnouncement moduleAnnouncement = moduleAnnouncementRepo.findById(targetKey)
                .orElseThrow(() -> new ResourceNotFoundException("Module Announcement record not found with primary key provided")
                );
        moduleAnnouncementRepo.deleteById(targetKey);
    }
}
