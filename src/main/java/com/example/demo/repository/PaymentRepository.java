package com.example.demo.repository;

import com.example.demo.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    
    Optional<Payment> findByMaGiaoDich(String maGiaoDich);
    
    List<Payment> findByUserId(Long userId);
    
    List<Payment> findByInvoiceId(Long invoiceId);
    
    List<Payment> findByAgentId(Long agentId);
    
    List<Payment> findByTrangThai(Payment.PaymentStatus trangThai);
    
    @Query("SELECT p FROM Payment p WHERE p.agent.id = ?1 AND p.trangThai = ?2")
    List<Payment> findByAgentIdAndTrangThai(Long agentId, Payment.PaymentStatus trangThai);
    
    @Query("SELECT COUNT(p) FROM Payment p WHERE p.agent.id = ?1")
    Long countByAgentId(Long agentId);
    
    @Query("SELECT COUNT(p) FROM Payment p WHERE p.trangThai = ?1")
    Long countByTrangThai(Payment.PaymentStatus trangThai);
    
    @Query("SELECT SUM(p.soTien) FROM Payment p WHERE p.agent.id = ?1 AND p.trangThai = ?2")
    BigDecimal sumSoTienByAgentIdAndTrangThai(Long agentId, Payment.PaymentStatus trangThai);
    
    @Query("SELECT p FROM Payment p WHERE p.createdDate BETWEEN ?1 AND ?2")
    List<Payment> findByCreatedDateBetween(LocalDateTime startDate, LocalDateTime endDate);
    
    @Query("SELECT p FROM Payment p WHERE p.agent.id = ?1 AND p.createdDate BETWEEN ?2 AND ?3")
    List<Payment> findByAgentIdAndCreatedDateBetween(Long agentId, LocalDateTime startDate, LocalDateTime endDate);
}