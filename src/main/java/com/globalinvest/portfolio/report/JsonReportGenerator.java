package com.globalinvest.portfolio.report;

import com.globalinvest.portfolio.domain.PortfolioReport;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class JsonReportGenerator {

    private static final String REPORT_DIR = "reports";

    private final ObjectMapper mapper;

    public JsonReportGenerator() {
        this.mapper = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .enable(SerializationFeature.INDENT_OUTPUT);
    }

    public void generate(PortfolioReport report) {
        try {
            Path reportsDir = Path.of(REPORT_DIR);
            if (Files.notExists(reportsDir)) {
                Files.createDirectories(reportsDir);
            }
            String timestamp = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"));

            Path reportFile = reportsDir.resolve(
                    "portfolio-report-" + timestamp + ".json"
            );

            mapper.writeValue(reportFile.toFile(), report);

            System.out.println("Portfolio report generated successfully");
            System.out.println("File location: " + reportFile.toAbsolutePath());

        } catch (Exception e) {
            throw new RuntimeException("Failed to generate JSON report", e);
        }
    }
}
