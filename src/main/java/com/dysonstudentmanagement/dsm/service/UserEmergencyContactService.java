package com.dysonstudentmanagement.dsm.service;

import com.dysonstudentmanagement.dsm.dto.UserEmergencyContactDto;
import com.dysonstudentmanagement.dsm.entity.useremergencycontact.UserEmergencyContact;
import com.dysonstudentmanagement.dsm.entity.useremergencycontact.UserEmergencyContactCompositeKey;

import java.util.List;


/*
UserEmergencyContactService

Interface that declares service methods that act upon UserEmergencyContact table in database

Original Author: Billy Peters 24/04/2024
 */
public interface UserEmergencyContactService {
    UserEmergencyContactDto createUserEmergencyContact(UserEmergencyContactDto userEmergencyContactDto);

    List<UserEmergencyContactDto> getUserEmergencyContactByUserID(String userID);

    UserEmergencyContactDto updateUserEmergencyContact(UserEmergencyContactCompositeKey targetCompositeKey, UserEmergencyContactDto updatedContactDto);

    void deleteUserEmergencyContact(UserEmergencyContactCompositeKey targetKey);
}
