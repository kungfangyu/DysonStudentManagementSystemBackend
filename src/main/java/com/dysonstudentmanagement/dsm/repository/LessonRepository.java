package com.dysonstudentmanagement.dsm.repository;

import com.dysonstudentmanagement.dsm.entity.lesson.Lesson;
import com.dysonstudentmanagement.dsm.entity.lesson.LessonCompositeKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson, LessonCompositeKey> {
    List<Lesson> findByModuleID(String moduleID);
}
