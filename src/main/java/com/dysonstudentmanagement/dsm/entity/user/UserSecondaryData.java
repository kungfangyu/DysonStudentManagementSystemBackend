package com.dysonstudentmanagement.dsm.entity.user;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "UserSecondaryData")
public class UserSecondaryData {
    @Id
    private String userID;
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



    @OneToOne(mappedBy = "userSecondaryData") //Currently represented by bidirectional relationship. Ideally prefer unidirectional relationship, maintianed by UserSecondaryData record, but @OneToOne mapping with shared primary key (rather than reference making up part of a composite primary key) appears to cause issues with custom id generators?
    private UserPrimaryData userPrimaryData;

}
