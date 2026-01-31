package com.globalinvest.portfolio.report;

import com.globalinvest.portfolio.domain.PortfolioPosition;
import com.globalinvest.portfolio.domain.PortfolioReport;
import com.globalinvest.portfolio.domain.TransactionLogEntry;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class ExcelReportGenerator {

    private static final String REPORT_DIR = "reports";

    public void generate(PortfolioReport report) {
        try {
            Files.createDirectories(Path.of(REPORT_DIR));

            String timestamp = report.reportGeneratedAt()
                    .format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"));

            Path filePath = Path.of(
                    REPORT_DIR,
                    "portfolio-report-" + timestamp + ".xlsx"
            );

            Workbook workbook = new XSSFWorkbook();

            createPortfolioSummarySheet(workbook, report.portfolioSummary());
            createTransactionHistorySheet(workbook, report.transactionLog());
            createStatisticsSheet(workbook, report);

            try (FileOutputStream out = new FileOutputStream(filePath.toFile())) {
                workbook.write(out);
            }

            workbook.close();

            System.out.println("Excel report generated at: " + filePath.toAbsolutePath());

        } catch (Exception e) {
            throw new RuntimeException("Failed to generate Excel report", e);
        }
    }

    // ---------- Portfolio Summary ----------
    private void createPortfolioSummarySheet(
            Workbook workbook,
            Map<String, PortfolioPosition> summary
    ) {
        Sheet sheet = workbook.createSheet("Portfolio Summary");

        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Ticker");
        header.createCell(1).setCellValue("Total Shares");
        header.createCell(2).setCellValue("Net Investment (USD)");

        int rowIdx = 1;
        for (var entry : summary.entrySet()) {
            Row row = sheet.createRow(rowIdx++);
            row.createCell(0).setCellValue(entry.getKey());
            row.createCell(1).setCellValue(entry.getValue().getTotalShares());
            row.createCell(2).setCellValue(
                    entry.getValue().getTotalInvestmentUsd().doubleValue()
            );
        }

        autoSize(sheet, 3);
    }

    // ---------- Transaction History ----------
    private void createTransactionHistorySheet(
            Workbook workbook,
            java.util.List<TransactionLogEntry> logs
    ) {
        Sheet sheet = workbook.createSheet("Transaction History");

        String[] headers = {
                "Txn ID", "Date", "Type", "Ticker",
                "Quantity", "Price", "Currency",
                "Exchange Rate", "Amount USD",
                "Status", "Message"
        };

        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            headerRow.createCell(i).setCellValue(headers[i]);
        }

        int rowIdx = 1;
        for (TransactionLogEntry log : logs) {
            Row row = sheet.createRow(rowIdx++);
            row.createCell(0).setCellValue(log.transactionId());
            row.createCell(1).setCellValue(log.date().toString());
            row.createCell(2).setCellValue(log.type().name());
            row.createCell(3).setCellValue(log.ticker());
            row.createCell(4).setCellValue(log.quantity());
            row.createCell(5).setCellValue(log.priceLocal().doubleValue());
            row.createCell(6).setCellValue(log.currency());
            row.createCell(7).setCellValue(log.exchangeRate().doubleValue());
            row.createCell(8).setCellValue(log.amountUsd().doubleValue());
            row.createCell(9).setCellValue(log.status());
            row.createCell(10).setCellValue(log.message());
        }

        autoSize(sheet, headers.length);
    }

    private void createStatisticsSheet(
            Workbook workbook,
            PortfolioReport report
    ) {
        Sheet sheet = workbook.createSheet("Statistics");

        int totalShares = report.portfolioSummary()
                .values()
                .stream()
                .mapToInt(PortfolioPosition::getTotalShares)
                .sum();

        double totalValue = report.portfolioSummary()
                .values()
                .stream()
                .map(p -> p.getTotalInvestmentUsd().doubleValue())
                .reduce(0.0, Double::sum);

        Row r1 = sheet.createRow(0);
        r1.createCell(0).setCellValue("Total Shares Held");
        r1.createCell(1).setCellValue(totalShares);

        Row r2 = sheet.createRow(1);
        r2.createCell(0).setCellValue("Total Portfolio Value (USD)");
        r2.createCell(1).setCellValue(totalValue);

        Row r3 = sheet.createRow(2);
        r3.createCell(0).setCellValue("Generated At");
        r3.createCell(1).setCellValue(report.reportGeneratedAt().toString());

        autoSize(sheet, 2);
    }

    private void autoSize(Sheet sheet, int columns) {
        for (int i = 0; i < columns; i++) {
            sheet.autoSizeColumn(i);
        }
    }
}
