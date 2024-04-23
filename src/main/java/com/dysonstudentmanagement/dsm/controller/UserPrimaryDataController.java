package com.dysonstudentmanagement.dsm.controller;

import com.dysonstudentmanagement.dsm.dto.user.UserPrimaryDataDto;
import com.dysonstudentmanagement.dsm.entity.user.UserPrimaryData;
import com.dysonstudentmanagement.dsm.service.UserPrimaryDataService;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/userPrimaryData")
public class UserPrimaryDataController {

    private UserPrimaryDataService userPrimaryDataService;

    @PostMapping
    public ResponseEntity<UserPrimaryDataDto> createUserPrimaryData(@RequestBody UserPrimaryDataDto userPrimaryDataDto){
        UserPrimaryDataDto savedUserPrimaryData = userPrimaryDataService.createUserPrimaryData(userPrimaryDataDto);
        return new ResponseEntity<>(savedUserPrimaryData, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserPrimaryDataDto> getUserPrimaryDataByID(@PathVariable("id") String userID){
        UserPrimaryDataDto userPrimaryData = userPrimaryDataService.getUserPrimaryDataById(userID);
        return ResponseEntity.ok(userPrimaryData);
    }

    @GetMapping
    public ResponseEntity<List<UserPrimaryDataDto>> getAllUserPrimaryData(){
        List<UserPrimaryDataDto> userPrimaryDatas = userPrimaryDataService.getAllUserPrimaryData();
        return ResponseEntity.ok(userPrimaryDatas);
    }

    @GetMapping("/by-ids")
    public ResponseEntity<List<UserPrimaryDataDto>> getCollectionUserPrimaryDataByIDs(@RequestBody Collection<String> userIDs){ //JSON format example: ["SAWIZNES", "JODOORYD"]. Should contain no {}
        List<UserPrimaryDataDto> userPrimaryDatas = userPrimaryDataService.getCollectionUserPrimaryDataByIDs(userIDs);
        return ResponseEntity.ok(userPrimaryDatas);
    }

    @GetMapping("/byUserType/{userType}")
    public ResponseEntity<List<UserPrimaryDataDto>> getUserPrimaryDataByUserType(@PathVariable("userType")UserPrimaryData.UserType userType){
        List<UserPrimaryDataDto> userPrimaryDatas = userPrimaryDataService.getAllUserPrimaryDataByUserType(userType);
        return ResponseEntity.ok(userPrimaryDatas);
    }

    @PutMapping("{id}")
    public ResponseEntity<UserPrimaryDataDto> updateUserPrimaryData(@PathVariable("id") String userID, @RequestBody UserPrimaryDataDto updatedData){
        UserPrimaryDataDto savedData = userPrimaryDataService.updateUserPrimaryData(userID, updatedData);
        return ResponseEntity.ok(savedData);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUserPrimaryData(@PathVariable("id") String userID){
        userPrimaryDataService.deleteEmployee(userID);
        return ResponseEntity.ok(userID+" deleted successfully");
    }
}
