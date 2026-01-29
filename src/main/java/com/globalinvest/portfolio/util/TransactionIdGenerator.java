package com.globalinvest.portfolio.util;

import java.security.SecureRandom;

public class TransactionIdGenerator {

    private static final String ALPHA_NUM = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final SecureRandom RANDOM = new SecureRandom();

    public static String generate() {
        StringBuilder sb = new StringBuilder("TXN");
        for (int i = 0; i < 10; i++) {
            sb.append(ALPHA_NUM.charAt(RANDOM.nextInt(ALPHA_NUM.length())));
        }
        return sb.toString();
    }
}
