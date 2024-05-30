package com.ict.feedbackappbe.repository;

import com.ict.feedbackappbe.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<UserModel, Integer> {
    @Query(value = "SELECT * FROM users WHERE email=?1 and password=?2", nativeQuery = true)
    UserModel login (String email, String password);

    @Query(value = "SELECT * FROM users WHERE email=?1", nativeQuery = true)
    UserModel emailExists(String email);
}
