package com.dysonstudentmanagement.dsm.entity.coursework;

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
CourseworkCompositeKey

Defines the Coursework entity's composite key fields

Original Author: Jack Burnett 27/04/2024
 */
public class CourseworkCompositeKey implements Serializable {
    @Id
    @Column(name = "ModuleID")
    private String moduleID;
    @Id
    @Column(name = "CourseworkID")
    private int courseworkID;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseworkCompositeKey that = (CourseworkCompositeKey) o;
        return getCourseworkID() == that.getCourseworkID() && Objects.equals(getModuleID(), that.getModuleID());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getModuleID(), getCourseworkID());
    }
}
