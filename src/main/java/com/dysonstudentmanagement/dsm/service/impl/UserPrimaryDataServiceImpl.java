package com.dysonstudentmanagement.dsm.service.impl;

import com.dysonstudentmanagement.dsm.dto.user.UserPrimaryDataDto;
import com.dysonstudentmanagement.dsm.entity.user.UserPrimaryData;
import com.dysonstudentmanagement.dsm.exception.ResourceNotFoundException;
import com.dysonstudentmanagement.dsm.mapper.UserPrimaryDataMapper;
import com.dysonstudentmanagement.dsm.repository.UserPrimaryDataRepository;
import com.dysonstudentmanagement.dsm.service.UserPrimaryDataService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserPrimaryDataServiceImpl implements UserPrimaryDataService {

    private UserPrimaryDataRepository userPrimaryDataRepo;
    @Override
    public UserPrimaryDataDto createUserPrimaryData(UserPrimaryDataDto userPrimaryDataDto) {
        UserPrimaryData userPrimaryData = UserPrimaryDataMapper.mapToUserPrimaryData(userPrimaryDataDto);
        UserPrimaryData savedUserPrimaryData = userPrimaryDataRepo.save(userPrimaryData);
        return UserPrimaryDataMapper.mapToUserPrimaryDataDto(savedUserPrimaryData);
    }

    @Override
    public UserPrimaryDataDto getUserPrimaryDataById(String userID) {
        UserPrimaryData userPrimaryData = userPrimaryDataRepo.findById(userID)
                .orElseThrow( () -> new ResourceNotFoundException("UserPrimaryData record not found with ID: " + userID)
                );
        return UserPrimaryDataMapper.mapToUserPrimaryDataDto(userPrimaryData);
    }

    @Override
    public List<UserPrimaryDataDto> getAllUserPrimaryData() {
        List<UserPrimaryData> userPrimaryDatas= userPrimaryDataRepo.findAll();
        return userPrimaryDatas.stream().map((userPrimaryData) -> UserPrimaryDataMapper.mapToUserPrimaryDataDto(userPrimaryData)).collect(Collectors.toList());
    }

    @Override
    public List<UserPrimaryDataDto> getCollectionUserPrimaryDataByIDs(Collection<String> userIDs) {
        List<UserPrimaryData> userPrimaryDatas = new ArrayList<>();
        for(String userID:userIDs){
            userPrimaryDatas.add(userPrimaryDataRepo.findById(userID)
                    .orElseThrow( () -> new ResourceNotFoundException("UserPrimaryData record not found with ID: " + userID))
            );
        }
        return userPrimaryDatas.stream().map((userPrimaryData) -> UserPrimaryDataMapper.mapToUserPrimaryDataDto(userPrimaryData)).collect(Collectors.toList());
    }

    @Override
    public List<UserPrimaryDataDto> getAllUserPrimaryDataByUserType(UserPrimaryData.UserType userType) {
        List<UserPrimaryData> userPrimaryDatas = userPrimaryDataRepo.findByUserType(userType);
        return userPrimaryDatas.stream().map((userPrimaryData) -> UserPrimaryDataMapper.mapToUserPrimaryDataDto(userPrimaryData)).collect(Collectors.toList());
    }

    @Override
    public UserPrimaryDataDto updateUserPrimaryData(String userID, UserPrimaryDataDto updatedPrimaryDataDto) {
        UserPrimaryData updatedPrimaryData = UserPrimaryDataMapper.mapToUserPrimaryData(updatedPrimaryDataDto);
        UserPrimaryData userPrimaryData = userPrimaryDataRepo.findById(userID)
                .orElseThrow(() -> new ResourceNotFoundException("UserPrimaryData record not found with ID: " + userID)
                );

        userPrimaryData.setFirstName(updatedPrimaryData.getFirstName());
        userPrimaryData.setLastName(updatedPrimaryData.getLastName());
        userPrimaryData.setPersonalEmail(updatedPrimaryData.getPersonalEmail());
        userPrimaryData.setPhone(updatedPrimaryData.getPhone());
        UserPrimaryData savedUserPrimaryData = userPrimaryDataRepo.save(userPrimaryData);
        return UserPrimaryDataMapper.mapToUserPrimaryDataDto(savedUserPrimaryData);
    }

    @Override
    public void deleteEmployee(String userID) {
        userPrimaryDataRepo.findById(userID)
                .orElseThrow(() -> new ResourceNotFoundException("UserPrimaryData record not found with ID: " + userID)
                );
        userPrimaryDataRepo.deleteById(userID);
    }
}
