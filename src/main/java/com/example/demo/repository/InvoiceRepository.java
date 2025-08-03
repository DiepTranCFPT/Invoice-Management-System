package com.example.demo.repository;

import com.example.demo.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    
    Optional<Invoice> findBySoHopDong(String soHopDong);
    
    List<Invoice> findByAgentId(Long agentId);
    
    List<Invoice> findByNguoiNhanId(Long nguoiNhanId);
    
    List<Invoice> findByTrangThai(Invoice.InvoiceStatus trangThai);
    
    @Query("SELECT i FROM Invoice i WHERE i.agent.id = ?1 AND i.trangThai = ?2")
    List<Invoice> findByAgentIdAndTrangThai(Long agentId, Invoice.InvoiceStatus trangThai);
    
    @Query("SELECT COUNT(i) FROM Invoice i WHERE i.agent.id = ?1")
    Long countByAgentId(Long agentId);
    
    @Query("SELECT COUNT(i) FROM Invoice i WHERE i.trangThai = ?1")
    Long countByTrangThai(Invoice.InvoiceStatus trangThai);
    
    @Query("SELECT SUM(i.soTien) FROM Invoice i WHERE i.agent.id = ?1 AND i.trangThai = ?2")
    BigDecimal sumSoTienByAgentIdAndTrangThai(Long agentId, Invoice.InvoiceStatus trangThai);
    
    @Query("SELECT i FROM Invoice i WHERE i.createdDate BETWEEN ?1 AND ?2")
    List<Invoice> findByCreatedDateBetween(LocalDateTime startDate, LocalDateTime endDate);
    
    @Query("SELECT i FROM Invoice i WHERE i.agent.id = ?1 AND i.createdDate BETWEEN ?2 AND ?3")
    List<Invoice> findByAgentIdAndCreatedDateBetween(Long agentId, LocalDateTime startDate, LocalDateTime endDate);
}