package com.dysonstudentmanagement.dsm.entity.studentexamgrade;

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
public class StudentExamGradeCompositeKey implements Serializable {

    @Id
    @Column(name="ModuleID",length=12)
    private String moduleID;
    @Id
    @Column(name = "ExamID")
    private int examID;  // Unique identifier for the exam

    @Id
    @Column(name = "StudentID", length = 8)
    private String studentID;  // Unique identifier for the user


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentExamGradeCompositeKey that = (StudentExamGradeCompositeKey) o;
        return Objects.equals(getModuleID(), that.getModuleID()) && Objects.equals(getExamID(), that.getExamID()) && Objects.equals(getStudentID(), that.getStudentID());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getModuleID(), getExamID(), getStudentID());
    }
}
