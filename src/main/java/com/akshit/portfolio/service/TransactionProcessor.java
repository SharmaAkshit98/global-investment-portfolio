package com.akshit.portfolio.service;

import com.akshit.portfolio.domain.Transaction;
import com.akshit.portfolio.fx.ExchangeRateProvider;
import com.akshit.portfolio.pricing.StockPriceProvider;

import java.math.BigDecimal;

public class TransactionProcessor {

    private final ExchangeRateProvider exchangeRateProvider;
    private final StockPriceProvider stockPriceProvider;
    private final PortfolioService portfolioService;

    public TransactionProcessor(
            ExchangeRateProvider exchangeRateProvider,
            StockPriceProvider stockPriceProvider,
            PortfolioService portfolioService
    ) {
        this.exchangeRateProvider = exchangeRateProvider;
        this.stockPriceProvider = stockPriceProvider;
        this.portfolioService = portfolioService;
    }

    public void process(Transaction transaction) {

        BigDecimal stockPrice = stockPriceProvider
                .getPrice(transaction.ticker(), transaction.date());

        BigDecimal exchangeRate = exchangeRateProvider
                .getUsdRate(transaction.currency(), transaction.date());

        BigDecimal usdAmount = stockPrice
                .multiply(BigDecimal.valueOf(transaction.quantity()))
                .multiply(exchangeRate);

        portfolioService.applyTransaction(
                transaction,
                exchangeRate,
                usdAmount
        );
    }
}
