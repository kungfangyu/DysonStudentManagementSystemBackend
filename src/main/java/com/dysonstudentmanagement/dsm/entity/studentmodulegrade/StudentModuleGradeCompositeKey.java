package com.dysonstudentmanagement.dsm.entity.studentmodulegrade;

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
StudentModuleGradeCompositeKey

Defines the StudentModuleGrade entity's composite key fields

Original Author: Grace Liu 25/04/2024
 */
public class StudentModuleGradeCompositeKey implements Serializable {
    @Id
    @Column(name = "StudentID", length = 8)
    private String studentID;
    @Id
    @Column(name = "ModuleID", length = 12)
    private String moduleID;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentModuleGradeCompositeKey that = (StudentModuleGradeCompositeKey) o;
        return Objects.equals(getStudentID(), that.getStudentID()) && Objects.equals(getModuleID(), that.getModuleID());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStudentID(), getModuleID());
    }
}
