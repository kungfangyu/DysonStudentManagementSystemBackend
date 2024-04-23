package com.dysonstudentmanagement.dsm.repository;

import com.dysonstudentmanagement.dsm.entity.previousqualification.PreviousQualification;
import com.dysonstudentmanagement.dsm.entity.previousqualification.PreviousQualificationCompositeKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PreviousQualificationRepository extends JpaRepository<PreviousQualification, PreviousQualificationCompositeKey> {
    public List<PreviousQualification> findByUserID(String userID);
}
