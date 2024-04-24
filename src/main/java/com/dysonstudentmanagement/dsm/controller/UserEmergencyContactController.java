package com.dysonstudentmanagement.dsm.controller;

import com.dysonstudentmanagement.dsm.dto.UserEmergencyContactDto;
import com.dysonstudentmanagement.dsm.entity.useremergencycontact.UserEmergencyContactCompositeKey;
import com.dysonstudentmanagement.dsm.service.UserEmergencyContactService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/userEmergencyContact")
public class UserEmergencyContactController {

    private UserEmergencyContactService userEmergencyContactService;

    @PostMapping
    public ResponseEntity<UserEmergencyContactDto> createUserEmergencyContact(@RequestBody UserEmergencyContactDto userEmergencyContactDto){
        UserEmergencyContactDto savedEmergencyContact = userEmergencyContactService.createUserEmergencyContact(userEmergencyContactDto);
        return new ResponseEntity<>(savedEmergencyContact, HttpStatus.CREATED);
    }

    @GetMapping("{userID}")
    public ResponseEntity<List<UserEmergencyContactDto>> getUserEmergencyContactByUserID(@PathVariable("userID") String userID){
        List<UserEmergencyContactDto> userEmergencyContactDtos = userEmergencyContactService.getUserEmergencyContactByUserID(userID);
        return ResponseEntity.ok(userEmergencyContactDtos);
    }

    @PutMapping("{userID}/{contactPriority}")
    public ResponseEntity<UserEmergencyContactDto> updateUserEmergencyContact(@PathVariable("userID") String userID,
                                                                              @PathVariable("contactPriority") int contactPriority,
                                                                              @RequestBody UserEmergencyContactDto updatedContact){
        UserEmergencyContactCompositeKey targetKey = new UserEmergencyContactCompositeKey(userID, contactPriority);
        UserEmergencyContactDto savedContact = userEmergencyContactService.updateUserEmergencyContact(targetKey, updatedContact);
        return ResponseEntity.ok(savedContact);
    }

    @DeleteMapping("{userID}/{contactPriority}")
    public ResponseEntity<String> deleteUserEmergencyContact(@PathVariable("userID") String userID,
                                                             @PathVariable("contactPriority") int contactPriority){
        UserEmergencyContactCompositeKey targetKey = new UserEmergencyContactCompositeKey(userID, contactPriority);
        userEmergencyContactService.deleteUserEmergencyContact(targetKey);
        return ResponseEntity.ok(userID+": Emergency contact "+contactPriority+" deleted successfully");
    }
}
