package com.dysonstudentmanagement.dsm.service.impl;

import com.dysonstudentmanagement.dsm.dto.LessonStaffDto;
import com.dysonstudentmanagement.dsm.entity.lesson.Lesson;
import com.dysonstudentmanagement.dsm.entity.lesson.LessonCompositeKey;
import com.dysonstudentmanagement.dsm.entity.lessonstaff.LessonStaff;
import com.dysonstudentmanagement.dsm.entity.lessonstaff.LessonStaffCompositeKey;
import com.dysonstudentmanagement.dsm.entity.moduledetails.ModuleDetails;
import com.dysonstudentmanagement.dsm.entity.user.UserPrimaryData;
import com.dysonstudentmanagement.dsm.mapper.LessonStaffMapper;
import com.dysonstudentmanagement.dsm.repository.LessonRepository;
import com.dysonstudentmanagement.dsm.repository.LessonStaffRepository;
import com.dysonstudentmanagement.dsm.repository.UserPrimaryDataRepository;
import com.dysonstudentmanagement.dsm.service.LessonStaffService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LessonStaffServiceImpl implements LessonStaffService {
    private LessonRepository lessonRepo;
    private UserPrimaryDataRepository staffRepo;
    private LessonStaffRepository lessonStaffRepo;

    @Override
    public LessonStaffDto createLessonStaff(LessonStaffDto lessonStaffDto) {
        LessonStaff lessonStaff = LessonStaffMapper.mapToLessonStaff(lessonStaffDto);

        LessonCompositeKey lessonCompositeKey = new LessonCompositeKey(lessonStaff.getModuleID(),lessonStaff.getLessonID());
        Lesson associatedLesson = lessonRepo.findById(lessonCompositeKey)
                .orElseThrow(() -> new DataIntegrityViolationException("Failed... Lesson Key does not exist in target table Lesson")
                );
        UserPrimaryData associatedStaff = staffRepo.findById(lessonStaff.getStaffID())
                .orElseThrow(() -> new DataIntegrityViolationException("Failed... StaffID does not exist in target table UserPrimaryData")
                );
        lessonStaff.setLesson(associatedLesson);
        lessonStaff.setStaffPrimaryData(associatedStaff);

        LessonStaffCompositeKey lessonStaffCompositeKey = new LessonStaffCompositeKey(lessonStaff.getModuleID(),lessonStaff.getLessonID(),lessonStaff.getStaffID());
        Optional<LessonStaff> lessonStaffExists = lessonStaffRepo.findById(lessonStaffCompositeKey);
        if(lessonStaffExists.isPresent()){
            throw new DataIntegrityViolationException("Failed...Record already exists for given primary key");
        }else {
            LessonStaff savedLessonStaff = lessonStaffRepo.save(lessonStaff);
            return LessonStaffMapper.mapToLessonStaffDto(savedLessonStaff);
        }
    }

    @Override
    public List<LessonStaffDto> getLessonStaffByModuleID(String moduleID) {
        return null;
    }

    @Override
    public List<LessonStaffDto> getLessonStaffByStaffID(String StaffID) {
        return null;
    }

    @Override
    public LessonStaffDto updateLessonStaff(LessonStaffCompositeKey targetKey, LessonStaffDto updatedLessonStaffDto) {
        return null;
    }

    @Override
    public void deleteLessonStaff(LessonStaffCompositeKey targetKey) {

    }
}
