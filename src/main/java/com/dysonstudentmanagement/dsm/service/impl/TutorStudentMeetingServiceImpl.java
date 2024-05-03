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
 */
public class TutorStudentMeetingServiceImpl implements TutorStudentMeetingService {
    private UserPrimaryDataRepository primaryDataRepository;
    private TutorStudentMeetingRepository tutorStudentMeetingRepository;

    @Override
    public TutorStudentMeetingDto createTutorStudentMeeting(TutorStudentMeetingDto tutorStudentMeetingDto) {
        TutorStudentMeeting tutorStudentMeeting = TutorStudentMeetingMapper.mapToTutorStudentMeeting(tutorStudentMeetingDto);

        UserPrimaryData student = primaryDataRepository.findById(tutorStudentMeetingDto.getStudentID())
                .orElseThrow(() -> new DataIntegrityViolationException("Student Not Found"));
        UserPrimaryData staff = primaryDataRepository.findById(tutorStudentMeetingDto.getStaffID())
                .orElseThrow(() -> new DataIntegrityViolationException("Staff Not Found"));

        TutorStudentMeetingCompositeKey targetKey = new TutorStudentMeetingCompositeKey(
                tutorStudentMeetingDto.getStaffID(),
                tutorStudentMeetingDto.getStudentID(),
                tutorStudentMeetingDto.getMeetingTime()
        );

        Optional<TutorStudentMeeting> tutorStudentMeetingOptional = tutorStudentMeetingRepository.findById(targetKey);
        if (tutorStudentMeetingOptional.isPresent()) {
            throw new DataIntegrityViolationException("Tutor Student Meeting Already Exists");
        } else {
            tutorStudentMeeting.setStaffID(tutorStudentMeetingDto.getStaffID());
            tutorStudentMeeting.setStudentID(tutorStudentMeetingDto.getStudentID());
            tutorStudentMeeting.setMeetingTime(tutorStudentMeetingDto.getMeetingTime());
            tutorStudentMeeting.setNotes(tutorStudentMeetingDto.getNotes());

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
    public List<TutorStudentMeetingDto> getTutorStudentMeetingByStudentIDandStaffID(String studentID, String staffID) {
        List<TutorStudentMeeting> studentMeetingList = tutorStudentMeetingRepository.findByStudentIDAndStaffID(studentID, staffID);
        return studentMeetingList.stream().map(TutorStudentMeetingMapper::mapToTutorStudentMeetingDto).collect(Collectors.toList());
    }

    @Override
    public TutorStudentMeetingDto updateTutorStudentMeeting(TutorStudentMeetingCompositeKey targetKey, TutorStudentMeetingDto tutorStudentMeetingDto) {
        TutorStudentMeeting tutorStudentMeeting = tutorStudentMeetingRepository.findById(targetKey)
                .orElseThrow(() -> new DataIntegrityViolationException("Tutor Student Meeting Not Found"));

        tutorStudentMeeting.setNotes(tutorStudentMeetingDto.getNotes());
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
