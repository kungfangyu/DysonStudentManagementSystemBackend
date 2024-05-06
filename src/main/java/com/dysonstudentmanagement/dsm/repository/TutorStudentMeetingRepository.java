package com.dysonstudentmanagement.dsm.repository;

import com.dysonstudentmanagement.dsm.entity.tutorstudentmeeting.TutorStudentMeeting;
import com.dysonstudentmanagement.dsm.entity.tutorstudentmeeting.TutorStudentMeetingCompositeKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/*
    Interface: TutorStudentMeetingRepository
    Interface which provides the TutorStudentMeeting Entity with the relevant JPARepo methods.
    Original Author: Jack Burnett, 30/04/2024
*/
public interface TutorStudentMeetingRepository extends JpaRepository<TutorStudentMeeting, TutorStudentMeetingCompositeKey> {
    List<TutorStudentMeeting> findByStaffID(String staffID);
}
