package com.ict.feedbackappbe.repository;

import com.ict.feedbackappbe.models.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseRepository extends JpaRepository<CourseEntity, Integer> {
    @Query(value = "SELECT * FROM courses WHERE trainer = ?1",nativeQuery = true)
    List<CourseEntity> getCoursesByTrainer(int trainerId);
}
