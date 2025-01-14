package com.dysonstudentmanagement.dsm.mapper;

import com.dysonstudentmanagement.dsm.dto.UserEmergencyContactDto;
import com.dysonstudentmanagement.dsm.entity.useremergencycontact.UserEmergencyContact;

/*
    Class: UserEmergencyContactMapper
    Mapper class responsible for mapping between UserEmergencyContact and UserEmergencyContactDto objects.
    Original Author: Billy Peters 24/04/2024
 */
public class UserEmergencyContactMapper {

    public static UserEmergencyContactDto mapToUserEmergencyContactDto(UserEmergencyContact userEmergencyContact){
        return new UserEmergencyContactDto(
          userEmergencyContact.getUserID(),
          userEmergencyContact.getContactPriority(),
          userEmergencyContact.getFirstName(),
          userEmergencyContact.getLastName(),
          userEmergencyContact.getTitle(),
          userEmergencyContact.getEmail(),
          userEmergencyContact.getPhone(),
          userEmergencyContact.getRelation(),
          userEmergencyContact.getAddress(),
          userEmergencyContact.getPostcode()
        );
    }

    public static UserEmergencyContact mapToUserEmergencyContact(UserEmergencyContactDto userEmergencyContactDto){
        UserEmergencyContact userEmergencyContact = UserEmergencyContact.builder()
                .userID(userEmergencyContactDto.getUserID())
                .contactPriority(userEmergencyContactDto.getContactPriority())
                .firstName(userEmergencyContactDto.getFirstName())
                .lastName(userEmergencyContactDto.getLastName())
                .title(userEmergencyContactDto.getTitle())
                .email(userEmergencyContactDto.getEmail())
                .phone(userEmergencyContactDto.getPhone())
                .relation(userEmergencyContactDto.getRelation())
                .address(userEmergencyContactDto.getAddress())
                .postcode(userEmergencyContactDto.getPostcode())
                .build();
        return userEmergencyContact;
    }
}
