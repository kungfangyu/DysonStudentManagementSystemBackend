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



//    CREATE TABLE `programmeStaff` (
//        `ProgrammeID` VARCHAR(12),
//        `StaffID` VARCHAR(8),
//        PRIMARY KEY(`ProgrammeID`,`StaffID`),
//        CONSTRAINT `ProgrammeStaff_FK_ProgrammeID` FOREIGN KEY (`ProgrammeID`) REFERENCES `programme`(`ProgrammeID`) ON UPDATE CASCADE ON DELETE CASCADE,
//        CONSTRAINT `ProgrammeStaff_FK_StaffID` FOREIGN KEY (`StaffID`) REFERENCES `UserPrimaryData`(`UserID`) ON UPDATE CASCADE ON DELETE CASCADE
//        );