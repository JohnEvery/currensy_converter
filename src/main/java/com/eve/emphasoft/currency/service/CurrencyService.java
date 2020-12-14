package com.eve.emphasoft.currency.service;

import com.eve.emphasoft.currency.dto.CurrencyDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Map;

@Service
public class CurrencyService {

    @Value("${internal.currency.url}")
    private String currencyUrl;

    public Map<String, BigDecimal> getCurrencyMap() throws IOException {
        URL jsonUrl = new URL(
                currencyUrl);
        ObjectMapper objectMapper = new ObjectMapper();
        CurrencyDTO fromJson = objectMapper.readValue(jsonUrl, CurrencyDTO.class);
        return fromJson.getRates();
    }

    public BigDecimal convertToUSD(BigDecimal amount, String currencyFrom) throws IOException {
        Map<String, BigDecimal> currencyMap = getCurrencyMap();
        BigDecimal current = currencyMap.get(currencyFrom.toUpperCase());
        return amount.divide(current, 2, BigDecimal.ROUND_HALF_EVEN);
    }
}
