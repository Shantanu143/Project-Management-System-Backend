package com.projectmanagement.projectmanagement.request;

import lombok.Data;

@Data
public class CreateMessageRequest {

    private Long senderId;
    private Long projectId;
    private String content;
    
}
