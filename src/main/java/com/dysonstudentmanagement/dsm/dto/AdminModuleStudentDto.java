package com.dysonstudentmanagement.dsm.dto;

import com.dysonstudentmanagement.dsm.entity.studentmodulegrade.StudentModuleGrade;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
//@AllArgsConstructor
@ToString
public class AdminModuleStudentDto {
    private String studentId;
    private String name;
    private String email;
    private float grade;
    private StudentModuleGrade.Status status;
    private String tutor;
}
