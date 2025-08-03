package com.example.demo.config;

import com.example.demo.entity.Agent;
import com.example.demo.entity.User;
import com.example.demo.service.AgentService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private AgentService agentService;
    
    @Override
    public void run(String... args) throws Exception {
        initializeData();
    }
    
    private void initializeData() {
        // Create admin user
        if (!userService.existsByUsername("admin")) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword("admin123");
            admin.setEmail("admin@invoice.com");
            admin.setFullName("System Administrator");
            admin.setPhone("0123456789");
            admin.setRole(User.Role.ADMIN);
            admin.setIsApproved(true);
            userService.save(admin);
            System.out.println("Created admin user: admin / admin123");
        }
        
        // Create sample agent
        if (agentService.findAll().isEmpty()) {
            Agent agent1 = new Agent();
            agent1.setName("ABC Company");
            agent1.setDescription("Sample agent company for testing");
            agent1.setContactInfo("contact@abc.com");
            agent1.setStatus(Agent.AgentStatus.ACTIVE);
            agentService.save(agent1);
            
            Agent agent2 = new Agent();
            agent2.setName("XYZ Corp");
            agent2.setDescription("Another sample agent company");
            agent2.setContactInfo("info@xyz.com");
            agent2.setStatus(Agent.AgentStatus.PENDING);
            agentService.save(agent2);
            
            System.out.println("Created sample agents");
        }
        
        // Create sample agent user
        if (!userService.existsByUsername("agent1")) {
            Agent agent = agentService.findAll().get(0);
            
            User agentUser = new User();
            agentUser.setUsername("agent1");
            agentUser.setPassword("agent123");
            agentUser.setEmail("agent1@invoice.com");
            agentUser.setFullName("Agent User 1");
            agentUser.setPhone("0987654321");
            agentUser.setRole(User.Role.AGENT);
            agentUser.setAgent(agent);
            agentUser.setIsApproved(true);
            userService.save(agentUser);
            System.out.println("Created agent user: agent1 / agent123");
        }
        
        // Create sample end user
        if (!userService.existsByUsername("user1")) {
            Agent agent = agentService.findAll().get(0);
            
            User endUser = new User();
            endUser.setUsername("user1");
            endUser.setPassword("user123");
            endUser.setEmail("user1@invoice.com");
            endUser.setFullName("End User 1");
            endUser.setPhone("0123987456");
            endUser.setRole(User.Role.USER);
            endUser.setAgent(agent);
            endUser.setIsApproved(true);
            userService.save(endUser);
            System.out.println("Created end user: user1 / user123");
        }
    }
}