package com.dysonstudentmanagement.dsm.service;

import com.dysonstudentmanagement.dsm.dto.UserEmergencyContactDto;
import com.dysonstudentmanagement.dsm.entity.useremergencycontact.UserEmergencyContact;
import com.dysonstudentmanagement.dsm.entity.useremergencycontact.UserEmergencyContactCompositeKey;

import java.util.List;


public interface UserEmergencyContactService {
    UserEmergencyContactDto createUserEmergencyContact(UserEmergencyContactDto userEmergencyContactDto);

    List<UserEmergencyContactDto> getUserEmergencyContactByUserID(String userID);

    UserEmergencyContactDto updateUserEmergencyContact(UserEmergencyContactCompositeKey targetCompositeKey, UserEmergencyContactDto updatedContactDto);

    void deleteUserEmergencyContact(UserEmergencyContactCompositeKey targetKey);
}
