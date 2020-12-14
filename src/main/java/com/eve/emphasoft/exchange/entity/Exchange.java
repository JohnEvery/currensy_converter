package com.eve.emphasoft.exchange.entity;

import com.eve.emphasoft.user.entity.UserEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
public class Exchange {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private UserEntity user;

    private BigDecimal amount;

    private String currencyFrom;

    private String currencyTo;

    private BigDecimal amountAfterExchange;

    public Exchange(UserEntity user, BigDecimal amount, String currencyFrom, String currencyTo, BigDecimal amountAfterExchange) {
        this.user = user;
        this.amount = amount;
        this.currencyFrom = currencyFrom;
        this.currencyTo = currencyTo;
        this.amountAfterExchange = amountAfterExchange;
    }
}
