package com.dysonstudentmanagement.dsm.entity.lessonstaff;

import com.dysonstudentmanagement.dsm.entity.lesson.Lesson;
import com.dysonstudentmanagement.dsm.entity.user.UserPrimaryData;
import com.dysonstudentmanagement.dsm.entitylistener.LessonStaffListener;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@IdClass(LessonStaffCompositeKey.class)
@Table(name = "LessonStaff")
@EntityListeners(LessonStaffListener.class)
/*
LessonStaff entity

Represents LessonStaff table in database

Original Author: Billy Peters 26/04/2024
 */
public class LessonStaff {
    @Id
    @Column(name = "ModuleID", length = 12)
    private String moduleID;
    @Id
    @Column(name = "LessonID")
    private int lessonID;
    @Id
    @Column(name = "StaffID", length = 8)
    private String staffID;

    @MapsId
    @ManyToOne
    @JoinColumn(name = "StaffID", referencedColumnName = "UserID")
    private UserPrimaryData staffPrimaryData;

    @MapsId
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "ModuleID", referencedColumnName = "ModuleID"),
            @JoinColumn(name = "LessonID", referencedColumnName = "LessonID")
    })
    private Lesson lesson;
}
