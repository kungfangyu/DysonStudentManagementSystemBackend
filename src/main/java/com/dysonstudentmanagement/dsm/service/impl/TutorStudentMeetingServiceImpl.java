package com.dysonstudentmanagement.dsm.service.impl;

import com.dysonstudentmanagement.dsm.dto.TutorStudentMeetingDto;
import com.dysonstudentmanagement.dsm.entity.tutorstudentmeeting.TutorStudentMeeting;
import com.dysonstudentmanagement.dsm.entity.tutorstudentmeeting.TutorStudentMeetingCompositeKey;
import com.dysonstudentmanagement.dsm.entity.user.UserPrimaryData;
import com.dysonstudentmanagement.dsm.mapper.TutorStudentMeetingMapper;
import com.dysonstudentmanagement.dsm.repository.TutorStudentMeetingRepository;
import com.dysonstudentmanagement.dsm.repository.UserPrimaryDataRepository;
import com.dysonstudentmanagement.dsm.service.TutorStudentMeetingService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
/*
TutorStudentMeetingServiceImpl

Class that implements service methods that act upon TutorStudentMeeting table in database

Original Author: Jack Burnett 30/04/2024
Modifying Author: Billy Peters 04/05/2024 Removed StudentID from composite primary key
 */
public class TutorStudentMeetingServiceImpl implements TutorStudentMeetingService {
    private UserPrimaryDataRepository primaryDataRepository;
    private TutorStudentMeetingRepository tutorStudentMeetingRepository;

    @Override
    public TutorStudentMeetingDto createTutorStudentMeeting(TutorStudentMeetingDto tutorStudentMeetingDto) {
        TutorStudentMeeting tutorStudentMeeting = TutorStudentMeetingMapper.mapToTutorStudentMeeting(tutorStudentMeetingDto);

        if(tutorStudentMeetingDto.getStudentID()!= null) {
            UserPrimaryData student = primaryDataRepository.findById(tutorStudentMeetingDto.getStudentID())
                    .orElseThrow(() -> new DataIntegrityViolationException("Student Not Found"));
            tutorStudentMeeting.setStudentPrimaryData(student);
        }
        UserPrimaryData staff = primaryDataRepository.findById(tutorStudentMeetingDto.getStaffID())
                .orElseThrow(() -> new DataIntegrityViolationException("Staff Not Found"));

        tutorStudentMeeting.setStaffID(tutorStudentMeetingDto.getStaffID());
        tutorStudentMeeting.setStaffPrimaryData(staff);
        tutorStudentMeeting.setMeetingTime(tutorStudentMeetingDto.getMeetingTime());
        tutorStudentMeeting.setNotes(tutorStudentMeetingDto.getNotes());


        TutorStudentMeetingCompositeKey targetKey = new TutorStudentMeetingCompositeKey(
                tutorStudentMeetingDto.getStaffID(),
                tutorStudentMeetingDto.getMeetingTime()
        );

        Optional<TutorStudentMeeting> tutorStudentMeetingOptional = tutorStudentMeetingRepository.findById(targetKey);
        if (tutorStudentMeetingOptional.isPresent()) {
            throw new DataIntegrityViolationException("Tutor Student Meeting Already Exists");
        } else {

            TutorStudentMeeting savedMeeting = tutorStudentMeetingRepository.save(tutorStudentMeeting);
            return TutorStudentMeetingMapper.mapToTutorStudentMeetingDto(savedMeeting);
        }
    }

    @Override
    public TutorStudentMeetingDto getTutorStudentMeeting(TutorStudentMeetingCompositeKey targetKey) {
        TutorStudentMeeting tutorStudentMeeting = tutorStudentMeetingRepository.findById(targetKey)
                .orElseThrow(() -> new DataIntegrityViolationException("Tutor Student Meeting Not Found"));
        return TutorStudentMeetingMapper.mapToTutorStudentMeetingDto(tutorStudentMeeting);
    }

    @Override
    public List<TutorStudentMeetingDto> getTutorStudentMeetingByStaffID(String staffID) {
        List<TutorStudentMeeting> studentMeetingList = tutorStudentMeetingRepository.findByStaffID(staffID);
        return studentMeetingList.stream().map(TutorStudentMeetingMapper::mapToTutorStudentMeetingDto).collect(Collectors.toList());
    }

    @Override
    public TutorStudentMeetingDto updateTutorStudentMeeting(TutorStudentMeetingCompositeKey targetKey, TutorStudentMeetingDto tutorStudentMeetingDto) {
        TutorStudentMeeting tutorStudentMeeting = tutorStudentMeetingRepository.findById(targetKey)
                .orElseThrow(() -> new DataIntegrityViolationException("Tutor Student Meeting Not Found"));

        UserPrimaryData student = primaryDataRepository.findById(tutorStudentMeetingDto.getStudentID())
                .orElseThrow(() -> new DataIntegrityViolationException("Student Not Found"));

        tutorStudentMeeting.setNotes(tutorStudentMeetingDto.getNotes());
        tutorStudentMeeting.setStudentID(tutorStudentMeetingDto.getStudentID());
        tutorStudentMeeting.setStudentPrimaryData(student);
        TutorStudentMeeting savedMeeting = tutorStudentMeetingRepository.save(tutorStudentMeeting);
        return TutorStudentMeetingMapper.mapToTutorStudentMeetingDto(savedMeeting);
    }

    @Override
    public void deleteTutorStudentMeeting(TutorStudentMeetingCompositeKey targetKey) {
        TutorStudentMeeting tutorStudentMeeting = tutorStudentMeetingRepository.findById(targetKey)
                .orElseThrow(() -> new DataIntegrityViolationException("Tutor Student Meeting Not Found"));
        tutorStudentMeetingRepository.deleteById(targetKey);
    }
}
