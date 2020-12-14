package com.eve.emphasoft.exchange.controller.dto;

import com.eve.emphasoft.exchange.entity.Exchange;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeSumDTO {

    private Long userId;

    private BigDecimal amount;

    private String currencyFrom;

    public ExchangeSumDTO(Exchange exchange) {
        userId = exchange.getUser().getId();
        amount = exchange.getAmount();
        currencyFrom = exchange.getCurrencyFrom();
    }
}
