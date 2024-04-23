package com.dysonstudentmanagement.dsm.entity.useremergencycontact;


import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEmergencyContactCompositeKey implements Serializable {
    @Id
    @Column(name = "UserID", length = 8)
    private String userID;
    @Id
    @Column(name = "FirstName", length = 70)
    private String firstName;
    @Id
    @Column(name = "LastName", length = 70)
    private String lastName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEmergencyContactCompositeKey that = (UserEmergencyContactCompositeKey) o;
        return Objects.equals(getUserID(), that.getUserID()) && Objects.equals(getFirstName(), that.getFirstName()) && Objects.equals(getLastName(), that.getLastName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserID(), getFirstName(), getLastName());
    }
}

