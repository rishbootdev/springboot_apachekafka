package com.rishbootdev.userservice.repository;

import com.rishbootdev.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
