package com.ict.feedbackappbe.controllers;

import com.ict.feedbackappbe.models.CourseEntity;
import com.ict.feedbackappbe.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CourseController {
    @Autowired
    private CourseRepository courseRepository;
    @CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
    @PostMapping("/course/save")
    public ResponseEntity<Map<String, Object>> saveCourse(@RequestHeader(name = "Authorization") String token,
                                                            @RequestBody CourseEntity course) {
        Map<String, Object> response = new HashMap<>();
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication.isAuthenticated()) {
                course.setCreatedDate(LocalDateTime.now());
                course.setModifiedDate(LocalDateTime.now());
                courseRepository.save(course);
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

    @CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
    @GetMapping("/course/get")
    public ResponseEntity<Map<String, Object>> getCoursesByTrainer(@RequestHeader(name = "Authorization") String token,
                                                          @RequestParam int trainerId) {
        Map<String, Object> response = new HashMap<>();
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication.isAuthenticated()) {
                List<CourseEntity> courses = courseRepository.getCoursesByTrainer(trainerId);
                response.put("status", "success");
                response.put("code", 200);
                response.put("courses", courses);
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

    @CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
    @DeleteMapping("/course/delete")
    public ResponseEntity<Map<String, Object>> deleteCourse (@RequestHeader(name = "Authorization") String token,
                                                              @RequestParam int id){

        Map<String, Object> response = new HashMap<>();
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (authentication.isAuthenticated())
            {
                courseRepository.deleteById(id);
                response.put("status", "success");
                response.put("code", 200);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }
            else {
                response.put("status", "error");
                response.put("message", "Token validation failed");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
        } catch (Exception e){
            response.put("status", "error");
            response.put("code", 500);
            response.put("message", e.getMessage());
        }
        return ResponseEntity.ok(response);
    }
}
