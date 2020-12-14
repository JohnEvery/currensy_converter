package com.eve.emphasoft.stats;

import com.eve.emphasoft.exchange.controller.dto.ExchangeSortDTO;
import com.eve.emphasoft.exchange.controller.dto.ExchangeSumDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class StatsController {

    private final StatsService statsService;

    @Autowired
    public StatsController(StatsService statsService) {
        this.statsService = statsService;
    }

    @GetMapping("/stats/bigger")
    public List<ExchangeSortDTO> getUsersExchangesMoreThanAmount(@RequestParam("amount") BigDecimal amount) {
        return statsService.getUsersExchangesMoreThanAmount(amount).stream().map(ExchangeSortDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/stats/sum")
    public List<ExchangeSumDTO> getSum(@RequestParam("amount") BigDecimal amount,
                                       @RequestParam(value = "currency", defaultValue = "USD") String currency) {
        return statsService.getSumList(amount, currency);
    }

    @GetMapping("/stats/top")
    public List<String> getTopCurrency() {
        return statsService.getTopCurrency();
    }
}
