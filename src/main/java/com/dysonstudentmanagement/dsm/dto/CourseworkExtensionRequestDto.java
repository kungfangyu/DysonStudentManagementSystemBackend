package com.dysonstudentmanagement.dsm.dto;

import com.dysonstudentmanagement.dsm.entity.courseworkextensionrequest.CourseworkExtensionRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
public class CourseworkExtensionRequestDto {
    private String studentID;
    private String moduleID;
    private int courseworkID;
    private int requestNumber;
    private Timestamp requestDate;
    private String requestReason;
    private CourseworkExtensionRequest.RequestStatus status;
    private Timestamp adjustedDeadline;
}
