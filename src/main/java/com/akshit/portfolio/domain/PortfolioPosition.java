package com.akshit.portfolio.domain;

import java.math.BigDecimal;

public class PortfolioPosition {

    private int totalShares;
    private BigDecimal totalInvestmentUsd;

    public PortfolioPosition() {
        this.totalShares = 0;
        this.totalInvestmentUsd = BigDecimal.ZERO;
    }

    public int getTotalShares() {
        return totalShares;
    }

    public BigDecimal getTotalInvestmentUsd() {
        return totalInvestmentUsd;
    }

    public void buy(int quantity, BigDecimal usdAmount) {
        this.totalShares += quantity;
        this.totalInvestmentUsd = this.totalInvestmentUsd.add(usdAmount);
    }

    public void sell(int quantity, BigDecimal usdAmount) {
        this.totalShares -= quantity;
        this.totalInvestmentUsd = this.totalInvestmentUsd.subtract(usdAmount);
    }
}
