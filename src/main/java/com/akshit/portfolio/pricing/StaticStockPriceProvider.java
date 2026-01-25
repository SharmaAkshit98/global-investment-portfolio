package com.akshit.portfolio.pricing;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

public class StaticStockPriceProvider implements StockPriceProvider {

    private final Map<String, BigDecimal> prices;

    public StaticStockPriceProvider(Map<String, BigDecimal> prices) {
        this.prices = prices;
    }

    @Override
    public BigDecimal getPrice(String ticker, LocalDate date) {
        BigDecimal price = prices.get(ticker);
        if (price == null) {
            throw new IllegalArgumentException(
                    "Price not found for ticker: " + ticker
            );
        }
        return price;
    }
}
