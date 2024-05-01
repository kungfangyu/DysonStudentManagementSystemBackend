package com.dysonstudentmanagement.dsm.entity.studentlessonallocation;

import com.dysonstudentmanagement.dsm.entity.lesson.Lesson;
import com.dysonstudentmanagement.dsm.entity.user.UserPrimaryData;
import com.dysonstudentmanagement.dsm.entitylistener.StudentLessonAllocationListener;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "StudentLessonAllocation")
@EntityListeners(StudentLessonAllocationListener.class)
@IdClass(StudentLessonAllocationCompositeKey.class)
public class StudentLessonAllocation {
    @Id
    @Column(name = "ModuleID", length = 12)
    private String moduleID;

    @Id
    @Column(name = "LessonID")
    private int lessonID;

    @Id
    @Column(name = "StudentID", length = 8)
    private String studentID;

    @Enumerated(EnumType.STRING)
    @Column(name = "isAttended")
    private Attended isAttended;

    @MapsId
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "ModuleID", referencedColumnName = "ModuleID"),
            @JoinColumn(name = "LessonID", referencedColumnName = "LessonID")})
    private Lesson lesson;


    @MapsId
    @ManyToOne
    @JoinColumn(name = "StudentID", referencedColumnName = "UserID")
    private UserPrimaryData studentPrimaryData;

    public enum Attended{
        yes,
        no,
        permittedAbsent
    }

}
