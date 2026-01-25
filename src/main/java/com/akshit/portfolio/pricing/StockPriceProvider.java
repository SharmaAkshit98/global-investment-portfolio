package com.akshit.portfolio.pricing;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface StockPriceProvider {

    BigDecimal getPrice(String ticker, LocalDate date);

}
