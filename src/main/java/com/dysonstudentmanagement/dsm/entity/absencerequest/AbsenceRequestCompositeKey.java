package com.dysonstudentmanagement.dsm.entity.absencerequest;

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
AbsenceRequestCompositeKey

Defines the AbsenceRequest entity's composite key

Original Author: Billy Peters 04/05/2024
 */
public class AbsenceRequestCompositeKey implements Serializable {
    @Id
    @Column(name = "ModuleID", length = 12)
    private String moduleID;
    @Id
    @Column(name = "LessonID")
    private int lessonID;
    @Id
    @Column(name = "StudentID", length = 8)
    private String studentID;
    @Id
    @Column(name = "RequestID")
    private int requestID;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbsenceRequestCompositeKey that = (AbsenceRequestCompositeKey) o;
        return lessonID == that.lessonID && requestID == that.requestID && Objects.equals(moduleID, that.moduleID) && Objects.equals(studentID, that.studentID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(moduleID, lessonID, studentID, requestID);
    }
}
