package com.dysonstudentmanagement.dsm.entity.modulestaff;


import com.dysonstudentmanagement.dsm.entity.moduledetails.ModuleDetails;
import com.dysonstudentmanagement.dsm.entity.user.UserPrimaryData;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "ModuleStaff")
@IdClass(ModuleStaffCompositeKey.class)
public class ModuleStaff {

    @Id
    @Column(name = "ModuleID", length = 12)
    private String moduleID;
    @MapsId
    @ManyToOne
    @JoinColumn(name = "ModuleID", referencedColumnName = "ModuleID")
    private ModuleDetails moduleDetails;


    @Id
    @Column(name = "StaffID", length = 8)
    private String staffID;
    @MapsId
    @ManyToOne
    @JoinColumn(name = "StaffID", referencedColumnName = "UserID")
    private UserPrimaryData userPrimaryData;

}
