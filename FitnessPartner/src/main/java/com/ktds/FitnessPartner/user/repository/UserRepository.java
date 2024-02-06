package com.ktds.FitnessPartner.user.repository;

import com.ktds.FitnessPartner.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmailAndPassword(String email, String password);
}
