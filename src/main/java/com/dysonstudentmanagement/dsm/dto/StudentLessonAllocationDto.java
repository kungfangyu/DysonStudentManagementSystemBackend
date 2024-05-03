package com.dysonstudentmanagement.dsm.dto;

import com.dysonstudentmanagement.dsm.entity.studentlessonallocation.StudentLessonAllocation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
/*
StudentLessonAllocationDto

Defines fields/data that will be transmitted to/from the API client when requesting/sending StudentLessonAllocation data.

Original Author: Billy Peters 26/04/2024
 */
public class StudentLessonAllocationDto {
    private String moduleID;
    private int lessonID;
    private String studentID;
    private StudentLessonAllocation.Attended isAttended;
}
