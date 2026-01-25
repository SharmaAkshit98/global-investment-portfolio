package com.akshit.portfolio.data;

import com.akshit.portfolio.domain.Transaction;
import com.akshit.portfolio.domain.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class TransactionDataLoader {

    public List<Transaction> loadTransactions() {
        return List.of(
                new Transaction(
                        LocalDate.of(2024, 1, 10),
                        "AAPL",
                        TransactionType.BUY,
                        10,
                        new BigDecimal("150"),
                        "USD"
                ),
                new Transaction(
                        LocalDate.of(2024, 2, 5),
                        "AAPL",
                        TransactionType.SELL,
                        5,
                        new BigDecimal("155"),
                        "USD"
                ),
                new Transaction(
                        LocalDate.of(2024, 3, 12),
                        "TSLA",
                        TransactionType.BUY,
                        8,
                        new BigDecimal("700"),
                        "EUR"
                )
        );
    }
}
