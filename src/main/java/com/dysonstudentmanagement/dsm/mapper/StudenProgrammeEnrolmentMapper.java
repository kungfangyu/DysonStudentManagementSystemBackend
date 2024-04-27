package com.dysonstudentmanagement.dsm.mapper;

import com.dysonstudentmanagement.dsm.dto.StudentProgrammeEnrolmentDto;
import com.dysonstudentmanagement.dsm.entity.studentprogrammeenrolment.StudentProgrammeEnrolment;

public class StudenProgrammeEnrolmentMapper {

    public static StudentProgrammeEnrolmentDto studentProgrammeEnrolmentDto(StudentProgrammeEnrolment studentProgrammeEnrolment){
        return new StudentProgrammeEnrolmentDto(
                studentProgrammeEnrolment.getStudentID(),
                studentProgrammeEnrolment.getProgrammeID(),
                studentProgrammeEnrolment.getDateEnrolled(),
                studentProgrammeEnrolment.getDateCompleted(),
                studentProgrammeEnrolment.getFinalGrade(),
                studentProgrammeEnrolment.getStatus()
        );
    }

    public static StudentProgrammeEnrolment mapToStudentProgrammeEnrolment(StudentProgrammeEnrolmentDto studentProgrammeEnrolmentDto){
        return StudentProgrammeEnrolment.builder()
                .programmeID(studentProgrammeEnrolmentDto.getProgrammeID())
                .studentID(studentProgrammeEnrolmentDto.getStudentID())
                .status(studentProgrammeEnrolmentDto.getStatus())
                .dateEnrolled(studentProgrammeEnrolmentDto.getDateEnrolled())
                .dateCompleted(studentProgrammeEnrolmentDto.getDateCompleted())
                .finalGrade(studentProgrammeEnrolmentDto.getFinalGrade())
                .build();
    }
}
//CREATE TABLE `StudentProgrammeEnrolment` (
//`StudentID` VARCHAR(8),
//`ProgrammeID` VARCHAR(12),
//`Status` ENUM('enrolled','suspended','withdrawn','completed'),
//`DateEnrolled` DATE,
//`DateCompleted` DATE,
//`FinalGrade` FLOAT,
//PRIMARY KEY (`StudentID`,`ProgrammeID`),
//CONSTRAINT `StudentProgrammeEnrolment_FK_StudentID` FOREIGN KEY (`StudentID`) REFERENCES `UserPrimaryData`(`UserID`) ON UPDATE CASCADE ON DELETE CASCADE,
//CONSTRAINT `StudentProgrammeEnrolment_FK_ProgrammeID` FOREIGN KEY (`ProgrammeID`) REFERENCES `Programme`(`ProgrammeID`) ON UPDATE CASCADE ON DELETE RESTRICT
//);


