package com.kakaopay.entity;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class InvestParam {
    @NotNull
    private long productId;

    @NotNull
    @Min(1)
    private long investAmount;
}
