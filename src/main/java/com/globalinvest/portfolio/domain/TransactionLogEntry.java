package com.globalinvest.portfolio.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TransactionLogEntry(
        String transactionId,
        LocalDate date,
        String ticker,
        TransactionType type,
        int quantity,
        BigDecimal priceLocal,
        String currency,
        BigDecimal exchangeRate,
        BigDecimal amountUsd,
        String status,
        String message
) {}
