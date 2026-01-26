package com.akshit.portfolio.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransactionSnapshot {

    private final LocalDate date;
    private final String ticker;
    private final String type;
    private final int quantity;
    private final BigDecimal localPrice;
    private final String currency;
    private final BigDecimal exchangeRate;
    private final BigDecimal amountUsd;
    private final BigDecimal balanceAfterUsd;

    public TransactionSnapshot(
            LocalDate date,
            String ticker,
            String type,
            int quantity,
            BigDecimal localPrice,
            String currency,
            BigDecimal exchangeRate,
            BigDecimal amountUsd,
            BigDecimal balanceAfterUsd
    ) {
        this.date = date;
        this.ticker = ticker;
        this.type = type;
        this.quantity = quantity;
        this.localPrice = localPrice;
        this.currency = currency;
        this.exchangeRate = exchangeRate;
        this.amountUsd = amountUsd;
        this.balanceAfterUsd = balanceAfterUsd;
    }

    public LocalDate getDate() { return date; }
    public String getTicker() { return ticker; }
    public String getType() { return type; }
    public int getQuantity() { return quantity; }
    public BigDecimal getLocalPrice() { return localPrice; }
    public String getCurrency() { return currency; }
    public BigDecimal getExchangeRate() { return exchangeRate; }
    public BigDecimal getAmountUsd() { return amountUsd; }
    public BigDecimal getBalanceAfterUsd() { return balanceAfterUsd; }
}
