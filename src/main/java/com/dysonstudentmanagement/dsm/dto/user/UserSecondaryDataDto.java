package com.dysonstudentmanagement.dsm.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserSecondaryDataDto {
    private String userID;
    private String idPhoto;
    private String title;
    private String middleNames;
    private String gender;
    private String ethnicity;
    private String address;
    private String postcode;
}
