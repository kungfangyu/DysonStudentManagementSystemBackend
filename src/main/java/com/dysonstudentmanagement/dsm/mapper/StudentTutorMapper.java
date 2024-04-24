package com.dysonstudentmanagement.dsm.mapper;

import com.dysonstudentmanagement.dsm.dto.StudentTutorDto;
import com.dysonstudentmanagement.dsm.entity.studenttutor.StudentTutor;

public class StudentTutorMapper {

    public static StudentTutorDto mapToStudentTutorDto(StudentTutor studentTutor){
        return new StudentTutorDto(
                studentTutor.getStudentID(),
                studentTutor.getStaffID()
        );
    }

    public static StudentTutor mapToStudentTutor(StudentTutorDto studentTutorDto){
        StudentTutor studentTutor = StudentTutor.builder()
                .studentID(studentTutorDto.getStudentID())
                .staffID(studentTutorDto.getStaffID())
                .build();
        return studentTutor;
    }
}
