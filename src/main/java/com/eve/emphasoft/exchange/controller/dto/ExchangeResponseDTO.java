package com.eve.emphasoft.exchange.controller.dto;

import com.eve.emphasoft.exchange.entity.Exchange;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class ExchangeResponseDTO {

    private Long exchangeId;

    private BigDecimal amountAfterExchange;

    public ExchangeResponseDTO(Exchange exchange) {
        exchangeId = exchange.getId();
        amountAfterExchange = exchange.getAmountAfterExchange();
    }
}
