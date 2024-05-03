package com.dysonstudentmanagement.dsm.mapper;

import com.dysonstudentmanagement.dsm.dto.StudentLessonAllocationDto;
import com.dysonstudentmanagement.dsm.entity.studentlessonallocation.StudentLessonAllocation;

/*
    Class: StudentLessonAllocationMapper
    Mapper class responsible for mapping between StudentLessonAllocation and StudentLessonAllocationDto objects.
    Original Author: Billy Peters 26/04/2024
 */
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
