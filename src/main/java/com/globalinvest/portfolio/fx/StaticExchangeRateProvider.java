package com.globalinvest.portfolio.fx;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

public class StaticExchangeRateProvider implements ExchangeRateProvider {

    private final Map<String, BigDecimal> rates;

    public StaticExchangeRateProvider(Map<String, BigDecimal> rates) {
        this.rates = rates;
    }

    @Override
    public BigDecimal getRate(String currency, LocalDate date) {
        return rates.getOrDefault(currency, BigDecimal.ONE);
    }
}
