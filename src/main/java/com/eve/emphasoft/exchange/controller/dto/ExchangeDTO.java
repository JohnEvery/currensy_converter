package com.eve.emphasoft.exchange.controller.dto;

import com.eve.emphasoft.exchange.entity.Exchange;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class ExchangeDTO {

    private Long exchangeId;

    private Long userId;

    private BigDecimal amount;

    private String currencyFrom;

    private String currencyTo;

    private BigDecimal amountAfterExchange;

    public ExchangeDTO(Exchange exchange) {
        exchangeId = exchange.getId();
        userId = exchange.getUser().getId();
        amount = exchange.getAmount();
        currencyFrom = exchange.getCurrencyFrom();
        currencyTo = exchange.getCurrencyTo();
        amountAfterExchange = exchange.getAmountAfterExchange();
    }


}
