package com.task.user.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.task.user.user.entity.VerificationToken;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
    VerificationToken findByToken(String token);

}
