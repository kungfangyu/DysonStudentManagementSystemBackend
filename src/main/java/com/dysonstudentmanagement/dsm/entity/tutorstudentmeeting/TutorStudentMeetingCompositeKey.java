package com.dysonstudentmanagement.dsm.entity.tutorstudentmeeting;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
/*
TutorStudentMeetingCompositeKey

Defines the TutorStudentMeeting entity's composite key fields

Original Author: Jack Burnett 30/04/2024
Modifying Author: Billy Peters 04/05/2024 Removed StudentID from composite primary key
 */
public class TutorStudentMeetingCompositeKey implements Serializable {
    @Id
    @Column(name = "StaffID")
    private String staffID;
    @Id
    @Column(name = "MeetingTime")
    private LocalDateTime meetingTime;

    public TutorStudentMeetingCompositeKey (String staffID, String meetingTimeString) {
        this.staffID = staffID;

        try {
            this.meetingTime = LocalDateTime.parse(meetingTimeString);
        } catch (DateTimeParseException e) {
            System.out.println(e);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TutorStudentMeetingCompositeKey that = (TutorStudentMeetingCompositeKey) o;
        return Objects.equals(getStaffID(), that.getStaffID()) && Objects.equals(getMeetingTime(), that.getMeetingTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStaffID(), getMeetingTime());
    }
}
