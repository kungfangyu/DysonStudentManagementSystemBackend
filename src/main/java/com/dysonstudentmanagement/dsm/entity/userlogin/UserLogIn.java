package com.dysonstudentmanagement.dsm.entity.userlogin;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "UserLogIn")

public class UserLogIn {
    @Id
    @Column(name = "UserID", length = 8)
    private String userID;
    @Column(name = "Password", length = 100)
    private String password;
}
