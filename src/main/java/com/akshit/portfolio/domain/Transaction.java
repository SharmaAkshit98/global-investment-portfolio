package com.akshit.portfolio.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

public record Transaction(
        LocalDate date,
        String ticker,
        TransactionType type,
        int quantity,
        BigDecimal localPrice,
        String currency
) {
}
