package com.dysonstudentmanagement.dsm.dto;

import com.dysonstudentmanagement.dsm.entity.courseworkextensionrequest.CourseworkExtensionRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class CourseworkExtensionRequestDto {
    private String studentID;
    private String moduleID;
    private int courseworkID;
    private int requestNumber;
    private LocalDateTime requestDate;
    private String requestReason;
    private CourseworkExtensionRequest.RequestStatus status;
    private LocalDateTime adjustedDeadline;
}
