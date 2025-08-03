package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    Optional<User> findByUsername(String username);
    
    Optional<User> findByEmail(String email);
    
    List<User> findByRole(User.Role role);
    
    List<User> findByAgentId(Long agentId);
    
    List<User> findByIsApproved(Boolean isApproved);
    
    @Query("SELECT u FROM User u WHERE u.agent.id = ?1 AND u.isApproved = ?2")
    List<User> findByAgentIdAndIsApproved(Long agentId, Boolean isApproved);
    
    @Query("SELECT COUNT(u) FROM User u WHERE u.role = ?1")
    Long countByRole(User.Role role);
    
    @Query("SELECT COUNT(u) FROM User u WHERE u.agent.id = ?1")
    Long countByAgentId(Long agentId);
}