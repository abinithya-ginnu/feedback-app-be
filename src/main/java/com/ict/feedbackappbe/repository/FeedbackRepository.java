package com.ict.feedbackappbe.repository;

import com.ict.feedbackappbe.models.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {

}
