package com.eve.emphasoft.exchange.repository;

import com.eve.emphasoft.exchange.entity.Exchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ExchangeRepository extends JpaRepository<Exchange, Long> {

    @Query(nativeQuery = true, value = "select * from exchange where amount >=:amountValue")
    Optional<List<Exchange>> findExchangesBiggerThanAmount(@Param("amountValue") BigDecimal amount);

}
