package com.dysonstudentmanagement.dsm.entity.coursework;

import com.dysonstudentmanagement.dsm.entity.moduledetails.ModuleDetails;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "Coursework")
@IdClass(CourseworkCompositeKey.class)
/*
Coursework Entity

Spring representation of the database table "Coursework"

Original Author: Jack Burnett 27/04/2024
 */
public class Coursework {
    @Id
    @Column(name = "ModuleID")
    private String moduleID;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "CourseworkID")
    private int courseworkID;
    @Column(name = "Description")
    private String description;
    @Column(name = "Deadline")
    private LocalDateTime deadline;
    @Column(name = "PercentageOfModule")
    private Double percentageOfModule;
    @Column(name = "isCourseworkPublished",columnDefinition = "TINYINT", length = 1)
    private boolean isCourseworkPublished;
    @Column(name = "isGradePublished", columnDefinition = "TINYINT", length = 1)
    private boolean isGradePublished;

    @MapsId
    @ManyToOne
    @JoinColumn(name = "ModuleID", referencedColumnName = "ModuleID")
    private ModuleDetails moduleDetails;
}
