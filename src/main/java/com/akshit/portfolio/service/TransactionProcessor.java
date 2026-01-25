package com.akshit.portfolio.service;

import com.akshit.portfolio.domain.Transaction;
import com.akshit.portfolio.domain.TransactionType;
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

        BigDecimal price = stockPriceProvider
                .getPrice(transaction.ticker(), transaction.date());

        BigDecimal usdAmount = price
                .multiply(BigDecimal.valueOf(transaction.quantity()))
                .multiply(exchangeRateProvider.getUsdRate(
                        transaction.currency(),
                        transaction.date()
                ));

        if (transaction.type() == TransactionType.BUY) {
            portfolioService.applyBuy(
                    transaction.ticker(),
                    transaction.quantity(),
                    usdAmount
            );
        } else {
            if (portfolioService.canSell(
                    transaction.ticker(),
                    transaction.quantity()
            )) {
                portfolioService.applySell(
                        transaction.ticker(),
                        transaction.quantity(),
                        usdAmount
                );
            } else {
                System.err.println(
                        "Invalid SELL skipped (short-sell not allowed): "
                                + transaction.ticker()
                );
            }
        }
    }
}
