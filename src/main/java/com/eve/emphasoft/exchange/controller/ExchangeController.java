package com.eve.emphasoft.exchange.controller;

import com.eve.emphasoft.exchange.controller.dto.ExchangeDTO;
import com.eve.emphasoft.exchange.controller.dto.ExchangeRequestDTO;
import com.eve.emphasoft.exchange.controller.dto.ExchangeResponseDTO;
import com.eve.emphasoft.exchange.service.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class ExchangeController {

    private final ExchangeService exchangeService;

    @Autowired
    public ExchangeController(ExchangeService exchangeService) {
        this.exchangeService = exchangeService;
    }

    @GetMapping("/exchange/{id}")
    public ExchangeDTO getExchangeById(@PathVariable("id") Long id) {
        return new ExchangeDTO(exchangeService.getExchangeById(id));
    }

    //id пользователя, сумма в исходной валюте, исходная валюта, целевая валюта
    @PostMapping("/exchange")
    public ExchangeResponseDTO exchange(@RequestBody ExchangeRequestDTO exchangeRequestDTO) throws IOException {
        return new ExchangeResponseDTO(exchangeService.exchange(exchangeRequestDTO.getUserId(), exchangeRequestDTO.getAmount(),
                exchangeRequestDTO.getCurrencyFrom(), exchangeRequestDTO.getCurrencyTo()));
    }
}
