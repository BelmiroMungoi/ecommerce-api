package com.bbm.ecommerce.repository;

import com.bbm.ecommerce.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {

    boolean existsByEmail(String email);

    @Query("""
                SELECT s FROM Seller s
                JOIN FETCH s.address
            """)
    List<Seller> findAllSellers();
}
