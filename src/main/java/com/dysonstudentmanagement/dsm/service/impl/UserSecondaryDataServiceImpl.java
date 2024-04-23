package com.dysonstudentmanagement.dsm.service.impl;

import com.dysonstudentmanagement.dsm.dto.user.UserSecondaryDataDto;
import com.dysonstudentmanagement.dsm.entity.user.UserPrimaryData;
import com.dysonstudentmanagement.dsm.entity.user.UserSecondaryData;
import com.dysonstudentmanagement.dsm.exception.ResourceNotFoundException;
import com.dysonstudentmanagement.dsm.mapper.UserSecondaryDataMapper;
import com.dysonstudentmanagement.dsm.repository.UserPrimaryDataRepository;
import com.dysonstudentmanagement.dsm.repository.UserSecondaryDataRepository;
import com.dysonstudentmanagement.dsm.service.UserSecondaryDataService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserSecondaryDataServiceImpl implements UserSecondaryDataService {

    private UserSecondaryDataRepository userSecondaryDataRepo;

    private UserPrimaryDataRepository userPrimaryDataRepo;

    @Override
    public UserSecondaryDataDto createUserSecondaryData(UserSecondaryDataDto userSecondaryDataDto) {
        Optional<UserPrimaryData> userPrimaryData = userPrimaryDataRepo.findById(userSecondaryDataDto.getUserID());
        Optional<UserSecondaryData> userSecondaryData = userSecondaryDataRepo.findById(userSecondaryDataDto.getUserID());
        if(!userPrimaryData.isPresent()){
            throw new DataIntegrityViolationException("Failed...UserID does not exist in foreign key table 'UserPrimaryData'");
        } else if(userSecondaryData.isPresent()){
            throw new DataIntegrityViolationException("Failed...Record already exists for given UserID");
        }
        else{
            UserPrimaryData existingPrimaryData = userPrimaryData.get();
            UserSecondaryData newUserSecondaryData = UserSecondaryDataMapper.mapToUserSecondaryData(userSecondaryDataDto);
            newUserSecondaryData.setUserPrimaryData(existingPrimaryData);
            newUserSecondaryData.setUserID(existingPrimaryData.getUserID());
            //System.out.println(newUserSecondaryData.toString());
            UserSecondaryData savedData = userSecondaryDataRepo.save(newUserSecondaryData);
            return UserSecondaryDataMapper.mapToUserSecondaryDataDto(savedData);
        }
    }

    @Override
    public UserSecondaryDataDto getUserSecondaryDataById(String userID) {
        UserSecondaryData userSecondaryData = userSecondaryDataRepo.findById(userID)
                .orElseThrow(() -> new ResourceNotFoundException("UserSecondaryData record not found with ID: " + userID)
                );
        return UserSecondaryDataMapper.mapToUserSecondaryDataDto(userSecondaryData);
    }

    @Override
    public UserSecondaryDataDto updateUserSecondaryData(String userID, UserSecondaryDataDto updatedSecondaryData) {
        UserSecondaryData userSecondaryData = userSecondaryDataRepo.findById(userID)
                .orElseThrow(() -> new ResourceNotFoundException("UserSecondaryData record not found with ID: " + userID)
                );

        userSecondaryData.setIdPhoto(updatedSecondaryData.getIdPhoto());
        userSecondaryData.setTitle(updatedSecondaryData.getTitle());
        userSecondaryData.setMiddleNames(updatedSecondaryData.getMiddleNames());
        userSecondaryData.setGender(updatedSecondaryData.getGender());
        userSecondaryData.setEthnicity(updatedSecondaryData.getEthnicity());
        userSecondaryData.setAddress(updatedSecondaryData.getAddress());
        userSecondaryData.setPostcode(updatedSecondaryData.getPostcode());
        UserSecondaryData savedUserSecondaryData = userSecondaryDataRepo.save(userSecondaryData);
        return UserSecondaryDataMapper.mapToUserSecondaryDataDto(savedUserSecondaryData);
    }

    @Override
    public void deleteEmployee(String userID) {
        userSecondaryDataRepo.findById(userID)
                .orElseThrow(() -> new ResourceNotFoundException("UserSecondaryData record not found with ID: " + userID)
                );
        userSecondaryDataRepo.deleteById(userID);
    }

}
