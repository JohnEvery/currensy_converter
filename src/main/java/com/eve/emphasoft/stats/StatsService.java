package com.eve.emphasoft.stats;

import com.eve.emphasoft.exceptions.ExchangeNotFoundException;
import com.eve.emphasoft.exchange.controller.dto.ExchangeSumDTO;
import com.eve.emphasoft.exchange.entity.Exchange;
import com.eve.emphasoft.exchange.repository.ExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class StatsService {

    @Value("${spring.datasource.url}")
    private String URL_DATABASE;
    @Value("${spring.datasource.driver-class-name}")
    private String DRIVER_CLASS_NAME;
    @Value("${spring.datasource.username}")
    private String USERNAME_DATABASE;
    @Value("${spring.datasource.password}")
    private String PASSWORD_DATABASE;

    private final ExchangeRepository exchangeRepository;

    @Autowired
    public StatsService(ExchangeRepository exchangeRepository) {
        this.exchangeRepository = exchangeRepository;
    }

    public List<Exchange> getUsersExchangesMoreThanAmount(BigDecimal amount) {
        return exchangeRepository.findExchangesBiggerThanAmount(amount).orElseThrow(() ->
                new ExchangeNotFoundException("Not found"));
    }

    public List<ExchangeSumDTO> getSumList(BigDecimal amount, String currency) {
        try {
            String sumQuery = "SELECT user_id, SUM(amount), currency_from FROM exchange " +
                    "GROUP BY user_id, currency_from";
            ResultSet resultSet = getResultSet(sumQuery);
            List<ExchangeSumDTO> list = new ArrayList<>();
            while (resultSet.next()) {
                ExchangeSumDTO exchangeSumDTO = new ExchangeSumDTO();
                Long userId = resultSet.getLong("user_id");
                BigDecimal amountSum = resultSet.getBigDecimal("sum");
                String currencyFrom = resultSet.getString("currency_from");
                if (amountSum.compareTo(amount) >= 0 && currencyFrom.equalsIgnoreCase(currency)) {
                    exchangeSumDTO.setUserId(userId);
                    exchangeSumDTO.setAmount(amountSum);
                    exchangeSumDTO.setCurrencyFrom(currencyFrom);
                    list.add(exchangeSumDTO);
                }
            }
            return list;
        } catch (Exception exc) {
            System.out.println(exc.getMessage());
        }
        return null;
    }

    public List<String> getTopCurrency() {
        try {
            String queryTop = "SELECT currency_to, COUNT(id) as count FROM exchange " +
                    "GROUP BY currency_to " +
                    "ORDER BY count DESC";
            ResultSet resultSet = getResultSet(queryTop);
            List<String> topCurrency = new ArrayList<>();
            while (resultSet.next()) {
                Long count = resultSet.getLong("count");
                String currency = resultSet.getString("currency_to");
                String position = "There was " + count + " exchanges to " + currency.toUpperCase();
                topCurrency.add(position);
            }
            return topCurrency;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private ResultSet getResultSet(String query) throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER_CLASS_NAME);
        Connection con = DriverManager.getConnection(URL_DATABASE, USERNAME_DATABASE, PASSWORD_DATABASE);
        PreparedStatement statement = con.prepareStatement(query);
        return statement.executeQuery();
    }


}
