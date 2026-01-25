package com.akshit.portfolio.fx;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface ExchangeRateProvider {

    BigDecimal getUsdRate(String currency, LocalDate date);

}
