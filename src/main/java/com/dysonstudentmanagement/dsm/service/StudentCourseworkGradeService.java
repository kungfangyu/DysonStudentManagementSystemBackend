package com.dysonstudentmanagement.dsm.service;

import com.dysonstudentmanagement.dsm.dto.ModuleAnnouncementDto;
import com.dysonstudentmanagement.dsm.dto.StudentCourseworkGradeDto;
import com.dysonstudentmanagement.dsm.entity.moduleannouncement.ModuleAnnouncementCompositeKey;
import com.dysonstudentmanagement.dsm.entity.studentcourseworkgrade.StudentCourseworkGradeCompositeKey;

import java.util.List;

public interface StudentCourseworkGradeService {
    StudentCourseworkGradeDto createStudentCourseworkGrade(StudentCourseworkGradeDto studentCourseworkGradeDto);

    StudentCourseworkGradeDto getStudentCourseworkGrade(StudentCourseworkGradeCompositeKey targetKey);

    StudentCourseworkGradeDto updateStudentCourseworkGrade(StudentCourseworkGradeCompositeKey targetKey, StudentCourseworkGradeDto
            studentCourseworkGradeDto);

    void deleteStudentCourseworkGrade(StudentCourseworkGradeCompositeKey targetKey);
}
