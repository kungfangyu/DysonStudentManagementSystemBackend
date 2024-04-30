package com.dysonstudentmanagement.dsm.controller;

import com.dysonstudentmanagement.dsm.dto.CourseworkExtensionRequestDto;
import com.dysonstudentmanagement.dsm.entity.courseworkextensionrequest.CourseworkExtensionRequestCompositeKey;
import com.dysonstudentmanagement.dsm.service.CourseworkExtensionRequestService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/CourseworkExtensionRequest")
public class CourseworkExtensionRequestController {
    private CourseworkExtensionRequestService courseworkExtensionRequestService;

    @PostMapping
    public ResponseEntity<CourseworkExtensionRequestDto> createCoursework(@RequestBody CourseworkExtensionRequestDto courseworkExtensionRequestDto) {
        CourseworkExtensionRequestDto courseworkExtension = courseworkExtensionRequestService.createCourseworkExtensionRequest(courseworkExtensionRequestDto);
        return new ResponseEntity<>(courseworkExtension, HttpStatus.CREATED);
    }

    @GetMapping("{studentID}/{moduleID}/{courseworkID}")
    public ResponseEntity<List<CourseworkExtensionRequestDto>> getAllCourseworkExtensionsRequests(@PathVariable("studentID") String studentID,
                                                                          @PathVariable("moduleID") String moduleID,
                                                                          @PathVariable("courseworkID") int courseworkID) {
        return ResponseEntity.ok(courseworkExtensionRequestService.getCourseworkExtensionRequests(studentID, moduleID, courseworkID));
    }

    @GetMapping("{studentID}/{moduleID}/{courseworkID}/{requestNumber}")
    public ResponseEntity<CourseworkExtensionRequestDto> getCourseworkExtensionRequest(@PathVariable("studentID") String studentID,
                                                                                       @PathVariable("moduleID") String moduleID,
                                                                                       @PathVariable("courseworkID") int courseworkID,
                                                                                       @PathVariable("requestNumber") int requestNumber) {
        CourseworkExtensionRequestCompositeKey key = new CourseworkExtensionRequestCompositeKey(studentID, moduleID, courseworkID, requestNumber);
        CourseworkExtensionRequestDto extensionRequestDto = courseworkExtensionRequestService.getCourseworkExtensionRequest(key);
        return ResponseEntity.ok(extensionRequestDto);
    }

    @PutMapping("{studentID}/{moduleID}/{courseworkID}/{requestNumber}")
    public ResponseEntity<CourseworkExtensionRequestDto> updateCoursework(@PathVariable("studentID") String studentID,
                                                          @PathVariable("moduleID") String moduleID,
                                                          @PathVariable("courseworkID") int courseworkID,
                                                          @PathVariable("requestNumber") int requestNumber,
                                                          @RequestBody CourseworkExtensionRequestDto updateDto) {
        CourseworkExtensionRequestCompositeKey key = new CourseworkExtensionRequestCompositeKey(studentID, moduleID, courseworkID, requestNumber);
        CourseworkExtensionRequestDto courseworkExtensionRequestDto = courseworkExtensionRequestService.updateCourseworkExtensionRequest(key, updateDto);
        return ResponseEntity.ok(courseworkExtensionRequestDto);
    }

    @DeleteMapping("{studentID}/{moduleID}/{courseworkID}/{requestNumber}")
    public ResponseEntity<String> deleteCourseworkExtensionRequest(@PathVariable("studentID") String studentID,
                                                                   @PathVariable("moduleID") String moduleID,
                                                                   @PathVariable("courseworkID") int courseworkID,
                                                                   @PathVariable("requestNumber") int requestNumber) {
        CourseworkExtensionRequestCompositeKey key = new CourseworkExtensionRequestCompositeKey(studentID, moduleID, courseworkID, requestNumber);
        courseworkExtensionRequestService.deleteCourseworkExtensionRequest(key);
        return ResponseEntity.ok(studentID + " " + moduleID + " " + courseworkID + " " + requestNumber + ": Deleted Successfully");
    }
}
