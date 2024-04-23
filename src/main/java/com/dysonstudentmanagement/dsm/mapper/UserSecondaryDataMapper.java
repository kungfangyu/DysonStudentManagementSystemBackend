package com.dysonstudentmanagement.dsm.mapper;

import com.dysonstudentmanagement.dsm.dto.user.UserSecondaryDataDto;
import com.dysonstudentmanagement.dsm.entity.user.UserSecondaryData;

public class UserSecondaryDataMapper {

    public static UserSecondaryDataDto mapToUserSecondaryDataDto(UserSecondaryData userSecondaryData){
        return new UserSecondaryDataDto(
                userSecondaryData.getUserPrimaryData().getUserID(),
                userSecondaryData.getIdPhoto(),
                userSecondaryData.getTitle(),
                userSecondaryData.getMiddleNames(),
                userSecondaryData.getGender(),
                userSecondaryData.getEthnicity(),
                userSecondaryData.getAddress(),
                userSecondaryData.getPostcode()
        );
    }
    public static UserSecondaryData mapToUserSecondaryData(UserSecondaryDataDto userSecondaryDataDto){
        UserSecondaryData secondaryData = UserSecondaryData.builder()
                .userID(userSecondaryDataDto.getUserID())
                .idPhoto(userSecondaryDataDto.getIdPhoto())
                .title(userSecondaryDataDto.getTitle())
                .middleNames(userSecondaryDataDto.getMiddleNames())
                .gender(userSecondaryDataDto.getGender())
                .ethnicity(userSecondaryDataDto.getEthnicity())
                .address(userSecondaryDataDto.getAddress())
                .postcode(userSecondaryDataDto.getPostcode())
                .build();
        return secondaryData;
    }
}
