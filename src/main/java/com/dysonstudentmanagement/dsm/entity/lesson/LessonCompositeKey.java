package com.dysonstudentmanagement.dsm.entity.lesson;

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
LessonCompositeKey

Defines the Lesson entity's composite key

Original Author: Billy Peters 26/04/2024
 */
public class LessonCompositeKey implements Serializable {
    @Id
    @Column(name = "ModuleID", length = 12)
    private String moduleID;
    @Id
    @Column(name = "LessonID")
    private int lessonID;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LessonCompositeKey that = (LessonCompositeKey) o;
        return lessonID == that.lessonID && Objects.equals(moduleID, that.moduleID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(moduleID, lessonID);
    }
}
