package com.projectmanagement.projectmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projectmanagement.projectmanagement.model.Chat;
import com.projectmanagement.projectmanagement.model.Message;
import com.projectmanagement.projectmanagement.model.User;
import com.projectmanagement.projectmanagement.request.CreateMessageRequest;
import com.projectmanagement.projectmanagement.service.MessageService;
import com.projectmanagement.projectmanagement.service.ProjectService;
import com.projectmanagement.projectmanagement.service.UserService;

@RestController
@RequestMapping("/api/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectService projectService;

    @PostMapping("/send")

    public ResponseEntity<Message> sendMessage(
            @RequestBody CreateMessageRequest request) throws Exception {

        User user = userService.findUserById(request.getSenderId());
        if (user == null) {
            throw new Exception("User not found with id : " + request.getSenderId());
        }
        Chat chat = projectService.getProjectById(request.getProjectId()).getChat();

        if (chat == null) {
            throw new Exception("Chat not found with id : " + request.getProjectId());
        }

        Message message = messageService.sendMessage(request.getSenderId(), request.getProjectId(),
                request.getContent());

        return ResponseEntity.ok(message);
    }

    @GetMapping("/chat/{projectId}")
    public ResponseEntity<List<Message>> getMessagesByChatId(
            @PathVariable Long projectId) throws Exception {

        List<Message> messages = messageService.getMessagesByProjectId(projectId);

        return ResponseEntity.ok(messages);
    }
}
