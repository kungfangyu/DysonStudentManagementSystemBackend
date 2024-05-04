package com.dysonstudentmanagement.dsm.entity.courseworkextensionrequest;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
CourseworkExtensionRequestCompositeKey

Defines the CourseworkExtensionRequest entity's composite key fields

Original Author: Jack Burnett 28/04/2024
 */
public class CourseworkExtensionRequestCompositeKey implements Serializable {
    @Id
    @Column(name = "StudentID")
    private String studentID;
    @Id
    @Column(name = "ModuleID")
    private String moduleID;
    @Id
    @Column(name = "CourseworkID")
    private int courseworkID;
    @Id
    @Column(name = "RequestNumber")
    private int requestNumber;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseworkExtensionRequestCompositeKey that = (CourseworkExtensionRequestCompositeKey) o;
        return getCourseworkID() == that.getCourseworkID() && Objects.equals(getStudentID(), that.getStudentID()) && Objects.equals(getModuleID(), that.getModuleID()) && Objects.equals(getRequestNumber(), that.getRequestNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStudentID(), getModuleID(), getCourseworkID(), getRequestNumber());
    }
}
