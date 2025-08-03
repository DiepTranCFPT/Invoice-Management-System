package com.example.demo.service;

import com.example.demo.entity.Payment;
import com.example.demo.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PaymentService {
    
    @Autowired
    private PaymentRepository paymentRepository;
    
    public List<Payment> findAll() {
        return paymentRepository.findAll();
    }
    
    public Optional<Payment> findById(Long id) {
        return paymentRepository.findById(id);
    }
    
    public Optional<Payment> findByMaGiaoDich(String maGiaoDich) {
        return paymentRepository.findByMaGiaoDich(maGiaoDich);
    }
    
    public List<Payment> findByUserId(Long userId) {
        return paymentRepository.findByUserId(userId);
    }
    
    public List<Payment> findByInvoiceId(Long invoiceId) {
        return paymentRepository.findByInvoiceId(invoiceId);
    }
    
    public List<Payment> findByAgentId(Long agentId) {
        return paymentRepository.findByAgentId(agentId);
    }
    
    public List<Payment> findByTrangThai(Payment.PaymentStatus trangThai) {
        return paymentRepository.findByTrangThai(trangThai);
    }
    
    public List<Payment> findByAgentIdAndTrangThai(Long agentId, Payment.PaymentStatus trangThai) {
        return paymentRepository.findByAgentIdAndTrangThai(agentId, trangThai);
    }
    
    public List<Payment> findByCreatedDateBetween(LocalDateTime startDate, LocalDateTime endDate) {
        return paymentRepository.findByCreatedDateBetween(startDate, endDate);
    }
    
    public List<Payment> findByAgentIdAndCreatedDateBetween(Long agentId, LocalDateTime startDate, LocalDateTime endDate) {
        return paymentRepository.findByAgentIdAndCreatedDateBetween(agentId, startDate, endDate);
    }
    
    public Payment save(Payment payment) {
        return paymentRepository.save(payment);
    }
    
    public Payment updatePayment(Payment payment) {
        return paymentRepository.save(payment);
    }
    
    public void deleteById(Long id) {
        paymentRepository.deleteById(id);
    }
    
    public Payment confirmPayment(Long paymentId) {
        Optional<Payment> paymentOpt = paymentRepository.findById(paymentId);
        if (paymentOpt.isPresent()) {
            Payment payment = paymentOpt.get();
            payment.setTrangThai(Payment.PaymentStatus.DA_XAC_NHAN);
            payment.setNgayXacNhan(LocalDateTime.now());
            return paymentRepository.save(payment);
        }
        throw new RuntimeException("Payment not found with id: " + paymentId);
    }
    
    public Payment completePayment(Long paymentId) {
        Optional<Payment> paymentOpt = paymentRepository.findById(paymentId);
        if (paymentOpt.isPresent()) {
            Payment payment = paymentOpt.get();
            payment.setTrangThai(Payment.PaymentStatus.HOAN_THANH);
            if (payment.getNgayXacNhan() == null) {
                payment.setNgayXacNhan(LocalDateTime.now());
            }
            return paymentRepository.save(payment);
        }
        throw new RuntimeException("Payment not found with id: " + paymentId);
    }
    
    public Payment cancelPayment(Long paymentId) {
        Optional<Payment> paymentOpt = paymentRepository.findById(paymentId);
        if (paymentOpt.isPresent()) {
            Payment payment = paymentOpt.get();
            payment.setTrangThai(Payment.PaymentStatus.DA_HUY);
            return paymentRepository.save(payment);
        }
        throw new RuntimeException("Payment not found with id: " + paymentId);
    }
    
    public Long countByAgentId(Long agentId) {
        return paymentRepository.countByAgentId(agentId);
    }
    
    public Long countByTrangThai(Payment.PaymentStatus trangThai) {
        return paymentRepository.countByTrangThai(trangThai);
    }
    
    public BigDecimal sumSoTienByAgentIdAndTrangThai(Long agentId, Payment.PaymentStatus trangThai) {
        BigDecimal result = paymentRepository.sumSoTienByAgentIdAndTrangThai(agentId, trangThai);
        return result != null ? result : BigDecimal.ZERO;
    }
    
    public boolean existsByMaGiaoDich(String maGiaoDich) {
        return paymentRepository.findByMaGiaoDich(maGiaoDich).isPresent();
    }
}