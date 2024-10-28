package com.bbm.ecommerce.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "carts")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> cartItems = new ArrayList<>();

    @Column(nullable = false)
    private double totalAmount;

    public void addItem(CartItem item) {
        cartItems.add(item);
        item.setCart(this);
        recalculateTotalAmount();
    }

    public void removeItem(CartItem item) {
        cartItems.remove(item);
        item.setCart(null);
        recalculateTotalAmount();
    }

    public void recalculateTotalAmount() {
        totalAmount = cartItems.stream().mapToDouble(i -> i.getPrice() * i.getQuantity()).sum();
    }

    @PrePersist
    protected void onCreate() {
        recalculateTotalAmount();
    }
}
