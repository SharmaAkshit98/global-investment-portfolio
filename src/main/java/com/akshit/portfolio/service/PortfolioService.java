package com.akshit.portfolio.service;

import com.akshit.portfolio.domain.*;

import java.math.BigDecimal;
import java.util.*;

public class PortfolioService {

    private static final BigDecimal OPENING_BALANCE =
            new BigDecimal("10000");

    private BigDecimal currentBalance = OPENING_BALANCE;

    private final Map<String, PortfolioPosition> portfolio = new HashMap<>();
    private final List<TransactionSnapshot> history = new ArrayList<>();

    public void applyTransaction(
            Transaction transaction,
            BigDecimal exchangeRate,
            BigDecimal usdAmount
    ) {

        PortfolioPosition position =
                portfolio.computeIfAbsent(
                        transaction.ticker(),
                        t -> new PortfolioPosition()
                );

        if (transaction.type() == TransactionType.SELL
                && position.getTotalShares() < transaction.quantity()) {

            System.out.println(
                    "Invalid SELL skipped (short-sell not allowed): "
                            + transaction.ticker()
            );
            return;
        }

        if (transaction.type() == TransactionType.BUY) {
            position.buy(transaction.quantity(), usdAmount);
            currentBalance = currentBalance.subtract(usdAmount);
        } else {
            position.sell(transaction.quantity(), usdAmount);
            currentBalance = currentBalance.add(usdAmount);
        }

        history.add(
                new TransactionSnapshot(
                        transaction.date(),
                        transaction.ticker(),
                        transaction.type().name(),
                        transaction.quantity(),
                        transaction.localPrice(),
                        transaction.currency(),
                        exchangeRate,
                        usdAmount,
                        currentBalance
                )
        );
    }

    public PortfolioReport generateReport() {

        BigDecimal totalInvested =
                portfolio.values().stream()
                        .map(PortfolioPosition::getTotalInvestmentUsd)
                        .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new PortfolioReport(
                OPENING_BALANCE,
                currentBalance,
                totalInvested,
                portfolio,
                history
        );
    }
}
