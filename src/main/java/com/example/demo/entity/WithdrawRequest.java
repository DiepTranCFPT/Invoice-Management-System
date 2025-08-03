package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "withdraw_requests")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class WithdrawRequest {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "so_tien_yeu_cau", nullable = false, precision = 15, scale = 2)
    private BigDecimal soTienYeuCau;
    
    @Column(name = "so_don_yeu_cau")
    private Integer soDonYeuCau;
    
    @Column(columnDefinition = "TEXT")
    private String note;
    
    @Column(name = "phan_hoi", columnDefinition = "TEXT")
    private String phanHoi;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "trang_thai", nullable = false)
    private WithdrawStatus trangThai = WithdrawStatus.CHO_XU_LY;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @CreatedDate
    @Column(name = "created_date", nullable = false, updatable = false)
    private LocalDateTime createdDate;
    
    @Column(name = "processed_date")
    private LocalDateTime processedDate;
    
    public enum WithdrawStatus {
        CHO_XU_LY, DA_DUYET, TU_CHOI
    }
}