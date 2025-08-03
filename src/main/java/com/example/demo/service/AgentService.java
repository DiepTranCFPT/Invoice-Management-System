package com.example.demo.service;

import com.example.demo.entity.Agent;
import com.example.demo.repository.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AgentService {
    
    @Autowired
    private AgentRepository agentRepository;
    
    public List<Agent> findAll() {
        return agentRepository.findAll();
    }
    
    public Optional<Agent> findById(Long id) {
        return agentRepository.findById(id);
    }
    
    public List<Agent> findByStatus(Agent.AgentStatus status) {
        return agentRepository.findByStatus(status);
    }
    
    public List<Agent> findByNameContaining(String name) {
        return agentRepository.findByNameContaining(name);
    }
    
    public Agent save(Agent agent) {
        return agentRepository.save(agent);
    }
    
    public Agent updateAgent(Agent agent) {
        return agentRepository.save(agent);
    }
    
    public void deleteById(Long id) {
        agentRepository.deleteById(id);
    }
    
    public Agent approveAgent(Long agentId) {
        Optional<Agent> agentOpt = agentRepository.findById(agentId);
        if (agentOpt.isPresent()) {
            Agent agent = agentOpt.get();
            agent.setStatus(Agent.AgentStatus.ACTIVE);
            return agentRepository.save(agent);
        }
        throw new RuntimeException("Agent not found with id: " + agentId);
    }
    
    public Agent rejectAgent(Long agentId) {
        Optional<Agent> agentOpt = agentRepository.findById(agentId);
        if (agentOpt.isPresent()) {
            Agent agent = agentOpt.get();
            agent.setStatus(Agent.AgentStatus.INACTIVE);
            return agentRepository.save(agent);
        }
        throw new RuntimeException("Agent not found with id: " + agentId);
    }
    
    public Agent activateAgent(Long agentId) {
        Optional<Agent> agentOpt = agentRepository.findById(agentId);
        if (agentOpt.isPresent()) {
            Agent agent = agentOpt.get();
            agent.setStatus(Agent.AgentStatus.ACTIVE);
            return agentRepository.save(agent);
        }
        throw new RuntimeException("Agent not found with id: " + agentId);
    }
    
    public Agent deactivateAgent(Long agentId) {
        Optional<Agent> agentOpt = agentRepository.findById(agentId);
        if (agentOpt.isPresent()) {
            Agent agent = agentOpt.get();
            agent.setStatus(Agent.AgentStatus.INACTIVE);
            return agentRepository.save(agent);
        }
        throw new RuntimeException("Agent not found with id: " + agentId);
    }
    
    public Long countByStatus(Agent.AgentStatus status) {
        return agentRepository.countByStatus(status);
    }
}