package com.dysonstudentmanagement.dsm.entity.moduledetails;

import com.dysonstudentmanagement.dsm.idgenerator.ModuleIDGenerator;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "ModuleDetails")
/*
ModuleDetails Entity

Spring representation of the database table "ModuleDetails"

Original Author: Billy Peters 24/04/2024
 */
public class ModuleDetails {
    @Id
    @GeneratedValue(generator = "customModuleIdGenerator")
    @GenericGenerator(name = "customModuleIdGenerator", type = ModuleIDGenerator.class)
    @Column(name="ModuleID",length=12)
    private String moduleID;
    @Column(name = "ModuleName",length=100)
    private String moduleName;
    @Column(name = "ModulePhoto", length=500)
    private String modulePhoto;
    @Column(name = "ModuleDescription")
    private String moduleDescription;
    @Column(name = "StartDate")
    private LocalDate startDate;
    @Column(name = "EndDate")
    private LocalDate endDate;
    @Column(name = "ModuleCredits")
    private int moduleCredits;
}
