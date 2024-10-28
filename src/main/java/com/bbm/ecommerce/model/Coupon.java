package com.bbm.ecommerce.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cupons")
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 20)
    private String code;

    @Column(nullable = false)
    private double discount;

    @Column(nullable = false)
    private boolean isActive = true;

    @Column(nullable = false)
    private LocalDate expirationDate;

    @ManyToMany(mappedBy = "coupons")
    private List<User> users = new ArrayList<>();

    public boolean isExpired() {
        return LocalDate.now().isAfter(this.expirationDate);
    }
}
