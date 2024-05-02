package com.dysonstudentmanagement.dsm.entity.lesson;

import com.dysonstudentmanagement.dsm.entity.moduledetails.ModuleDetails;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

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
//    @Column(name = "LessonID") // commented out as hibernate does not support autoincrementing of one column a composite key
//    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int lessonID;

    @Enumerated(EnumType.STRING)
    @Column(name = "LessonType")
    private LessonType lessonType;
    @Column(name= "StartTime")
    private LocalDateTime startTime;
    @Column(name = "EndTime")
    private LocalDateTime endTime;
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
