package com.akshit.portfolio.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class PortfolioReport {

    private final LocalDateTime generatedAt;
    private final String baseCurrency;

    private final BigDecimal openingBalanceUsd;
    private final BigDecimal closingBalanceUsd;
    private final BigDecimal totalInvestedUsd;

    private final Map<String, PortfolioPosition> positions;
    private final List<TransactionSnapshot> transactionHistory;

    public PortfolioReport(
            BigDecimal openingBalanceUsd,
            BigDecimal closingBalanceUsd,
            BigDecimal totalInvestedUsd,
            Map<String, PortfolioPosition> positions,
            List<TransactionSnapshot> transactionHistory
    ) {
        this.generatedAt = LocalDateTime.now();
        this.baseCurrency = "USD";
        this.openingBalanceUsd = openingBalanceUsd;
        this.closingBalanceUsd = closingBalanceUsd;
        this.totalInvestedUsd = totalInvestedUsd;
        this.positions = positions;
        this.transactionHistory = transactionHistory;
    }

    public LocalDateTime getGeneratedAt() {
        return generatedAt;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public BigDecimal getOpeningBalanceUsd() {
        return openingBalanceUsd;
    }

    public BigDecimal getClosingBalanceUsd() {
        return closingBalanceUsd;
    }

    public BigDecimal getTotalInvestedUsd() {
        return totalInvestedUsd;
    }

    public Map<String, PortfolioPosition> getPositions() {
        return positions;
    }

    public List<TransactionSnapshot> getTransactionHistory() {
        return transactionHistory;
    }
}


//package com.akshit.portfolio.domain;
//
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//import java.util.Map;
//
//public class PortfolioReport {
//
//    private final LocalDateTime generatedAt;
//    private final String currency;
//    private final Map<String, PortfolioSummary> positions;
//
//    public PortfolioReport(Map<String, PortfolioSummary> positions) {
//        this.generatedAt = LocalDateTime.now();
//        this.currency = "USD";
//        this.positions = positions;
//    }
//
//    public LocalDateTime getGeneratedAt() {
//        return generatedAt;
//    }
//
//    public String getCurrency() {
//        return currency;
//    }
//
//    public Map<String, PortfolioSummary> getPositions() {
//        return positions;
//    }
//
//    public record PortfolioSummary(
//            int sharesHeld,
//            BigDecimal netInvestmentUsd
//    ) {
//    }
//}
