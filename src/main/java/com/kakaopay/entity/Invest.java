package com.kakaopay.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name="user")
@Data
public class Invest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long investId;
    private long userId;
    private long myInvestAmount;
    private LocalDateTime investDate;
    private long productId;
    private long investAmount;

    public Invest(){}

    public Invest(final long userId, final long productId, final  long myInvestAmount, final LocalDateTime investDate, final long investAmount){
        this.userId = userId;
        this.myInvestAmount = myInvestAmount;
        this.productId = productId;
        this.investDate = LocalDateTime.now();
        this.investAmount = investAmount;
    }

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "productId", insertable = false, updatable = false)
    private Product product;

    public Product getProduct(){
        return product;
    }

}
