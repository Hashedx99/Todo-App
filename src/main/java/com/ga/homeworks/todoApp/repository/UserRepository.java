package com.ga.homeworks.todoApp.repository;

import com.ga.homeworks.todoApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmailAddress(String email);
    Optional<User> findByEmailAddress(String email);
}
