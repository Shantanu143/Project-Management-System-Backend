package com.projectmanagement.projectmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectmanagement.projectmanagement.model.Comments;

public interface CommentRepository extends JpaRepository<Comments, Long> {

    List<Comments> findByIssueId(Long issueId);
    
}
