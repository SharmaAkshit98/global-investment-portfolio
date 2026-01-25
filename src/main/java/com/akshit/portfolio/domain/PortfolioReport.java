package com.akshit.portfolio.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

public class PortfolioReport {

    private final LocalDateTime generatedAt;
    private final String currency;
    private final Map<String, PortfolioSummary> positions;

    public PortfolioReport(Map<String, PortfolioSummary> positions) {
        this.generatedAt = LocalDateTime.now();
        this.currency = "USD";
        this.positions = positions;
    }

    public LocalDateTime getGeneratedAt() {
        return generatedAt;
    }

    public String getCurrency() {
        return currency;
    }

    public Map<String, PortfolioSummary> getPositions() {
        return positions;
    }

    public record PortfolioSummary(
            int sharesHeld,
            BigDecimal netInvestmentUsd
    ) {
    }
}
