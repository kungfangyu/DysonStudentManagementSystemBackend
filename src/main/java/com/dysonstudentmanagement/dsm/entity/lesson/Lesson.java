package com.dysonstudentmanagement.dsm.entity.lesson;

import com.dysonstudentmanagement.dsm.entity.moduledetails.ModuleDetails;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "Lesson")
@IdClass(LessonCompositeKey.class)
/*
Lesson entity

Represents Lesson table record in database

Original Author: Billy Peters 26/04/2024
 */
public class Lesson {
    @Id
    @Column(name = "ModuleID", length = 12)
    private String moduleID;
    @Id
    @Column(name = "LessonID")
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int lessonID;

    @Enumerated(EnumType.STRING)
    @Column(name = "LessonType")
    private LessonType lessonType;
    @Column(name= "StartTime")
    private Timestamp startTime;
    @Column(name = "EndTime")
    private Timestamp endTime;
    @Column(name = "isAttendanceRequired")
    private boolean isAttendanceRequired;

    @ManyToOne
    @MapsId
    @JoinColumn(name = "ModuleID", referencedColumnName = "ModuleID")
    private ModuleDetails moduleDetails;
    public enum LessonType{
        Lecture,
        Practical,
        Tutorial
    }

}
