package com.dysonstudentmanagement.dsm.service.impl;

import com.dysonstudentmanagement.dsm.dto.UserEmergencyContactDto;
import com.dysonstudentmanagement.dsm.entity.user.UserPrimaryData;
import com.dysonstudentmanagement.dsm.entity.useremergencycontact.UserEmergencyContact;
import com.dysonstudentmanagement.dsm.entity.useremergencycontact.UserEmergencyContactCompositeKey;
import com.dysonstudentmanagement.dsm.exception.ResourceNotFoundException;
import com.dysonstudentmanagement.dsm.mapper.UserEmergencyContactMapper;
import com.dysonstudentmanagement.dsm.repository.UserEmergencyContactRepository;
import com.dysonstudentmanagement.dsm.repository.UserPrimaryDataRepository;
import com.dysonstudentmanagement.dsm.service.UserEmergencyContactService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
/*
UserEmergencyContactServiceImpl

Class that implements service methods that act upon UserEmergencyContact table in database

Original Author: Billy Peters 24/04/2024
 */
public class UserEmergencyContactServiceImpl implements UserEmergencyContactService {
    private UserEmergencyContactRepository userEmergencyContactRepo;
    private UserPrimaryDataRepository userPrimaryDataRepo;
    @Override
    public UserEmergencyContactDto createUserEmergencyContact(UserEmergencyContactDto userEmergencyContactDto) {
        UserEmergencyContact userEmergencyContact = UserEmergencyContactMapper.mapToUserEmergencyContact(userEmergencyContactDto);
        UserEmergencyContactCompositeKey userEmergencyContactCompositeKey = new UserEmergencyContactCompositeKey(userEmergencyContact.getUserID(),
                userEmergencyContact.getContactPriority()
        );

        UserPrimaryData userPrimaryData = userPrimaryDataRepo.findById(userEmergencyContactDto.getUserID())
                .orElseThrow(() -> new DataIntegrityViolationException("Failed...UserID does not exist in foreign key table 'UserPrimaryData'")
                );
        Optional<UserEmergencyContact> userEmergencyContactExists = userEmergencyContactRepo.findById(userEmergencyContactCompositeKey);
        if(userEmergencyContactExists.isPresent()){
            throw new DataIntegrityViolationException("Failed...Record already exists for given primary key");
        }else{
            userEmergencyContact.setUserPrimaryData(userPrimaryData);
            //System.out.println(userEmergencyContact.toString());
            //userEmergencyContact.setUserID(userPrimaryData.getUserID());
            UserEmergencyContact savedData = userEmergencyContactRepo.save(userEmergencyContact);
            return UserEmergencyContactMapper.mapToUserEmergencyContactDto(savedData);
        }
    }

    @Override
    public List<UserEmergencyContactDto> getUserEmergencyContactByUserID(String userID) {
        List<UserEmergencyContact> userEmergencyContacts = userEmergencyContactRepo.findByUserID(userID);
        return userEmergencyContacts.stream().map((userEmergencyContact) -> UserEmergencyContactMapper.mapToUserEmergencyContactDto(userEmergencyContact)).collect(Collectors.toList());
    }

    @Override
    public UserEmergencyContactDto updateUserEmergencyContact(UserEmergencyContactCompositeKey targetCompositeKey, UserEmergencyContactDto updatedContactDto) {
        UserEmergencyContact userEmergencyContact = userEmergencyContactRepo.findById(targetCompositeKey)
                .orElseThrow( () -> new ResourceNotFoundException("UserEmergencyContact record not found with primary key provided")
                );

        UserEmergencyContact updatedContact = UserEmergencyContactMapper.mapToUserEmergencyContact(updatedContactDto);
        updatedContact.setUserID(userEmergencyContact.getUserID());
        updatedContact.setContactPriority(userEmergencyContact.getContactPriority());
        UserEmergencyContact savedEmergencyContact = userEmergencyContactRepo.save(updatedContact);
        return UserEmergencyContactMapper.mapToUserEmergencyContactDto(savedEmergencyContact);
    }

    @Override
    public void deleteUserEmergencyContact(UserEmergencyContactCompositeKey targetKey) {
        UserEmergencyContact userEmergencyContact = userEmergencyContactRepo.findById(targetKey)
                .orElseThrow( () -> new ResourceNotFoundException("UserEmergencyContact record not found with primary key provided")
                );
        userEmergencyContactRepo.deleteById(targetKey);
    }
}
