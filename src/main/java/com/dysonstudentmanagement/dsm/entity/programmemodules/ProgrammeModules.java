package com.dysonstudentmanagement.dsm.entity.programmemodules;

import com.dysonstudentmanagement.dsm.entity.moduledetails.ModuleDetails;
import com.dysonstudentmanagement.dsm.entity.programme.Programme;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "ProgrammeModules")
@IdClass(ProgrammeModulesCompositeKey.class)
/*
ProgrammeModules Entity

Spring representation of the database table "ProgrammeModules"

Original Author: Imran Matloob 24/04/2024
 */
public class ProgrammeModules {

    @Id
    @Column(name = "ModuleID", length = 12)
    private String moduleID;

    @Id
    @Column(name = "ProgrammeID", length = 12)
    private String programmeID;


    @MapsId
    @ManyToOne
    @JoinColumn(name = "ProgrammeID", referencedColumnName = "ProgrammeID")
    private Programme programme;
    @MapsId
    @ManyToOne
    @JoinColumn(name = "ModuleID", referencedColumnName = "ModuleID")
    private ModuleDetails moduleDetails;




}

