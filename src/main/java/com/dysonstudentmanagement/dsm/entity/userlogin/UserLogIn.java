package com.dysonstudentmanagement.dsm.entity.userlogin;

import com.dysonstudentmanagement.dsm.entitylistener.UserPrimaryDataListener;
import com.dysonstudentmanagement.dsm.idgenerator.UserIDGenerator;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@EntityListeners(UserPrimaryDataListener.class)
@Table(name = "UserLogIn")

public class UserLogIn {
    @Id
    @GeneratedValue(generator = "customUserIdGenerator")
    @GenericGenerator(name = "customUserIdGenerator", type = UserIDGenerator.class)
    @Column(name = "UserID", length = 8)
    private String userID;
    @Column(name = "Password", length = 100)
    private String password;
}
