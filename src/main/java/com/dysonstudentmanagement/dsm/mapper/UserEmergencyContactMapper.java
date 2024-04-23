package com.dysonstudentmanagement.dsm.mapper;

import com.dysonstudentmanagement.dsm.dto.UserEmergencyContactDto;
import com.dysonstudentmanagement.dsm.entity.useremergencycontact.UserEmergencyContact;

public class UserEmergencyContactMapper {

    public static UserEmergencyContactDto mapToUserEmergencyContactDto(UserEmergencyContact userEmergencyContact){
        return new UserEmergencyContactDto(
          userEmergencyContact.getUserID(),
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
