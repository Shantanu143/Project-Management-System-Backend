package com.projectmanagement.projectmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectmanagement.projectmanagement.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

}
