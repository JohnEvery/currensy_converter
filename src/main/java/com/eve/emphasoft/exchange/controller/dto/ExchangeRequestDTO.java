package com.eve.emphasoft.exchange.controller.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ExchangeRequestDTO {

    private Long userId;

    private BigDecimal amount;

    private String currencyFrom;

    private String currencyTo;

}
