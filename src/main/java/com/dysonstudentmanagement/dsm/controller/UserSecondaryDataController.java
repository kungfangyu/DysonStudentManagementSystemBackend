package com.dysonstudentmanagement.dsm.controller;

import com.dysonstudentmanagement.dsm.dto.user.UserSecondaryDataDto;
import com.dysonstudentmanagement.dsm.service.UserSecondaryDataService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/userSecondaryData")
public class UserSecondaryDataController {

    private UserSecondaryDataService userSecondaryDataService;

    @PostMapping
    public ResponseEntity<UserSecondaryDataDto> createUserSecondaryData(@RequestBody UserSecondaryDataDto userSecondaryDataDto){
        UserSecondaryDataDto savedUserSecondaryData = userSecondaryDataService.createUserSecondaryData(userSecondaryDataDto);
        return new ResponseEntity<>(savedUserSecondaryData, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserSecondaryDataDto> getUserSecondaryDataByID(@PathVariable("id") String userID){
        UserSecondaryDataDto userSecondaryData = userSecondaryDataService.getUserSecondaryDataById(userID);
        return ResponseEntity.ok(userSecondaryData);
    }

    @PutMapping("{id}")
    public ResponseEntity<UserSecondaryDataDto> updateUserSecondaryData(@PathVariable("id") String userID, @RequestBody UserSecondaryDataDto updatedData){
        UserSecondaryDataDto savedData = userSecondaryDataService.updateUserSecondaryData(userID,updatedData);
        return ResponseEntity.ok(savedData);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUserSecondaryData(@PathVariable("id") String userID){
        userSecondaryDataService.deleteEmployee(userID);
        return ResponseEntity.ok(userID+" Secondary Data deleted successfully");
    }

}