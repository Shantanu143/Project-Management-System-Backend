package com.projectmanagement.projectmanagement.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectmanagement.projectmanagement.model.Comments;
import com.projectmanagement.projectmanagement.model.Issue;
import com.projectmanagement.projectmanagement.model.User;
import com.projectmanagement.projectmanagement.repository.CommentRepository;
import com.projectmanagement.projectmanagement.repository.IssueRepository;
import com.projectmanagement.projectmanagement.repository.UserRepository;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Comments createComments(Long issueId, Long userId, String comment) throws Exception {

        Optional<Issue> issOptional = issueRepository.findById(issueId);
        Optional<User> userOptional = userRepository.findById(userId);

        if (issOptional.isEmpty()) {
            throw new Exception("Issue not found with id : " + issueId);
        }

        if (userOptional.isEmpty()) {
            throw new Exception("User not found with id : " + userId);
        }
        Issue issue = issOptional.get();
        User user = userOptional.get();

        Comments comments = new Comments();
        comments.setIssue(issue);
        comments.setUser(user);
        comments.setCreateDateTime(LocalDateTime.now());
        comments.setContent(comment);

        Comments saveComments = commentRepository.save(comments);

        issue.getComments().add(saveComments);

        return saveComments;
    }

    @Override
    public void deleteComment(Long commentId, Long userId) throws Exception {

        Optional<Comments> commentsOptional = commentRepository.findById(commentId);
        Optional<User> userOptional = userRepository.findById(userId);

        if (commentsOptional.isEmpty()) {
            throw new Exception("Comment not found with id : " + commentId);
        }
        if (userOptional.isEmpty()) {
            throw new Exception("User not found with id : " + userId);
        }
        Comments comments = commentsOptional.get();

        User user = userOptional.get();

        if (comments.getUser().equals(user)) {
            commentRepository.delete(comments);
        } else {
            throw new Exception("User not authorized to delete this comment");
        }

    }

    @Override
    public List<Comments> findCommentsByIssueId(Long issueId) {
        return commentRepository.findByIssueId(issueId);
    }

}
