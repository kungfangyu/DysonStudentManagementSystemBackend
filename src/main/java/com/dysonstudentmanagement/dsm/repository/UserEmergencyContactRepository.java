package com.dysonstudentmanagement.dsm.repository;

import com.dysonstudentmanagement.dsm.entity.useremergencycontact.UserEmergencyContact;
import com.dysonstudentmanagement.dsm.entity.useremergencycontact.UserEmergencyContactCompositeKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserEmergencyContactRepository extends JpaRepository<UserEmergencyContact, UserEmergencyContactCompositeKey> {
    public List<UserEmergencyContact> findByUserID(String userID);
}
