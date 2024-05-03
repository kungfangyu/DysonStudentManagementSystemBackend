package com.dysonstudentmanagement.dsm.entity.lessonstaff;

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
LessonStaffCompositeKey

Defines the LessonStaff entity's composite key

Original Author: Billy Peters 26/04/2024
 */
public class LessonStaffCompositeKey implements Serializable {
    @Id
    @Column(name = "ModuleID", length = 12)
    private String moduleID;
    @Id
    @Column(name = "LessonID")
    private int lessonID;
    @Id
    @Column(name = "StaffID", length = 8)
    private String staffID;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LessonStaffCompositeKey that = (LessonStaffCompositeKey) o;
        return getLessonID() == that.getLessonID() && Objects.equals(getModuleID(), that.getModuleID()) && Objects.equals(getStaffID(), that.getStaffID());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getModuleID(), getLessonID(), getStaffID());
    }
}
