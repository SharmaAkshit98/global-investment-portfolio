# global-investment-portfolio

A production-style Java application that processes multi-currency stock transactions, normalizes all investments into USD using exchange rates, enforces trading rules, and generates a detailed JSON portfolio report.


# Problem Statement

Users can buy and sell stocks across global markets using different local currencies (USD, INR, EUR, etc.).  
To provide a clear financial overview, all transactions must be consolidated into a **single base currency (USD)** using exchange rates applicable at the time of the transaction.

The system must:
- Track holdings per stock
- Convert all values to USD
- Prevent invalid trades (e.g., short selling)
- Generate a structured JSON report


# Architecture & Project Structure

src/main/java/com/globalinvest/portfolio
│
├── app
│ └── PortfolioRunner.java → Application entry & orchestration
│
├── domain
│ ├── Transaction.java → Core transaction model
│ ├── TransactionType.java → BUY / SELL
│ ├── TransactionLogEntry.java → Transaction history records
│ ├── PortfolioPosition.java → Holdings per stock
│ └── PortfolioReport.java → Final report model
│
├── service
│ ├── TransactionProcessor.java → Business rules & transaction execution
│ └── PortfolioService.java → Portfolio aggregation logic
│
├── fx
│ ├── ExchangeRateProvider.java → Exchange rate abstraction
│ └── StaticExchangeRateProvider.java → In-memory exchange rates
│
├── report
│ └── JsonReportGenerator.java → JSON report writer
│
├── util
│ └── TransactionIdGenerator.java → Secure transaction ID generator
│
└── GlobalInvestmentPortfolioApplication.java

How to Run
Prerequisites:
Java 17+
Maven

IntelliJ IDEA / Eclipse
Run via Terminal: mvn spring-boot:run

A timestamped JSON report will be generated under the reports/ directory.