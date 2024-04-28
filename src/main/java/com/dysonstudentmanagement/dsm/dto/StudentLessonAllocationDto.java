package com.dysonstudentmanagement.dsm.dto;

import com.dysonstudentmanagement.dsm.entity.studentlessonallocation.StudentLessonAllocation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StudentLessonAllocationDto {
    private String moduleID;
    private int lessonID;
    private String studentID;
    private StudentLessonAllocation.Attended isAttended;
}
