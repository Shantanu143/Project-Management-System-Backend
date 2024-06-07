package com.projectmanagement.projectmanagement.service;

import java.util.List;

import com.projectmanagement.projectmanagement.model.Comments;

public interface CommentService {

    Comments createComments(Long issueId, Long userId, String comment) throws Exception;

    void deleteComment(Long commentId, Long userId) throws Exception;

    List<Comments> findCommentsByIssueId(Long issueId);

}
