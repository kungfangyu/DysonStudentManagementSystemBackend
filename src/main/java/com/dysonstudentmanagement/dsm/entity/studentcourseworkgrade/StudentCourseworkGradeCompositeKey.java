package com.dysonstudentmanagement.dsm.entity.studentcourseworkgrade;

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
public class StudentCourseworkGradeCompositeKey implements Serializable {
    @Id
    @Column(name = "ModuleID")
    private String moduleID;
    @Id
    @Column(name = "CourseworkID")
    private int courseworkID;
    @Id
    @Column(name = "StudentID")
    private String studentID;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentCourseworkGradeCompositeKey that = (StudentCourseworkGradeCompositeKey) o;
        return getCourseworkID() == that.getCourseworkID() && Objects.equals(getModuleID(), that.getModuleID()) && Objects.equals(getStudentID(), that.getStudentID());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getModuleID(), getCourseworkID(), getStudentID());
    }
}
