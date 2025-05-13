package com.bbm.ecommerce.model;

import com.bbm.ecommerce.model.enums.OrderStatus;
import jakarta.persistence.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "address_id", nullable = false)
    private Address shippingAddress;

    @Column(nullable = false)
    private double totalAmount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
    private LocalDateTime placedAt;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime completedAt;

    @PrePersist
    protected void onCreate() {
        this.placedAt = LocalDateTime.now();
        recalculateTotalAmount();
    }

    public void recalculateTotalAmount() {
        totalAmount = orderItems.stream().mapToDouble(i -> i.getPrice() * i.getQuantity()).sum();
    }

    public void completeOrder() {
        this.status = OrderStatus.COMPLETED;
        this.completedAt = LocalDateTime.now();
    }
}
