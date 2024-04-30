package com.dysonstudentmanagement.dsm.entity.ProgrammModules;

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
@IdClass(programmeModulesCompositeKey.class)
public class programmeModules {

    @Id
    @Column(name = "ModuleID", length = 12)
    private String moduleID;

    @Id
    @Column(name = "ProgrammeID", length = 12)
    private String programmeID;


    @MapsId
    @ManyToOne
    @JoinColumn(name = "ProgrammeID", referencedColumnName = "ProgrammeID")
    private com.dysonstudentmanagement.dsm.entity.Programme.programme programme;
    @MapsId
    @ManyToOne
    @JoinColumn(name = "ModuleID", referencedColumnName = "ModuleID")
    private com.dysonstudentmanagement.dsm.entity.moduledetails.ModuleDetails moduleDetails;




}


//CREATE TABLE `ProgrammeModules` (
//`ModuleID` VARCHAR(12),
//`ProgrammeID` VARCHAR(12),
//PRIMARY KEY (`ModuleID`,`ProgrammeID`),
//CONSTRAINT `ProgrammeModules_FK_ModuleID` FOREIGN KEY (`ModuleID`) REFERENCES `ModuleDetails`(`ModuleID`) ON UPDATE CASCADE ON DELETE RESTRICT,
//CONSTRAINT `ProgrammeModules_FK_ProgrammeID` FOREIGN KEY (`ProgrammeID`) REFERENCES `Programme`(`ProgrammeID`) ON UPDATE CASCADE ON DELETE CASCADE
//);