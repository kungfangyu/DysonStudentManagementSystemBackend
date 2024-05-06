package com.dysonstudentmanagement.dsm.entity.programmestaff;

import com.dysonstudentmanagement.dsm.entity.programme.Programme;
import com.dysonstudentmanagement.dsm.entity.user.UserPrimaryData;
import com.dysonstudentmanagement.dsm.entitylistener.ProgrammeStaffListener;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "programmeStaff")
@EntityListeners(ProgrammeStaffListener.class)
@IdClass(ProgrammeStaffCompositeKey.class)
/*
ProgrammeStaff Entity

Spring representation of the database table "ProgrammeStaff"

Original Author: Imran Matloob 24/04/2024
 */
public class ProgrammeStaff {

    @Id
    @Column(name = "ProgrammeID", length = 12)
    private String programmeID;
    @Id
    @Column(name = "StaffID", length = 8)
    private String staffID;



    //this might need checked, but im fairly sure it will be okay

    //this might need checked, but im fairly sure it will be okay
    @MapsId
    @ManyToOne
    @JoinColumn(name = "ProgrammeID", referencedColumnName = "ProgrammeID")
    private Programme programme;
    @MapsId
    @ManyToOne
    @JoinColumn(name = "StaffID", referencedColumnName = "UserID")
    private UserPrimaryData staffPrimaryData;


}

