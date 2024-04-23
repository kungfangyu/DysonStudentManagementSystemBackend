package com.dysonstudentmanagement.dsm.entity;

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
@Table(name = "Module")
public class Module {
    @Id
    @GeneratedValue(generator = "customUserIdGenerator")
    @GenericGenerator(name = "customUserIdGenerator", type = ModuleIDGenerator.class)
    @Column(name="ModuleID",length=12)
    String moduleID;
    @Column(name = "ModuleName",length=100)
    String moduleName;
    @Column(name = "ModulePhoto", length=256)
    String modulePhoto;
    @Column(name = "StartDate")
    LocalDate startDate;
    @Column(name = "EndDate")
    LocalDate endDate;
    @Column(name = "ModuleCredits")
    int moduleCredits;
}
