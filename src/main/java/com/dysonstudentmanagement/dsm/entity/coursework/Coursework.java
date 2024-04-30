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
