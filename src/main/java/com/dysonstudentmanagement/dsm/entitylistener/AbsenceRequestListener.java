package com.dysonstudentmanagement.dsm.entitylistener;

import com.dysonstudentmanagement.dsm.entity.absencerequest.AbsenceRequest;
import com.dysonstudentmanagement.dsm.entity.studentlessonallocation.StudentLessonAllocation;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

/*
AbsenceRequestListener

Acts as a 'sql trigger' detecting attempts to change a table in the database that lists the class as an entity listener.
This entity listener is used by the AbsenceRequest entity.

Original Author: Billy Peters 04/05/2024
 */
public class AbsenceRequestListener {
    @PrePersist
    @PreUpdate
    /*
    beforeCreatingOrUpdating(AbsenceRequest)
    If absence request is accepted, sets studentLessonAllocation.isAttended to permittedAbsent.
    Original Author: Billy Peters 04/05/2024
     */
    private void beforeCreatingOrUpdating(AbsenceRequest absenceRequest){
        StudentLessonAllocation studentLessonAllocation = absenceRequest.getStudentLessonAllocation();
        if(absenceRequest.getRequestStatus() == AbsenceRequest.RequestStatus.accepted){
           studentLessonAllocation.setIsAttended(StudentLessonAllocation.Attended.permittedAbsent);
        }
        if(absenceRequest.getRequestStatus() == AbsenceRequest.RequestStatus.rejected){
            studentLessonAllocation.setIsAttended(null); //set to null to allow attendance to be recorded
        }
        if(absenceRequest.getRequestStatus() == AbsenceRequest.RequestStatus.submitted){
            studentLessonAllocation.setIsAttended(StudentLessonAllocation.Attended.permittedAbsent);
        }
        absenceRequest.setStudentLessonAllocation(studentLessonAllocation);
    }
}
