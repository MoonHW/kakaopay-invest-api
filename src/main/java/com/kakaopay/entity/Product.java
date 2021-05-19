package com.kakaopay.entity;
import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name="products")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productId;
    private String title;

    @Column(nullable = false)
    private long totalInvestingAmount;
    private long currentInvestingAmount;

    private LocalDateTime startedAt;
    private LocalDateTime finishedAt;
    private String productStatus;
    private long investors;

    private Product(){
    }

}
