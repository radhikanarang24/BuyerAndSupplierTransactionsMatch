package com.craft.utils;

import java.math.BigDecimal;
import java.util.Date;

import static com.craft.constants.Constants.STRING_MULTIPLICATION_FACTOR;
import static java.lang.Math.abs;

public class MatchUtils {
    public static Integer BigDecimalMatch(BigDecimal amount1, BigDecimal amount2) {
        Integer partialMatch = amount1.subtract(amount2).intValue();
        return abs(partialMatch);
    }

    public static Integer IntegerMatch(Integer i1, Integer i2) {
        return abs(i1 - i2);
    }

    public static Long DateMatch(Date d1, Date d2) {
        if (d1.compareTo(d2) == 0) return 0L;
        else {
            Long diffInDates = d1.getTime() - d2.getTime();

            return abs((diffInDates / (1000 * 60 * 60 * 24)) % 365);
        }
    }

    public static Integer StringMatch(String s1, String s2) {
        String longer = s1, shorter = s2;
        if (s1.length() < s2.length()) {
            longer = s2;
            shorter = s1;
        }
        int longerLength = longer.length();
        if (longerLength == 0) {
            return 0;
        }
        return STRING_MULTIPLICATION_FACTOR - (int) (((longerLength - editDistance(longer, shorter)) / (double) longerLength) * STRING_MULTIPLICATION_FACTOR);
    }

    private static int editDistance(String s1, String s2) {
        int m = s1.length(), n = s2.length();

        int[][] costs = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++)
            costs[i][0] = i;

        for (int j = 1; j <= n; j++)
            costs[0][j] = j;

        int cost;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                cost = (s1.charAt(i - 1) == s2.charAt(j - 1)) ? 0 : 1;
                costs[i][j] = Integer.min(Integer.min(costs[i - 1][j] + 1, costs[i][j - 1] + 1), costs[i - 1][j - 1] + cost);
            }
        }
        return costs[m][n];
    }

}
