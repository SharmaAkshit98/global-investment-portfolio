package com.akshit.portfolio.data;

import java.math.BigDecimal;
import java.util.Map;

public class StaticDataProvider {

    public Map<String, BigDecimal> exchangeRates() {
        return Map.of(
                "USD", BigDecimal.ONE,
                "EUR", new BigDecimal("1.10"),
                "GBP", new BigDecimal("1.25")
        );
    }

    public Map<String, BigDecimal> stockPrices() {
        return Map.of(
                "AAPL", new BigDecimal("150"),
                "TSLA", new BigDecimal("700")
        );
    }
}
