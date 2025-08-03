package com.example.demo.repository;

import com.example.demo.entity.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgentRepository extends JpaRepository<Agent, Long> {
    
    List<Agent> findByStatus(Agent.AgentStatus status);
    
    @Query("SELECT COUNT(a) FROM Agent a WHERE a.status = ?1")
    Long countByStatus(Agent.AgentStatus status);
    
    @Query("SELECT a FROM Agent a WHERE a.name LIKE %?1%")
    List<Agent> findByNameContaining(String name);
}