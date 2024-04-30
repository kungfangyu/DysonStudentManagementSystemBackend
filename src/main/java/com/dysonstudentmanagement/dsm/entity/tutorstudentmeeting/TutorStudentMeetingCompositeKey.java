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
public class TutorStudentMeetingCompositeKey implements Serializable {
    @Id
    @Column(name = "StaffID")
    private String staffID;
    @Id
    @Column(name = "StudentID")
    private String studentID;
    @Id
    @Column(name = "MeetingTime")
    private LocalDateTime meetingTime;

    public TutorStudentMeetingCompositeKey (String staffID, String studentID, String meetingTimeString) {
        this.staffID = staffID;
        this.studentID = studentID;

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
        return Objects.equals(getStaffID(), that.getStaffID()) && Objects.equals(getStudentID(), that.getStudentID()) && Objects.equals(getMeetingTime(), that.getMeetingTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStaffID(), getStudentID(), getMeetingTime());
    }
}
