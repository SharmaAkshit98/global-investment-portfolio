package com.globalinvest.portfolio.app;

import com.globalinvest.portfolio.domain.Transaction;
import com.globalinvest.portfolio.domain.TransactionType;
import com.globalinvest.portfolio.fx.StaticExchangeRateProvider;
import com.globalinvest.portfolio.report.JsonReportGenerator;
import com.globalinvest.portfolio.service.PortfolioService;
import com.globalinvest.portfolio.service.TransactionProcessor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

@Component
public class PortfolioRunner implements CommandLineRunner {

    @Override
    public void run(String... args) {

        var fx = new StaticExchangeRateProvider(
                Map.of("INR", new BigDecimal("0.012"))
        );

        var portfolioService = new PortfolioService();
        var processor = new TransactionProcessor(portfolioService, fx);

        processor.process(new Transaction(
                LocalDate.now(), "APPLE", TransactionType.BUY,
                10, new BigDecimal("175"), "USD"
        ));

        processor.process(new Transaction(
                LocalDate.now(), "TATAPOWER", TransactionType.BUY,
                50, new BigDecimal("320"), "INR"
        ));

        processor.process(new Transaction(
                LocalDate.now(), "APPLE", TransactionType.SELL,
                20, new BigDecimal("250"), "USD"
        ));
        processor.process(new Transaction(
                LocalDate.now(), "TESLA", TransactionType.SELL,
                5, new BigDecimal("200"), "USD"
        ));


        var report = portfolioService.generateReport();
        new JsonReportGenerator().generate(report);
    }
}
