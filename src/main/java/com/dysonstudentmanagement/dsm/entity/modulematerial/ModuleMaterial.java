package com.dysonstudentmanagement.dsm.entity.modulematerial;

import com.dysonstudentmanagement.dsm.entity.moduledetails.ModuleDetails;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "ModuleMaterial")
@IdClass(ModuleMaterialCompositeKey.class)
/*
ModuleMaterial Entity

Spring representation of the database table "ModuleMaterial"

Original Author: Tianpu Li 25/04/2024
 */
public class ModuleMaterial {
    @Id
    @Column(name = "ModuleID", length = 12)
    private String moduleID;
    @MapsId
    @ManyToOne
    @JoinColumn(name = "ModuleID", referencedColumnName = "ModuleID")
    private ModuleDetails moduleDetails;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaterialNumber")
    private int materialNumber;

    @Column(name = "Title")
    private String title;
    @Column(name = "Description")
    private String description;
    @Column(name = "isPublished")
    private boolean isPublished;

}
