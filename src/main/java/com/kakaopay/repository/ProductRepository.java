package com.kakaopay.repository;

import com.kakaopay.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findByStartedAtBeforeAndFinishedAtAfter(LocalDateTime form, LocalDateTime to);

    Product findByProductId(long productId);
}
