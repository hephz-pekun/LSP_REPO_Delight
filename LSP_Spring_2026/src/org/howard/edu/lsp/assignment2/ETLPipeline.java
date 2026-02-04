/**
 * Name: Iyanuoluwa Hephzibah Olanipekun
 */

package org.howard.edu.lsp.assignment2;

public class ETLPipeline {

    public static void main(String[] args) {
        String inputPath = "data/products.csv";
        String outputPath = "data/transformed_products.csv";

        java.io.FileReader fr = null;
        java.io.FileWriter fw = null;
        java.io.BufferedReader br = null;
        java.io.BufferedWriter bw = null;

        int rowsRead = 0;
        int rowsTransformed = 0;
        int rowsSkipped = 0;

        try {
            // Ensure data folder exists
            java.io.File outDir = new java.io.File("data");
            if (!outDir.exists()) {
                outDir.mkdirs();
            }

            // Open files
            fr = new java.io.FileReader(inputPath);
            fw = new java.io.FileWriter(outputPath);
            br = new java.io.BufferedReader(fr);
            bw = new java.io.BufferedWriter(fw);

            // Write header
            bw.write("ProductID,Name,Price,Category,PriceRange");
            bw.newLine();

            // Read the input header
            String header = br.readLine();
            if (header == null) {
                printSummary(rowsRead, rowsTransformed, rowsSkipped, outputPath);
                return;
            }

            String line;

            while ((line = br.readLine()) != null) {
                rowsRead++;

                // skip blank lines
                if (line.trim().isEmpty()) {
                    rowsSkipped++;
                    continue;
                }

                // split
                String[] parts = splitCSV(line);
                if (parts.length != 4) {
                    rowsSkipped++;
                    continue;
                }

                String idStr = parts[0].trim();
                String name = parts[1].trim();
                String priceStr = parts[2].trim();
                String category = parts[3].trim();

                int productId;
                double price;

                try {
                    productId = Integer.parseInt(idStr);
                } catch (Exception e) {
                    rowsSkipped++;
                    continue;
                }

                try {
                    price = Double.parseDouble(priceStr);
                } catch (Exception e) {
                    rowsSkipped++;
                    continue;
                }

                // Transformations
                name = name.toUpperCase();
                String originalCategory = category;

                if (originalCategory.equals("Electronics")) {
                    price = price * 0.90;
                }

                price = roundTwoDecimals(price);

                if (originalCategory.equals("Electronics") && price > 500.00) {
                    category = "Premium Electronics";
                }

                String priceRange = getPriceRange(price);

                // Write output line
                bw.write(productId + "," + name + "," +
                        formatMoney(price) + "," + category + "," + priceRange);
                bw.newLine();

                rowsTransformed++;
            }

        } catch (Exception e) {
            System.out.println("Error processing file.");
        } finally {
            try { if (bw != null) bw.close(); } catch (Exception ignore) {}
            try { if (br != null) br.close(); } catch (Exception ignore) {}
            try { if (fw != null) fw.close(); } catch (Exception ignore) {}
            try { if (fr != null) fr.close(); } catch (Exception ignore) {}
        }

        printSummary(rowsRead, rowsTransformed, rowsSkipped, outputPath);
    }

    static double roundTwoDecimals(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    static String formatMoney(double value) {
        String s = "" + value;
        int dot = s.indexOf('.');
        if (dot == -1) return s + ".00";
        int decimals = s.length() - dot - 1;
        if (decimals == 0) return s + "00";
        if (decimals == 1) return s + "0";
        return s;
    }

    static String getPriceRange(double price) {
        if (price <= 10) return "Low";
        if (price <= 100) return "Medium";
        if (price <= 500) return "High";
        return "Premium";
    }

    static String[] splitCSV(String line) {
        int count = 1;
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == ',') count++;
        }

        String[] out = new String[count];
        String current = "";
        int index = 0;

        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (c == ',') {
                out[index] = current;
                index++;
                current = "";
            } else {
                current += c;
            }
        }
        out[index] = current;
        return out;
    }

    static void printSummary(int read, int transformed, int skipped, String outputPath) {
        System.out.println("Run Summary");
        System.out.println("-----------");
        System.out.println("Rows read: " + read);
        System.out.println("Rows transformed: " + transformed);
        System.out.println("Rows skipped: " + skipped);
        System.out.println("Output file: " + outputPath);
    }
}