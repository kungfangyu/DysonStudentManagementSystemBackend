package com.dysonstudentmanagement.dsm.entity.useremergencycontact;

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
@Table(name = "UserEmergencyContact")
@IdClass(UserEmergencyContactCompositeKey.class)
/*
UserEmergencyContact Entity

Spring representation of the database table "UserEmergencyContact"

Original Author: Billy Peters 23/04/2024
 */
public class UserEmergencyContact {

    @Id
    @Column(name = "UserID", length = 8)
    private String userID;
    @Id
    @Column(name = "ContactPriority") //describes order in which emergency contacts should be contacted
    private int contactPriority;
    @Column(name = "FirstName", length = 70)
    private String firstName;
    @Column(name = "LastName", length = 70)
    private String lastName;
    @Column(name = "Title", length = 70)
    private String title;
    @Column(name = "Email", length = 254)
    private String email;
    @Column(name = "Phone", length = 15)
    private String phone;
    @Column(name = "Relation", length = 20)
    private String relation;
    @Column(name = "Address", length = 120)
    private String address;
    @Column(name = "Postcode",length = 8)
    private String postcode;

    @MapsId
    @ManyToOne
    @JoinColumn(name = "UserID", referencedColumnName = "UserID")
    private UserPrimaryData userPrimaryData;
}

