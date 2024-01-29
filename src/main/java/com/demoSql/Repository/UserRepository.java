package com.demoSql.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demoSql.Entity.User;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
