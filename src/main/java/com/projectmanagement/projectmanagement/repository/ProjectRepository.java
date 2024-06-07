package com.projectmanagement.projectmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.projectmanagement.projectmanagement.model.Project;
import com.projectmanagement.projectmanagement.model.User;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    List<Project> findByOwner(User owner);

    List<Project> findByNameContainingAndTeamContaining(String partialName, User user);

    @Query("SELECT p FROM Project p join p.team t where t = :user")
    List<Project> findProjectByTeam(@Param("user") User user);

    List<Project> findByTeamContainingOrOwner(User user, User owner);

}
