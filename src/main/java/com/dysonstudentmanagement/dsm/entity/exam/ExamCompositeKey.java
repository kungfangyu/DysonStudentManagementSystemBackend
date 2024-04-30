package com.dysonstudentmanagement.dsm.entity.exam;

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
public class ExamCompositeKey implements Serializable {
    @Id
    @Column(name="ModuleID",length=12)
    private String moduleID; // Identifier for the module associated with the exam
    @Id
    @Column(name = "ExamID")
    private int examID;  // Unique identifier for the exam




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExamCompositeKey that = (ExamCompositeKey) o;
        return Objects.equals(getExamID(), that.getExamID()) && Objects.equals(getModuleID(), that.getModuleID());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getExamID(), getModuleID());
    }
}
