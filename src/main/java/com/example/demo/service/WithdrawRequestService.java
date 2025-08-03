package com.example.demo.service;

import com.example.demo.entity.WithdrawRequest;
import com.example.demo.repository.WithdrawRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class WithdrawRequestService {
    
    @Autowired
    private WithdrawRequestRepository withdrawRequestRepository;
    
    public List<WithdrawRequest> findAll() {
        return withdrawRequestRepository.findAll();
    }
    
    public Optional<WithdrawRequest> findById(Long id) {
        return withdrawRequestRepository.findById(id);
    }
    
    public List<WithdrawRequest> findByUserId(Long userId) {
        return withdrawRequestRepository.findByUserId(userId);
    }
    
    public List<WithdrawRequest> findByTrangThai(WithdrawRequest.WithdrawStatus trangThai) {
        return withdrawRequestRepository.findByTrangThai(trangThai);
    }
    
    public List<WithdrawRequest> findByUserAgentId(Long agentId) {
        return withdrawRequestRepository.findByUserAgentId(agentId);
    }
    
    public List<WithdrawRequest> findByUserAgentIdAndTrangThai(Long agentId, WithdrawRequest.WithdrawStatus trangThai) {
        return withdrawRequestRepository.findByUserAgentIdAndTrangThai(agentId, trangThai);
    }
    
    public List<WithdrawRequest> findByCreatedDateBetween(LocalDateTime startDate, LocalDateTime endDate) {
        return withdrawRequestRepository.findByCreatedDateBetween(startDate, endDate);
    }
    
    public WithdrawRequest save(WithdrawRequest withdrawRequest) {
        return withdrawRequestRepository.save(withdrawRequest);
    }
    
    public WithdrawRequest updateWithdrawRequest(WithdrawRequest withdrawRequest) {
        return withdrawRequestRepository.save(withdrawRequest);
    }
    
    public void deleteById(Long id) {
        withdrawRequestRepository.deleteById(id);
    }
    
    public WithdrawRequest approveWithdrawRequest(Long withdrawRequestId, String phanHoi) {
        Optional<WithdrawRequest> withdrawRequestOpt = withdrawRequestRepository.findById(withdrawRequestId);
        if (withdrawRequestOpt.isPresent()) {
            WithdrawRequest withdrawRequest = withdrawRequestOpt.get();
            withdrawRequest.setTrangThai(WithdrawRequest.WithdrawStatus.DA_DUYET);
            withdrawRequest.setPhanHoi(phanHoi);
            withdrawRequest.setProcessedDate(LocalDateTime.now());
            return withdrawRequestRepository.save(withdrawRequest);
        }
        throw new RuntimeException("Withdraw request not found with id: " + withdrawRequestId);
    }
    
    public WithdrawRequest rejectWithdrawRequest(Long withdrawRequestId, String phanHoi) {
        Optional<WithdrawRequest> withdrawRequestOpt = withdrawRequestRepository.findById(withdrawRequestId);
        if (withdrawRequestOpt.isPresent()) {
            WithdrawRequest withdrawRequest = withdrawRequestOpt.get();
            withdrawRequest.setTrangThai(WithdrawRequest.WithdrawStatus.TU_CHOI);
            withdrawRequest.setPhanHoi(phanHoi);
            withdrawRequest.setProcessedDate(LocalDateTime.now());
            return withdrawRequestRepository.save(withdrawRequest);
        }
        throw new RuntimeException("Withdraw request not found with id: " + withdrawRequestId);
    }
    
    public Long countByUserAgentId(Long agentId) {
        return withdrawRequestRepository.countByUserAgentId(agentId);
    }
    
    public Long countByTrangThai(WithdrawRequest.WithdrawStatus trangThai) {
        return withdrawRequestRepository.countByTrangThai(trangThai);
    }
    
    public BigDecimal sumSoTienYeuCauByUserAgentIdAndTrangThai(Long agentId, WithdrawRequest.WithdrawStatus trangThai) {
        BigDecimal result = withdrawRequestRepository.sumSoTienYeuCauByUserAgentIdAndTrangThai(agentId, trangThai);
        return result != null ? result : BigDecimal.ZERO;
    }
}