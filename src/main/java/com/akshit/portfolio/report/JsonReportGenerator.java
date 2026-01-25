package com.akshit.portfolio.report;

import com.akshit.portfolio.domain.PortfolioReport;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JsonReportGenerator implements ReportGenerator {

    private final ObjectMapper objectMapper;

    public JsonReportGenerator() {
        this.objectMapper = new ObjectMapper()
                .enable(SerializationFeature.INDENT_OUTPUT);
    }

    @Override
    public void generate(PortfolioReport report) {
        try {
            String json = objectMapper.writeValueAsString(report);
            System.out.println("\n===== PORTFOLIO REPORT (JSON) =====");
            System.out.println(json);
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate JSON report", e);
        }
    }
}
