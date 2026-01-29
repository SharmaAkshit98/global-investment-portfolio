package com.globalinvest.portfolio.service;

import com.globalinvest.portfolio.domain.PortfolioPosition;
import com.globalinvest.portfolio.domain.PortfolioReport;
import com.globalinvest.portfolio.domain.TransactionLogEntry;

import java.math.BigDecimal;
import java.util.*;

public class PortfolioService {

    private final Map<String, PortfolioPosition> positions = new HashMap<>();
    private final List<TransactionLogEntry> transactionLogs = new ArrayList<>();

    public boolean canSell(String ticker, int qty) {
        return positions.containsKey(ticker)
                && positions.get(ticker).getTotalShares() >= qty;
    }

    public void applyBuy(String ticker, int qty, BigDecimal usdAmount) {
        positions.computeIfAbsent(ticker, t -> new PortfolioPosition())
                .buy(qty, usdAmount);
    }

    public void applySell(String ticker, int qty, BigDecimal usdAmount) {
        positions.get(ticker).sell(qty, usdAmount);
    }

    public void log(TransactionLogEntry entry) {
        transactionLogs.add(entry);
    }

    public PortfolioReport generateReport() {
        return new PortfolioReport(
                java.time.LocalDateTime.now(),
                "USD",
                positions,
                transactionLogs
        );
    }
}
