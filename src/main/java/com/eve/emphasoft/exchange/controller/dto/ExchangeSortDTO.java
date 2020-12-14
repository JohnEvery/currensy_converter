package com.eve.emphasoft.exchange.controller.dto;

import com.eve.emphasoft.exchange.entity.Exchange;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class ExchangeSortDTO {

    private Long exchangeId;

    private Long userId;

    private BigDecimal amount;

    public ExchangeSortDTO(Exchange exchange) {
        exchangeId = exchange.getId();
        userId = exchange.getUser().getId();
        amount = exchange.getAmount();
    }
}
