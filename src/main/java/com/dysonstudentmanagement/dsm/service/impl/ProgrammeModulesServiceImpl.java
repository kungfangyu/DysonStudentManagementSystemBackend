package com.dysonstudentmanagement.dsm.service.impl;

import com.dysonstudentmanagement.dsm.dto.ProgrammeModulesDto;
import com.dysonstudentmanagement.dsm.entity.programmemodules.ProgrammeModules;
import com.dysonstudentmanagement.dsm.entity.programmemodules.ProgrammeModulesCompositeKey;
import com.dysonstudentmanagement.dsm.entity.programme.Programme;
import com.dysonstudentmanagement.dsm.entity.moduledetails.ModuleDetails;
import com.dysonstudentmanagement.dsm.exception.ResourceNotFoundException;
import com.dysonstudentmanagement.dsm.mapper.ProgrammeModulesMapper;
import com.dysonstudentmanagement.dsm.repository.ModuleDetailsRepository;
import com.dysonstudentmanagement.dsm.repository.ProgrammeModulesRepository;
import com.dysonstudentmanagement.dsm.repository.ProgrammeRepository;

import com.dysonstudentmanagement.dsm.service.ProgrammeModulesService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ProgrammeModulesServiceImpl implements ProgrammeModulesService {

    private ProgrammeRepository programmeRepo;

    private ModuleDetailsRepository moduleReo;

    private ProgrammeModulesRepository programmeModulesServiceReop;



    @Override
    public ProgrammeModulesDto createProgrammeModules (ProgrammeModulesDto programmeModulesDto){
        ProgrammeModules programmeModules = ProgrammeModulesMapper.mapToProgrammeModules(programmeModulesDto);

        ModuleDetails module = moduleReo.findById(programmeModules.getModuleID())
                .orElseThrow(() -> new DataIntegrityViolationException("Failed...ProgrammeID does not exist in foreign key table 'programme"));
        programmeModules.setModuleDetails(module);


        Programme programme = programmeRepo.findById(programmeModules.getProgrammeID())
                .orElseThrow(() -> new DataIntegrityViolationException("Failed...ProgrammeID does not exist in foreign key table 'programme"));
        programmeModules.setProgramme(programme);

        ProgrammeModulesCompositeKey targetKey = new ProgrammeModulesCompositeKey(programmeModules.getModuleID(), programmeModules.getProgrammeID());

        Optional<ProgrammeModules> programmeExists = programmeModulesServiceReop.findById(targetKey);

        if(programmeExists.isPresent()){
            throw new DataIntegrityViolationException("Failed...Record already exists for given primary key");
        } else {
            ProgrammeModules savedData = programmeModulesServiceReop.save(programmeModules);
            return ProgrammeModulesMapper.mapToProgrammeModulesDto(savedData);
        }
    }


     @Override
        public List<ProgrammeModulesDto> getAllProgrammeModules() {
            List<ProgrammeModules> programmes = programmeModulesServiceReop.findAll();
            return programmes.stream()
                    .map(ProgrammeModulesMapper::mapToProgrammeModulesDto)
                    .collect(Collectors.toList());
        }


        @Override
        public ProgrammeModulesDto getProgrammeModules(ProgrammeModulesCompositeKey targetKey) {
            ProgrammeModules programmeModules = programmeModulesServiceReop.findById(targetKey)
                    .orElseThrow(() -> new ResourceNotFoundException("Programme Modules record not found with provided key"));
            return ProgrammeModulesMapper.mapToProgrammeModulesDto(programmeModules);
        }
        @Override
        public List<ProgrammeModulesDto> getProgrammeModulesDtoByModulesID(String moduleID){
        List<ProgrammeModules> programmeModulesList = programmeModulesServiceReop.findByModuleID(moduleID);
        return programmeModulesList.stream()
                .map(ProgrammeModulesMapper::mapToProgrammeModulesDto)
                .collect(Collectors.toList());
        }

        @Override
        public List<ProgrammeModulesDto> getProgrammeModulesDtoByProgrammeID(String ProgrammeID){
            List<ProgrammeModules> programmeModulesList = programmeModulesServiceReop.findByProgrammeID(ProgrammeID);
            return programmeModulesList.stream()
                    .map(ProgrammeModulesMapper::mapToProgrammeModulesDto)
                    .collect(Collectors.toList());
        }



        @Override
        public ProgrammeModulesDto updateProgrammeModules(ProgrammeModulesCompositeKey targetKey, ProgrammeModulesDto updateProgrammeModulesDto){

        ProgrammeModules programmeModules = programmeModulesServiceReop.findById(targetKey)
                .orElseThrow(() -> new ResourceNotFoundException("programmeModules record not found with provided key"));

            programmeModulesServiceReop.delete(programmeModules);

            ProgrammeModules newProgrammeModules = new ProgrammeModules();
            newProgrammeModules.setModuleID(updateProgrammeModulesDto.getModuleID());
            newProgrammeModules.setProgrammeID(updateProgrammeModulesDto.getProgrammeID());

            ModuleDetails moduleDetails = moduleReo.findById(newProgrammeModules.getModuleID())
                    .orElseThrow(() -> new DataIntegrityViolationException("Failed...getModuleID does not exist in foreign key table 'programme'"));
            newProgrammeModules.setModuleDetails(moduleDetails);


            Programme programme = programmeRepo.findById(newProgrammeModules.getProgrammeID())
                    .orElseThrow(() -> new DataIntegrityViolationException("Failed...ProgrammeID does not exist in foreign key table 'programme'"));
            newProgrammeModules.setProgramme(programme);


            ProgrammeModules savedData = programmeModulesServiceReop.save(newProgrammeModules);

            // Map the saved programmeStaff entity to DTO and return it
            return ProgrammeModulesMapper.mapToProgrammeModulesDto(savedData);

        }

    @Override
    public void deleteProgrammeModules(ProgrammeModulesCompositeKey targetKey) {
        ProgrammeModules programmeModules = programmeModulesServiceReop.findById(targetKey)
                .orElseThrow(() -> new ResourceNotFoundException("Programme Modules record not found with provided key"));
        programmeModulesServiceReop.delete(programmeModules);
    }






}


