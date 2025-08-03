package com.example.demo.controller;

import com.example.demo.entity.Agent;
import com.example.demo.entity.User;
import com.example.demo.service.AgentService;
import com.example.demo.service.InvoiceService;
import com.example.demo.service.PaymentService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private AgentService agentService;
    
    @Autowired
    private InvoiceService invoiceService;
    
    @Autowired
    private PaymentService paymentService;
    
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        // Dashboard statistics
        model.addAttribute("totalUsers", userService.countByRole(User.Role.USER));
        model.addAttribute("totalAgents", agentService.countByStatus(Agent.AgentStatus.ACTIVE));
        model.addAttribute("pendingAgents", agentService.countByStatus(Agent.AgentStatus.PENDING));
        model.addAttribute("totalInvoices", invoiceService.findAll().size());
        model.addAttribute("totalPayments", paymentService.findAll().size());
        
        return "admin/dashboard";
    }
    
    @GetMapping("/agents")
    public String agentsList(Model model) {
        model.addAttribute("agents", agentService.findAll());
        return "admin/agents";
    }
    
    @PostMapping("/agents/{id}/approve")
    @ResponseBody
    public String approveAgent(@PathVariable Long id) {
        try {
            agentService.approveAgent(id);
            return "success";
        } catch (Exception e) {
            return "error";
        }
    }
    
    @PostMapping("/agents/{id}/reject")
    @ResponseBody
    public String rejectAgent(@PathVariable Long id) {
        try {
            agentService.rejectAgent(id);
            return "success";
        } catch (Exception e) {
            return "error";
        }
    }
    
    @PostMapping("/agents/{id}/activate")
    @ResponseBody
    public String activateAgent(@PathVariable Long id) {
        try {
            agentService.activateAgent(id);
            return "success";
        } catch (Exception e) {
            return "error";
        }
    }
    
    @PostMapping("/agents/{id}/deactivate")
    @ResponseBody
    public String deactivateAgent(@PathVariable Long id) {
        try {
            agentService.deactivateAgent(id);
            return "success";
        } catch (Exception e) {
            return "error";
        }
    }
    
    @GetMapping("/users")
    public String usersList(Model model) {
        model.addAttribute("users", userService.findAll());
        return "admin/users";
    }
    
    @GetMapping("/statistics")
    public String statistics(Model model) {
        // Add comprehensive statistics
        model.addAttribute("userStats", userService.findAll());
        model.addAttribute("agentStats", agentService.findAll());
        model.addAttribute("invoiceStats", invoiceService.findAll());
        model.addAttribute("paymentStats", paymentService.findAll());
        
        return "admin/statistics";
    }
}