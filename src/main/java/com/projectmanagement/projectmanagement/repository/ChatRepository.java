package com.projectmanagement.projectmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectmanagement.projectmanagement.model.Chat;

public interface ChatRepository extends JpaRepository<Chat, Long> {

}
