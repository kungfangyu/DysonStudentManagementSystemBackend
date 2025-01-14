package com.dysonstudentmanagement.dsm.entity.studentlessonallocation;

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
StudentLessonAllocationCompositeKey

Defines the StudentLessonAllocation entity's composite key fields

Original Author: Billy Peters 26/04/2024
 */
public class StudentLessonAllocationCompositeKey implements Serializable {
    @Id
    @Column(name = "ModuleID", length = 12)
    private String moduleID;

    @Id
    @Column(name = "LessonID")
    private int lessonID;

    @Id
    @Column(name = "StudentID", length = 8)
    private String studentID;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentLessonAllocationCompositeKey that = (StudentLessonAllocationCompositeKey) o;
        return getLessonID() == that.getLessonID() && Objects.equals(getModuleID(), that.getModuleID()) && Objects.equals(getStudentID(), that.getStudentID());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getModuleID(), getLessonID(), getStudentID());
    }
}
