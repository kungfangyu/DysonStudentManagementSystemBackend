package com.dysonstudentmanagement.dsm.entity.modulematerilafile;

import com.dysonstudentmanagement.dsm.entity.modulematerial.ModuleMaterial;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "ModuleMaterialFile")
@IdClass(ModuleMaterialFileCompositeKey.class)
/*
ModuleMaterialFile Entity

Spring representation of the database table "ModuleMaterialFile"

Original Author: Grace Liu 25/04/2024
 */
public class ModuleMaterialFile {
    @Id
    @Column(name = "ModuleID", length = 12)
    private String moduleID;

    @Id
    @Column(name = "MaterialNumber")
    private int materialNumber;
    @MapsId
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "ModuleID", referencedColumnName = "ModuleID"),
            @JoinColumn(name = "MaterialNumber", referencedColumnName = "MaterialNumber")
    })
    private ModuleMaterial moduleMaterial;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FileNumber")
    private int fileNumber;

    @Column(name = "FilePath", length = 256)
    private String filePath;
}
