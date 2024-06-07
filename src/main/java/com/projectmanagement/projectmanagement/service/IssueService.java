package com.projectmanagement.projectmanagement.service;

import java.util.List;
import java.util.Optional;

import com.projectmanagement.projectmanagement.model.Issue;
import com.projectmanagement.projectmanagement.model.User;
import com.projectmanagement.projectmanagement.request.IssueRequest;

public interface IssueService {

    Issue getIssueById(Long issueId) throws Exception;

    List<Issue> getIssuesByProjectId(Long projectId) throws Exception;

    Issue createIssue(IssueRequest issue, User user) throws Exception;

    void deleteIssue(Long issueId, Long userId) throws Exception;

    Issue addUserToIssue(Long issueId, Long userId) throws Exception;

    Issue updateStatus(Long issueId, String status) throws Exception;

}
