package com.ict.feedbackappbe.controllers;

import com.ict.feedbackappbe.models.UserModel;
import com.ict.feedbackappbe.repository.UserRepository;
import com.ict.feedbackappbe.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody UserModel user){
        Map<String, Object> response = new HashMap<>();
        try{
            UserModel userData = userRepository.login(user.getEmail(), user.getPassword());
            if (userData != null) {
                // Token Generated
                response.put("status", "success");
                response.put("code", 200);
                response.put("id", userData.getId());
                response.put("name", userData.getName());
                response.put("profession", userData.getProfession());
                response.put("token", JwtUtils.generateToken(userData.getEmail()));
            } else {
                response.put("code", "404");
            }
        } catch (Exception e){
            response.put("status", "error");
            response.put("code", 500);
            response.put("message", e.getMessage());
        }
        return ResponseEntity.ok(response);
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/emailexists")
    public ResponseEntity<Map<String, Object>> emailExists (@RequestParam String email){
        Map<String, Object> response = new HashMap<>();
        try{
            UserModel userData = userRepository.emailExists(email);
            if (userData != null){
                response.put("status", "success");
                response.put("code" , 200);
                response.put("id", userData.getId());
            } else {
                response.put("code" , 404);
                response.put("id", "email does not exist");
            }
        } catch (Exception e){
            response.put("status", "error");
            response.put("code", 500);
            response.put("message", e.getMessage());
        }
        return ResponseEntity.ok(response);
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/signup")
    public  ResponseEntity<Map<String, Object>> createUser(@RequestBody UserModel user){
        Map<String, Object> response = new HashMap<>();
        try {
            UserModel userData =new UserModel();
            userData.setName(user.getName());
            userData.setEmail(user.getEmail());
            userData.setPassword(user.getPassword());
            userData.setProfession(user.getProfession());
            UserModel result = userRepository.save(userData);
            response.put("status", "success");
            response.put("code", 201);
            response.put("id", result.getId());
            response.put("name", result.getName());
            response.put("token", JwtUtils.generateToken(result.getEmail()));
;        } catch (Exception ex) {
            response.put("status", "error");
            response.put("code", 500);
            response.put("message", ex.getMessage());
        }
        return ResponseEntity.ok(response);
    }
}

