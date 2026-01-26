package com.akshit.portfolio.report;

import com.akshit.portfolio.domain.PortfolioReport;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.nio.file.Path;

public class JsonReportGenerator implements ReportGenerator {

    private static final String REPORT_FILE_NAME = "portfolio-report.json";

    private final ObjectMapper objectMapper;

    public JsonReportGenerator() {
        this.objectMapper = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .enable(SerializationFeature.INDENT_OUTPUT);
    }

    @Override
    public void generate(PortfolioReport report) {
        try {
            // Convert to JSON string
            String json = objectMapper.writeValueAsString(report);

            // Print to console
            System.out.println("\n===== PORTFOLIO REPORT (JSON) =====");
            System.out.println(json);

            // Write to file
            Path reportPath = Path.of(REPORT_FILE_NAME);
            objectMapper.writeValue(reportPath.toFile(), report);

            System.out.println("\nReport written to file: " + reportPath.toAbsolutePath());

        } catch (Exception e) {
            throw new RuntimeException("Failed to generate JSON report", e);
        }
    }
}
