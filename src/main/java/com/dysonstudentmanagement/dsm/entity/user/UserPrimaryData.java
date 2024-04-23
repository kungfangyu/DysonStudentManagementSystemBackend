package com.dysonstudentmanagement.dsm.entity.user;

import com.dysonstudentmanagement.dsm.entity.useremergencycontact.UserEmergencyContact;
import com.dysonstudentmanagement.dsm.idgenerator.UserIDGenerator;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;



import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "UserPrimaryData")
public class UserPrimaryData {
    @Id
    @GeneratedValue(generator = "customUserIdGenerator")
    @GenericGenerator(name = "customUserIdGenerator", type = UserIDGenerator.class)
    @Column(name = "UserID", length = 8)
    private String userID;
    @Enumerated(EnumType.STRING)
    @Column(name = "UserType")
    private UserType userType;
    @Column(name = "FirstName", length = 70)
    private String firstName;
    @Column(name = "LastName", length = 70)
    private String lastName;
    @Column(name = "DOB")
    private LocalDate dateOfBirth;
    @Column(name = "PersonalEmail", length = 254)
    private String personalEmail;
    @Column(name = "DysonEmail", length = 254)
    private String dysonEmail;
    @Column(name = "Phone", length = 15)
    private String phone;


    @OneToOne(cascade = CascadeType.ALL)
    @ToString.Exclude
    @PrimaryKeyJoinColumn
    private UserSecondaryData userSecondaryData;

    public enum UserType{
        student,
        teacher,
        admin;
    }

}

