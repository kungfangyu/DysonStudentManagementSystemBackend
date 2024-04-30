package com.dysonstudentmanagement.dsm.repository;

import com.dysonstudentmanagement.dsm.entity.courseworkextensionrequest.CourseworkExtensionRequest;
import com.dysonstudentmanagement.dsm.entity.courseworkextensionrequest.CourseworkExtensionRequestCompositeKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/*
    Interface: CourseworkExtensionRequestRepository
    Interface which provides the CourseworkExtensionRequest Entity with the relevant JPARepo methods.
    Original Author: Jack Burnett, 28/04/2024
*/
public interface CourseworkExtensionRequestRepository extends JpaRepository<CourseworkExtensionRequest, CourseworkExtensionRequestCompositeKey> {
    public List<CourseworkExtensionRequest> findByStudentIDAndModuleIDAndCourseworkID(String studentID, String moduleID, int courseworkID);
}
