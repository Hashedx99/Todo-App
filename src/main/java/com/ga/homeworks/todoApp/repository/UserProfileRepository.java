package com.ga.homeworks.todoApp.repository;

import com.ga.homeworks.todoApp.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
}
