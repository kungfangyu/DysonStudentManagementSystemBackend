package com.dysonstudentmanagement.dsm.entity.studenttutor;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
/*
StudentTutorCompositeKey

Defines the StudentTutor entity's composite key

Original Author: Billy Peters 24/04/2024
 */
public class StudentTutorCompositeKey implements Serializable {

    @Id
    @Column(name = "StudentID", length = 8)
    private String studentID;
    @Id
    @Column(name = "StaffID", length = 8)
    private String staffID;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentTutorCompositeKey that = (StudentTutorCompositeKey) o;
        return Objects.equals(getStudentID(), that.getStudentID()) && Objects.equals(getStaffID(), that.getStaffID());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStudentID(), getStaffID());
    }
}
