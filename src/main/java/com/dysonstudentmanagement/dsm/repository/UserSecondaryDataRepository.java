package com.dysonstudentmanagement.dsm.repository;

import com.dysonstudentmanagement.dsm.entity.user.UserSecondaryData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSecondaryDataRepository extends JpaRepository<UserSecondaryData,String> {
}
