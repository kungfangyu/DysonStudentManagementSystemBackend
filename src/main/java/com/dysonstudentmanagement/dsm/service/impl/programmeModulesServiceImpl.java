package com.dysonstudentmanagement.dsm.service.impl;

import com.dysonstudentmanagement.dsm.dto.programmeModulesDto;
import com.dysonstudentmanagement.dsm.entity.ProgrammModules.programmeModules;
import com.dysonstudentmanagement.dsm.entity.ProgrammModules.programmeModulesCompositeKey;
import com.dysonstudentmanagement.dsm.entity.Programme.programme;
import com.dysonstudentmanagement.dsm.entity.moduledetails.ModuleDetails;
import com.dysonstudentmanagement.dsm.exception.ResourceNotFoundException;
import com.dysonstudentmanagement.dsm.mapper.programmeModulesMapper;
import com.dysonstudentmanagement.dsm.repository.ModuleDetailsRepository;
import com.dysonstudentmanagement.dsm.repository.programmeModulesRepository;
import com.dysonstudentmanagement.dsm.repository.programmeRepository;

import com.dysonstudentmanagement.dsm.service.ProgrammeModulesService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class programmeModulesServiceImpl implements ProgrammeModulesService {

    private programmeRepository programmeRepo;

    private ModuleDetailsRepository moduleReo;

    private programmeModulesRepository programmeModulesServiceReop;



    @Override
    public programmeModulesDto createProgrammeModules (programmeModulesDto programmeModulesDto){
        programmeModules programmeModules = programmeModulesMapper.mapToProgrammeModules(programmeModulesDto);

        ModuleDetails module = moduleReo.findById(programmeModules.getModuleID())
                .orElseThrow(() -> new DataIntegrityViolationException("Failed...ProgrammeID does not exist in foreign key table 'programme"));
        programmeModules.setModuleDetails(module);


        programme programme = programmeRepo.findById(programmeModules.getProgrammeID())
                .orElseThrow(() -> new DataIntegrityViolationException("Failed...ProgrammeID does not exist in foreign key table 'programme"));
        programmeModules.setProgramme(programme);

        programmeModulesCompositeKey targetKey = new programmeModulesCompositeKey(programmeModules.getModuleID(), programmeModules.getProgrammeID());

        Optional<programmeModules> programmeExists = programmeModulesServiceReop.findById(targetKey);

        if(programmeExists.isPresent()){
            throw new DataIntegrityViolationException("Failed...Record already exists for given primary key");
        } else {
            programmeModules savedData = programmeModulesServiceReop.save(programmeModules);
            return programmeModulesMapper.mapToProgrammeModulesDto(savedData);
        }
    }


     @Override
        public List<programmeModulesDto> getAllProgrammeModules() {
            List<programmeModules> programmes = programmeModulesServiceReop.findAll();
            return programmes.stream()
                    .map(programmeModulesMapper::mapToProgrammeModulesDto)
                    .collect(Collectors.toList());
        }


        @Override
        public programmeModulesDto getProgrammeModules(programmeModulesCompositeKey targetKey) {
            programmeModules programmeModules = programmeModulesServiceReop.findById(targetKey)
                    .orElseThrow(() -> new ResourceNotFoundException("Programme Modules record not found with provided key"));
            return programmeModulesMapper.mapToProgrammeModulesDto(programmeModules);
        }
        @Override
        public List<programmeModulesDto> getProgrammeModulesDtoByModulesID(String moduleID){
        List<programmeModules> programmeModulesList = programmeModulesServiceReop.findByModuleID(moduleID);
        return programmeModulesList.stream()
                .map(programmeModulesMapper::mapToProgrammeModulesDto)
                .collect(Collectors.toList());
        }

        @Override
        public List<programmeModulesDto> getProgrammeModulesDtoByProgrammeID(String ProgrammeID){
            List<programmeModules> programmeModulesList = programmeModulesServiceReop.findByProgrammeID(ProgrammeID);
            return programmeModulesList.stream()
                    .map(programmeModulesMapper::mapToProgrammeModulesDto)
                    .collect(Collectors.toList());
        }



        @Override
        public programmeModulesDto updateProgrammeModules(programmeModulesCompositeKey targetKey, programmeModulesDto updateProgrammeModulesDto){

        programmeModules programmeModules = programmeModulesServiceReop.findById(targetKey)
                .orElseThrow(() -> new ResourceNotFoundException("programmeModules record not found with provided key"));

            programmeModulesServiceReop.delete(programmeModules);

            programmeModules newProgrammeModules = new programmeModules();
            newProgrammeModules.setModuleID(updateProgrammeModulesDto.getModuleID());
            newProgrammeModules.setProgrammeID(updateProgrammeModulesDto.getProgrammeID());

            ModuleDetails moduleDetails = moduleReo.findById(newProgrammeModules.getModuleID())
                    .orElseThrow(() -> new DataIntegrityViolationException("Failed...getModuleID does not exist in foreign key table 'programme'"));
            newProgrammeModules.setModuleDetails(moduleDetails);


            programme programme = programmeRepo.findById(newProgrammeModules.getProgrammeID())
                    .orElseThrow(() -> new DataIntegrityViolationException("Failed...ProgrammeID does not exist in foreign key table 'programme'"));
            newProgrammeModules.setProgramme(programme);


            programmeModules savedData = programmeModulesServiceReop.save(newProgrammeModules);

            // Map the saved programmeStaff entity to DTO and return it
            return programmeModulesMapper.mapToProgrammeModulesDto(savedData);

        }

    @Override
    public void deleteProgrammeModules(programmeModulesCompositeKey targetKey) {
        programmeModules programmeModules = programmeModulesServiceReop.findById(targetKey)
                .orElseThrow(() -> new ResourceNotFoundException("Programme Modules record not found with provided key"));
        programmeModulesServiceReop.delete(programmeModules);
    }






}


