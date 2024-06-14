package com.ict.feedbackappbe.repository;

import com.ict.feedbackappbe.models.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface CourseRepository extends JpaRepository<CourseEntity, Integer> {
    @Query(value = "SELECT * FROM courses WHERE trainer = ?1",nativeQuery = true)
    List<CourseEntity> getCoursesByTrainer(int trainerId);

    @Query(value = "SELECT c.id, course_name, name AS trainer, feedback_score\n" +
                    "FROM courses c\n" +
                    "JOIN users u ON c.trainer = u.id AND c.status = 2\n" +
                    "ORDER BY c.end_date DESC",nativeQuery = true)
    List<Map<String, Object>> getRecentlyCompletedCoursesForIqa();

    @Query(value = "SELECT c.*, name AS trainer_name\n" +
                    "FROM courses c\n" +
                    "JOIN users u ON c.trainer = u.id\n" +
                    "WHERE c.id = ?1",nativeQuery = true)
    Map<String, Object> getCourseDetailsById(int id);
}
