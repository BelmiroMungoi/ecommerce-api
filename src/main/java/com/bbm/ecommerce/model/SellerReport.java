package com.bbm.ecommerce.model;

import com.bbm.ecommerce.model.enums.ReportStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "seller_reports")
public class SellerReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String reportDetails;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReportStatus status;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private LocalDateTime reportDate;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime resolvedDate;

    @ManyToOne
    @JoinColumn(name = "seller_id", nullable = false)
    private Seller seller;

    @PrePersist
    protected void onCreate() {
        this.reportDate = LocalDateTime.now();
    }

    public void markAsResolved() {
        this.status = ReportStatus.RESOLVED;
        this.resolvedDate = LocalDateTime.now();
    }
}
