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
/*
UserEmergencyContactCompositeKey

Defines the UserEmergencyContact entity's composite key

Original Author: Billy Peters 23/04/2024
 */
public class UserEmergencyContactCompositeKey implements Serializable {

    @Id
    @Column(name = "UserID", length = 8)
    private String userID;
    @Id
    @Column(name = "ContactPriority") //describes order in which emergency contacts should be contacted
    private int contactPriority;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEmergencyContactCompositeKey that = (UserEmergencyContactCompositeKey) o;
        return getContactPriority() == that.getContactPriority() && Objects.equals(getUserID(), that.getUserID());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserID(), getContactPriority());
    }
}

