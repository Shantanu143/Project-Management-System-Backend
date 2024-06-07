package com.projectmanagement.projectmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectmanagement.projectmanagement.model.Chat;
import com.projectmanagement.projectmanagement.model.Project;
import com.projectmanagement.projectmanagement.model.User;
import com.projectmanagement.projectmanagement.repository.ProjectRepository;

@Service

public class ProjectServiceimpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ChatService chatService;

    @Override
    public Project createProject(Project project, User user) throws Exception {

        Project createdProject = new Project();
        createdProject.setOwner(user);
        createdProject.setTags(project.getTags());
        createdProject.setName(project.getName());
        createdProject.setCategory(project.getCategory());
        createdProject.setDescription(project.getDescription());
        createdProject.getTeam().add(user);

        Project savedProject = projectRepository.save(createdProject);

        Chat chat = new Chat();
        chat.setProject(savedProject);

        Chat projectChat = chatService.createChat(chat);

        savedProject.setChat(projectChat);

        return savedProject;

    }

    @Override
    public List<Project> getProjectByTeam(User user, String category, String tag) throws Exception {
        
        
  
        return null;
    }

    @Override
    public Project getProjectById(Long projectId) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getProjectById'");
    }

    @Override
    public void deleteProject(Long projectId, Long userId) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteProject'");
    }

    @Override
    public Project updateProject(Project updatedProject, Long id) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateProject'");
    }

    @Override
    public void addUserToProject(Long projectId, Long userId) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addUserToProject'");
    }

    @Override
    public void removeUserToProject(Long projectId, Long userId) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeUserToProject'");
    }

    @Override
    public Chat getChatByProjectId(Long projectId) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getChatByProjectId'");
    }

}
