package com.akshit.portfolio.app;

import com.akshit.portfolio.data.StaticDataProvider;
import com.akshit.portfolio.data.TransactionDataLoader;
import com.akshit.portfolio.domain.PortfolioReport;
import com.akshit.portfolio.fx.StaticExchangeRateProvider;
import com.akshit.portfolio.pricing.StaticStockPriceProvider;
import com.akshit.portfolio.report.JsonReportGenerator;
import com.akshit.portfolio.service.PortfolioService;
import com.akshit.portfolio.service.TransactionProcessor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class PortfolioRunner implements CommandLineRunner {

    @Override
    public void run(String... args) {

        // Load static data
        StaticDataProvider staticDataProvider = new StaticDataProvider();
        TransactionDataLoader transactionDataLoader = new TransactionDataLoader();

        // Providers
        var fxProvider =
                new StaticExchangeRateProvider(staticDataProvider.exchangeRates());
        var priceProvider =
                new StaticStockPriceProvider(staticDataProvider.stockPrices());

        // Core services
        PortfolioService portfolioService = new PortfolioService();
        TransactionProcessor processor =
                new TransactionProcessor(fxProvider, priceProvider, portfolioService);

        // Process transactions
        transactionDataLoader.loadTransactions()
                .forEach(processor::process);

        // Generate report
        PortfolioReport report = portfolioService.generateReport();
        new JsonReportGenerator().generate(report);
    }
}
