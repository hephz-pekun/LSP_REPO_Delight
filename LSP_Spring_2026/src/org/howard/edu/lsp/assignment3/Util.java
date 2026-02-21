package org.howard.edu.lsp.assignment3;

/**
 * Small, focused helpers for rounding/formatting money and splitting CSV lines.
 * Collapsed into one utility class to reduce separate files.
 */
public class Util {
    /**
     * Rounds to two decimals using Math.round—identical to Assignment 2 behavior:
     * multiply, round to nearest long, then divide back.
     */
    public static double round2(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    /**
     * Formats a double with exactly two decimals, reproducing Assignment 2's edge cases:
     * no locale; no scientific notation; pad/truncate to 2 fractional digits.
     */
    public static String moneyFormat(double value) {
        String s = "" + value;
        int dot = s.indexOf('.');
        if (dot == -1) return s + ".00";
        int decimals = s.length() - dot - 1;
        if (decimals == 0) return s + "00";
        if (decimals == 1) return s + "0";
        return s;
    }

    /**
     * Splits a CSV line on commas without quote handling (sufficient for the assignment data).
     * The implementation mirrors Assignment 2 to keep behavior identical.
     */
    public static String[] csvSplit(String line) {
        int count = 1;
        for (int i = 0; i < line.length(); i++) if (line.charAt(i) == ',') count++;

        String[] out = new String[count];
        StringBuilder current = new StringBuilder();
        int index = 0;

        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (c == ',') {
                out[index++] = current.toString(); // finalize current token
                current.setLength(0);              // reset builder
            } else {
                current.append(c);                 // accumulate characters
            }
        }
        out[index] = current.toString();           // last token after the final comma
        return out;
    }
}