package com.globalinvest.portfolio.domain;

import java.math.BigDecimal;

public class PortfolioPosition {

    private int totalShares;
    private BigDecimal totalInvestmentUsd = BigDecimal.ZERO;

    public void buy(int qty, BigDecimal amountUsd) {
        totalShares += qty;
        totalInvestmentUsd = totalInvestmentUsd.add(amountUsd);
    }

    public void sell(int qty, BigDecimal amountUsd) {
        totalShares -= qty;
        totalInvestmentUsd = totalInvestmentUsd.subtract(amountUsd);
    }

    public int getTotalShares() {
        return totalShares;
    }

    public BigDecimal getTotalInvestmentUsd() {
        return totalInvestmentUsd;
    }
}
