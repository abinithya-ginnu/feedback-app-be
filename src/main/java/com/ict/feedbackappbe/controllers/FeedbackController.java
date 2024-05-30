package com.ict.feedbackappbe.controllers;

import com.ict.feedbackappbe.models.Feedback;
import com.ict.feedbackappbe.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class FeedbackController {
    @Autowired
    private FeedbackRepository feedbackRepository;
    @CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
    @PostMapping("/feedback/save")
    public ResponseEntity<Map<String, Object>> saveFeedback(@RequestHeader(name = "Authorization") String token,
                                                            @RequestBody Feedback feedback) {
        Map<String, Object> response = new HashMap<>();
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication.isAuthenticated()) {
                Feedback result = feedbackRepository.save(feedback);
                response.put("status", "success");
                return ResponseEntity.status(HttpStatus.CREATED).body(response);
            } else {
                response.put("status", "error");
                response.put("message", "Token validation failed");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
        } catch (Exception e) {
            response.put("status", "error");
            response.put("code", 500);
            response.put("message", e.getMessage());
        }
        return ResponseEntity.ok(response);
    }
}
