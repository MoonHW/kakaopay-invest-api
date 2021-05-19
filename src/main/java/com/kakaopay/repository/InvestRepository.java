package com.kakaopay.repository;


import com.kakaopay.entity.Invest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvestRepository extends JpaRepository <Invest, String> {
    Invest findByProductIdAndUserId(Long investedProductId, Long xUserId);

    List<Invest> findByUserId(Long xUserId);
}
