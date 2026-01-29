package com.globalinvest.portfolio.fx;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface ExchangeRateProvider {
    BigDecimal getRate(String currency, LocalDate date);
}
