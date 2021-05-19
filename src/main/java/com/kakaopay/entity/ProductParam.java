package com.kakaopay.entity;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
public class ProductParam {
    @NotBlank
    private String title;
    private LocalDateTime startedAt;
    private LocalDateTime finishedAt;
}
