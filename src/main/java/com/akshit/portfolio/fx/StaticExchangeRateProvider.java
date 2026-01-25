package com.akshit.portfolio.fx;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

public class StaticExchangeRateProvider implements ExchangeRateProvider {

    private final Map<String, BigDecimal> exchangeRates;

    public StaticExchangeRateProvider(Map<String, BigDecimal> exchangeRates) {
        this.exchangeRates = exchangeRates;
    }

    @Override
    public BigDecimal getUsdRate(String currency, LocalDate date) {
        BigDecimal rate = exchangeRates.get(currency);
        if (rate == null) {
            throw new IllegalArgumentException(
                    "Exchange rate not found for currency: " + currency
            );
        }
        return rate;
    }
}
