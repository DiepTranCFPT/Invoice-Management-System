package com.example.demo.repository;

import com.example.demo.entity.WithdrawRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface WithdrawRequestRepository extends JpaRepository<WithdrawRequest, Long> {
    
    List<WithdrawRequest> findByUserId(Long userId);
    
    List<WithdrawRequest> findByTrangThai(WithdrawRequest.WithdrawStatus trangThai);
    
    @Query("SELECT w FROM WithdrawRequest w WHERE w.user.agent.id = ?1")
    List<WithdrawRequest> findByUserAgentId(Long agentId);
    
    @Query("SELECT w FROM WithdrawRequest w WHERE w.user.agent.id = ?1 AND w.trangThai = ?2")
    List<WithdrawRequest> findByUserAgentIdAndTrangThai(Long agentId, WithdrawRequest.WithdrawStatus trangThai);
    
    @Query("SELECT COUNT(w) FROM WithdrawRequest w WHERE w.user.agent.id = ?1")
    Long countByUserAgentId(Long agentId);
    
    @Query("SELECT COUNT(w) FROM WithdrawRequest w WHERE w.trangThai = ?1")
    Long countByTrangThai(WithdrawRequest.WithdrawStatus trangThai);
    
    @Query("SELECT SUM(w.soTienYeuCau) FROM WithdrawRequest w WHERE w.user.agent.id = ?1 AND w.trangThai = ?2")
    BigDecimal sumSoTienYeuCauByUserAgentIdAndTrangThai(Long agentId, WithdrawRequest.WithdrawStatus trangThai);
    
    @Query("SELECT w FROM WithdrawRequest w WHERE w.createdDate BETWEEN ?1 AND ?2")
    List<WithdrawRequest> findByCreatedDateBetween(LocalDateTime startDate, LocalDateTime endDate);
}