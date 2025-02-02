package com.dysonstudentmanagement.dsm.entity.user;

import com.dysonstudentmanagement.dsm.entitylistener.UserPrimaryDataListener;
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
@EntityListeners(UserPrimaryDataListener.class)
@Table(name = "UserPrimaryData")
/*
UserPrimaryData Entity

Spring representation of the database table "UserPrimaryData".

Original Author: Billy Peters 21/04/2024

 */
public class UserPrimaryData {
    @Id
    @GeneratedValue(generator = "customUserIdGenerator")
    @GenericGenerator(name = "customUserIdGenerator", type = UserIDGenerator.class) // Id is generated by custom method defined in: UserIDGenerator
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
    @Column(name = "IdPhoto",length = 256)
    private String idPhoto;
    @Column(name= "Title",length = 50)
    private String title;
    @Column(name = "MiddleNames", length=100)
    private String middleNames;
    @Column(name = "Gender", length = 50)
    private String gender;
    @Column(name= "Ethnicity", length = 50)
    private String ethnicity;
    @Column(name = "Address", length = 120)
    private String address;
    @Column(name = "Postcode", length = 8)
    private String postcode;


    public enum UserType{
        student,
        teacher,
        admin
    }

}

