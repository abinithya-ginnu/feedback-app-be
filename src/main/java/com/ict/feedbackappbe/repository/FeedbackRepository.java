package com.ict.feedbackappbe.repository;

import com.ict.feedbackappbe.models.FeedbackEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<FeedbackEntity, Integer> {

}
