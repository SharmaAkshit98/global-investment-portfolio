# global-investment-portfolio

This project demonstrates clean architecture, financial accuracy using `BigDecimal`, extensible design, and real-world reporting suitable for enterprise systems.


## Key Features

- Multi-currency stock transaction processing (USD, INR, etc.)
- Automatic currency normalization into **USD**
- Buy / Sell transaction handling
- **Short-sell prevention** (invalid sell transactions are rejected and logged)
- Secure, random transaction ID generation
- **Excel report generation (`.xlsx`)**
- Timestamped reports with automatic folder creation

# Project Structure
src/main/java/com/globalinvest/portfolio/
│
├── app/
│ └── PortfolioRunner.java
│
├── domain/
│ ├── PortfolioPosition.java
│ ├── PortfolioReport.java
│ ├── Transaction.java
│ ├── TransactionLogEntry.java
│ └── TransactionType.java
│
├── fx/
│ ├── ExchangeRateProvider.java
│ └── StaticExchangeRateProvider.java
│
├── report/
│ └── ExcelReportGenerator.java
│
├── service/
│ ├── PortfolioService.java
│ └── TransactionProcessor.java
│
├── util/
│ └── TransactionIdGenerator.java
│
└── GlobalInvestmentPortfolioApplication.java

## Excel Report Output

When the application runs, it generates an **Excel file** under:
reports/

Each run creates a **new timestamped Excel file**, ensuring reports are never overwritten.

## How to Run
Prerequisites:
Java 17+
Maven

IntelliJ IDEA / Eclipse
Run via Terminal: mvn spring-boot:run
