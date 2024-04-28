package com.dysonstudentmanagement.dsm.mapper;

import com.dysonstudentmanagement.dsm.dto.StudentLessonAllocationDto;
import com.dysonstudentmanagement.dsm.entity.studentlessonallocation.StudentLessonAllocation;


public class StudentLessonAllocationMapper {
    public static StudentLessonAllocationDto mapToStudentLessonAllocationDto(StudentLessonAllocation studentLessonAllocation){
        return new StudentLessonAllocationDto(
                studentLessonAllocation.getModuleID(),
                studentLessonAllocation.getLessonID(),
                studentLessonAllocation.getStudentID(),
                studentLessonAllocation.getIsAttended()
        );
    }

    public static StudentLessonAllocation mapToStudentLessonAllocation(StudentLessonAllocationDto studentLessonAllocationDto){
        return StudentLessonAllocation.builder()
                .moduleID(studentLessonAllocationDto.getModuleID())
                .lessonID(studentLessonAllocationDto.getLessonID())
                .studentID(studentLessonAllocationDto.getStudentID())
                .isAttended(studentLessonAllocationDto.getIsAttended())
                .build();
    }

}
