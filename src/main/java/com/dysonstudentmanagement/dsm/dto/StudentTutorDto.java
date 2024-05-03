package com.dysonstudentmanagement.dsm.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
/*
StudentTutorDto

Defines fields/data that will be transmitted to/from the API client when requesting/sending StudentTutor data.

Original Author: Billy Peters 24/04/2024
 */
public class StudentTutorDto {
    private String studentID;
    private String staffID;
}
