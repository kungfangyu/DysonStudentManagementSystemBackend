package com.dysonstudentmanagement.dsm.dto;

import com.dysonstudentmanagement.dsm.entity.absencerequest.AbsenceRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
/*
AbsenceRequestDto

Defines fields/data that will be transmitted to/from the API client when requesting/sending AbsenceRequest data.

Original Author: Billy Peters 04/05/2024
 */
public class AbsenceRequestDto {
    private String moduleID;
    private int lessonID;
    private String studentID;
    private int requestID;
    private String requestReason;
    private AbsenceRequest.RequestStatus requestStatus;
}
