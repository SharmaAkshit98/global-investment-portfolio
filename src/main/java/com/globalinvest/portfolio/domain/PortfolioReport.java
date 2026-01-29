package com.globalinvest.portfolio.domain;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public record PortfolioReport(
        LocalDateTime reportGeneratedAt,
        String baseCurrency,
        Map<String, PortfolioPosition> portfolioSummary,
        List<TransactionLogEntry> transactionLog
) {}
