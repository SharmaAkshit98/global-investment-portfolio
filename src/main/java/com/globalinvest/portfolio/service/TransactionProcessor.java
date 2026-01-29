package com.globalinvest.portfolio.service;

import com.globalinvest.portfolio.domain.Transaction;
import com.globalinvest.portfolio.domain.TransactionLogEntry;
import com.globalinvest.portfolio.domain.TransactionType;
import com.globalinvest.portfolio.fx.ExchangeRateProvider;
import com.globalinvest.portfolio.util.TransactionIdGenerator;

import java.math.BigDecimal;

public class TransactionProcessor {

    private final PortfolioService portfolioService;
    private final ExchangeRateProvider exchangeRateProvider;

    public TransactionProcessor(
            PortfolioService portfolioService,
            ExchangeRateProvider exchangeRateProvider
    ) {
        this.portfolioService = portfolioService;
        this.exchangeRateProvider = exchangeRateProvider;
    }

    public void process(Transaction tx) {

        String txnId = TransactionIdGenerator.generate();
        BigDecimal exchangeRate = tx.currency().equals("USD")
                ? BigDecimal.ONE
                : exchangeRateProvider.getRate(tx.currency(), tx.date());

        BigDecimal usdAmount = tx.priceLocal()
                .multiply(BigDecimal.valueOf(tx.quantity()))
                .multiply(exchangeRate);

        if (tx.type() == TransactionType.BUY) {

            portfolioService.applyBuy(tx.ticker(), tx.quantity(), usdAmount);
            portfolioService.log(new TransactionLogEntry(
                    txnId, tx.date(), tx.ticker(), tx.type(),
                    tx.quantity(), tx.priceLocal(), tx.currency(),
                    exchangeRate, usdAmount,
                    "EXECUTED",
                    "Order executed successfully"
            ));

        } else {

            if (!portfolioService.canSell(tx.ticker(), tx.quantity())) {
                portfolioService.log(new TransactionLogEntry(
                        txnId, tx.date(), tx.ticker(), tx.type(),
                        tx.quantity(), tx.priceLocal(), tx.currency(),
                        exchangeRate, usdAmount,
                        "REJECTED",
                        "Transaction rejected: short selling is not allowed"
                ));
                return;
            }

            portfolioService.applySell(tx.ticker(), tx.quantity(), usdAmount);
            portfolioService.log(new TransactionLogEntry(
                    txnId, tx.date(), tx.ticker(), tx.type(),
                    tx.quantity(), tx.priceLocal(), tx.currency(),
                    exchangeRate, usdAmount,
                    "EXECUTED",
                    "Order executed successfully"
            ));
        }
    }
}
