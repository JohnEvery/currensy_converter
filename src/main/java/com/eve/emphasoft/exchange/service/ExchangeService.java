package com.eve.emphasoft.exchange.service;

import com.eve.emphasoft.currency.service.CurrencyService;
import com.eve.emphasoft.exceptions.ExchangeNotFoundException;
import com.eve.emphasoft.exchange.entity.Exchange;
import com.eve.emphasoft.exchange.repository.ExchangeRepository;
import com.eve.emphasoft.user.entity.UserEntity;
import com.eve.emphasoft.user.service.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public class ExchangeService {

    private final ExchangeRepository exchangeRepository;
    private final UserEntityService userEntityService;
    private final CurrencyService currencyService;

    @Autowired
    public ExchangeService(ExchangeRepository exchangeRepository, UserEntityService userEntityService, CurrencyService currencyService) {
        this.exchangeRepository = exchangeRepository;
        this.userEntityService = userEntityService;
        this.currencyService = currencyService;
    }

    public Exchange getExchangeById(Long id) {
        return exchangeRepository.findById(id).orElseThrow(()
                -> new ExchangeNotFoundException("Unable to found exchange with id: " + id));
    }

    public Exchange exchange(Long userId, BigDecimal amount, String currentFrom, String currentTo) throws IOException {
        UserEntity userById = userEntityService.getUserById(userId);
        Map<String, BigDecimal> currencyMap = currencyService.getCurrencyMap();
        BigDecimal currencyFromValue = currencyMap.get(currentFrom.toUpperCase());
        BigDecimal currencyToValue = currencyMap.get(currentTo.toUpperCase());
        BigDecimal result = extracted(amount, currencyFromValue, currencyToValue);
        Exchange exchange = new Exchange(userById, amount, currentFrom, currentTo, result);
        List<Exchange> exchanges = userById.getExchanges();
        exchanges.add(exchange);
        exchangeRepository.save(exchange);
        userEntityService.updateUser(userId, exchanges);
        return exchange;
    }

    private BigDecimal extracted(BigDecimal amount, BigDecimal currencyFromValue, BigDecimal currencyToValue) {
        BigDecimal amountFrom = currencyToValue.multiply(amount).multiply(BigDecimal.valueOf(100));
        return amountFrom.divide(currencyFromValue.multiply(BigDecimal.valueOf(100.00)), 2,
                        BigDecimal.ROUND_HALF_EVEN);
    }

}
