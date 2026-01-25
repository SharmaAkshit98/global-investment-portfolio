package com.akshit.portfolio.service;

import com.akshit.portfolio.domain.PortfolioPosition;
import com.akshit.portfolio.domain.PortfolioReport;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class PortfolioService {

    private final Map<String, PortfolioPosition> portfolio = new HashMap<>();

    public void applyBuy(String ticker, int quantity, BigDecimal usdAmount) {
        PortfolioPosition position =
                portfolio.computeIfAbsent(ticker, t -> new PortfolioPosition());
        position.buy(quantity, usdAmount);
    }

    public boolean canSell(String ticker, int quantity) {
        PortfolioPosition position = portfolio.get(ticker);
        return position != null && position.getTotalShares() >= quantity;
    }

    public void applySell(String ticker, int quantity, BigDecimal usdAmount) {
        PortfolioPosition position = portfolio.get(ticker);
        position.sell(quantity, usdAmount);
    }

    public PortfolioReport generateReport() {
        Map<String, PortfolioReport.PortfolioSummary> summary = new HashMap<>();

        for (var entry : portfolio.entrySet()) {
            if (entry.getValue().getTotalShares() > 0) {
                summary.put(
                        entry.getKey(),
                        new PortfolioReport.PortfolioSummary(
                                entry.getValue().getTotalShares(),
                                entry.getValue().getTotalInvestmentUsd()
                        )
                );
            }
        }

        return new PortfolioReport(summary);
    }
}
