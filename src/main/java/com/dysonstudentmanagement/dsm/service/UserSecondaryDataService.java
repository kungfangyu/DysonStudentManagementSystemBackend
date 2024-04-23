package com.dysonstudentmanagement.dsm.service;

import com.dysonstudentmanagement.dsm.dto.user.UserSecondaryDataDto;

public interface UserSecondaryDataService {
    UserSecondaryDataDto createUserSecondaryData(UserSecondaryDataDto userSecondaryDataDto);

    UserSecondaryDataDto getUserSecondaryDataById(String userID);

    UserSecondaryDataDto updateUserSecondaryData(String userID, UserSecondaryDataDto updatedSecondaryData);

    void deleteEmployee(String userID);


}
