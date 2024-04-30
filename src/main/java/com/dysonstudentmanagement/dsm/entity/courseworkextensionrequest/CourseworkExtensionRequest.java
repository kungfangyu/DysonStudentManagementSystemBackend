package com.dysonstudentmanagement.dsm.entity.courseworkextensionrequest;

import com.dysonstudentmanagement.dsm.entity.studentcourseworkgrade.StudentCourseworkGrade;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "CourseworkExtensionRequest")
@IdClass(CourseworkExtensionRequestCompositeKey.class)
public class CourseworkExtensionRequest {
    @Id
    @Column(name = "StudentID")
    private String studentID;
    @Id
    @Column(name = "ModuleID")
    private String moduleID;
    @Id
    @Column(name = "CourseworkID")
    private int courseworkID;
    @Id
    @Column(name = "RequestNumber")
    private int requestNumber;
    @Column(name = "RequestDate")
    private LocalDateTime requestDate;
    @Column(name = "RequestReason")
    private String requestReason;
    @Enumerated(EnumType.STRING)
    @Column(name = "Status")
    private RequestStatus status;
    @Column(name = "AdjustedDeadline")
    private LocalDateTime adjustedDeadline;

    @MapsId
    @ManyToOne
    @JoinColumns({
            @JoinColumn (name = "StudentID", referencedColumnName = "StudentID"),
            @JoinColumn (name = "ModuleID", referencedColumnName = "ModuleID"),
            @JoinColumn (name = "CourseworkID", referencedColumnName = "CourseworkID")
    })
    private StudentCourseworkGrade studentCourseworkGrade;

    public enum RequestStatus{
        Submitted,
        Accepted,
        Rejected
    }
}
