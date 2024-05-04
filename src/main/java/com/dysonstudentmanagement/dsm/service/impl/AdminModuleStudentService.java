package com.dysonstudentmanagement.dsm.service.impl;

import com.dysonstudentmanagement.dsm.dto.AdminModuleStudentDto;
import com.dysonstudentmanagement.dsm.entity.studentmodulegrade.StudentModuleGrade;
import com.dysonstudentmanagement.dsm.entity.studenttutor.StudentTutor;
import com.dysonstudentmanagement.dsm.entity.user.UserPrimaryData;
import com.dysonstudentmanagement.dsm.repository.StudentModuleGradeRepository;
import com.dysonstudentmanagement.dsm.repository.StudentTutorRepository;
import com.dysonstudentmanagement.dsm.repository.UserPrimaryDataRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class AdminModuleStudentService {
    private StudentModuleGradeRepository studentModuleGradeRepository;
    private UserPrimaryDataRepository userPrimaryDataRepository;
    private StudentTutorRepository studentTutorRepository;

    public List<AdminModuleStudentDto> getStudentsByModuleId(String moduleId) {
        List<StudentModuleGrade> studentModuleGrades = studentModuleGradeRepository.findByModuleID(moduleId);
        if (!studentModuleGrades.isEmpty()) {
            return studentModuleGrades.stream().map(this::mapToAdminModuleStudentDto).collect(Collectors.toList());
        }else {
            return null;
        }
    }
    private AdminModuleStudentDto mapToAdminModuleStudentDto(StudentModuleGrade studentModuleGrade) {
        AdminModuleStudentDto adminModuleStudentDto = new AdminModuleStudentDto();

        adminModuleStudentDto.setStudentId(studentModuleGrade.getStudentID());
        adminModuleStudentDto.setGrade(studentModuleGrade.getGrade());
        adminModuleStudentDto.setStatus(studentModuleGrade.getStatus());

        UserPrimaryData studentPrimaryData = userPrimaryDataRepository.findById(studentModuleGrade.getStudentID()).orElse(null);
        if (studentPrimaryData != null) {
            adminModuleStudentDto.setName(studentPrimaryData.getFirstName()+" "+studentPrimaryData.getLastName());
            adminModuleStudentDto.setEmail(studentPrimaryData.getPersonalEmail());

            StudentTutor studentTutor = studentTutorRepository.findByStudentID(studentModuleGrade.getStudentID()).getFirst();
            if (studentTutor != null) {
                UserPrimaryData tutorPrimaryData = studentTutor.getStaffPrimaryData();
                adminModuleStudentDto.setTutor(tutorPrimaryData.getFirstName()+" "+tutorPrimaryData.getLastName());
            } else{
                adminModuleStudentDto.setTutor("Not assigned");
            }
        }

        return adminModuleStudentDto;
    }
}
