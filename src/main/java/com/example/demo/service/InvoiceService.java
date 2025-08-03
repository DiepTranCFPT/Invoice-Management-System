package com.example.demo.service;

import com.example.demo.entity.Invoice;
import com.example.demo.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class InvoiceService {
    
    @Autowired
    private InvoiceRepository invoiceRepository;
    
    public List<Invoice> findAll() {
        return invoiceRepository.findAll();
    }
    
    public Optional<Invoice> findById(Long id) {
        return invoiceRepository.findById(id);
    }
    
    public Optional<Invoice> findBySoHopDong(String soHopDong) {
        return invoiceRepository.findBySoHopDong(soHopDong);
    }
    
    public List<Invoice> findByAgentId(Long agentId) {
        return invoiceRepository.findByAgentId(agentId);
    }
    
    public List<Invoice> findByNguoiNhanId(Long nguoiNhanId) {
        return invoiceRepository.findByNguoiNhanId(nguoiNhanId);
    }
    
    public List<Invoice> findByTrangThai(Invoice.InvoiceStatus trangThai) {
        return invoiceRepository.findByTrangThai(trangThai);
    }
    
    public List<Invoice> findByAgentIdAndTrangThai(Long agentId, Invoice.InvoiceStatus trangThai) {
        return invoiceRepository.findByAgentIdAndTrangThai(agentId, trangThai);
    }
    
    public List<Invoice> findByCreatedDateBetween(LocalDateTime startDate, LocalDateTime endDate) {
        return invoiceRepository.findByCreatedDateBetween(startDate, endDate);
    }
    
    public List<Invoice> findByAgentIdAndCreatedDateBetween(Long agentId, LocalDateTime startDate, LocalDateTime endDate) {
        return invoiceRepository.findByAgentIdAndCreatedDateBetween(agentId, startDate, endDate);
    }
    
    public Invoice save(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }
    
    public Invoice updateInvoice(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }
    
    public void deleteById(Long id) {
        invoiceRepository.deleteById(id);
    }
    
    public Invoice confirmInvoice(Long invoiceId) {
        Optional<Invoice> invoiceOpt = invoiceRepository.findById(invoiceId);
        if (invoiceOpt.isPresent()) {
            Invoice invoice = invoiceOpt.get();
            invoice.setTrangThai(Invoice.InvoiceStatus.DA_XAC_NHAN);
            return invoiceRepository.save(invoice);
        }
        throw new RuntimeException("Invoice not found with id: " + invoiceId);
    }
    
    public Invoice completeInvoice(Long invoiceId) {
        Optional<Invoice> invoiceOpt = invoiceRepository.findById(invoiceId);
        if (invoiceOpt.isPresent()) {
            Invoice invoice = invoiceOpt.get();
            invoice.setTrangThai(Invoice.InvoiceStatus.HOAN_THANH);
            return invoiceRepository.save(invoice);
        }
        throw new RuntimeException("Invoice not found with id: " + invoiceId);
    }
    
    public Invoice cancelInvoice(Long invoiceId) {
        Optional<Invoice> invoiceOpt = invoiceRepository.findById(invoiceId);
        if (invoiceOpt.isPresent()) {
            Invoice invoice = invoiceOpt.get();
            invoice.setTrangThai(Invoice.InvoiceStatus.DA_HUY);
            return invoiceRepository.save(invoice);
        }
        throw new RuntimeException("Invoice not found with id: " + invoiceId);
    }
    
    public Long countByAgentId(Long agentId) {
        return invoiceRepository.countByAgentId(agentId);
    }
    
    public Long countByTrangThai(Invoice.InvoiceStatus trangThai) {
        return invoiceRepository.countByTrangThai(trangThai);
    }
    
    public BigDecimal sumSoTienByAgentIdAndTrangThai(Long agentId, Invoice.InvoiceStatus trangThai) {
        BigDecimal result = invoiceRepository.sumSoTienByAgentIdAndTrangThai(agentId, trangThai);
        return result != null ? result : BigDecimal.ZERO;
    }
    
    public boolean existsBySoHopDong(String soHopDong) {
        return invoiceRepository.findBySoHopDong(soHopDong).isPresent();
    }
}