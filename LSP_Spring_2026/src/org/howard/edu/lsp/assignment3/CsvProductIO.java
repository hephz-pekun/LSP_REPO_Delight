package org.howard.edu.lsp.assignment3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Path;

/**
 * CSV I/O utilities grouped in one public class to reduce file count.
 * Contains a Reader (Iterable<Product>) and a Writer for output.
 */
public class CsvProductIO {

    /**
     * Lazily reads products from a CSV file whose header is:
     * ProductID,Name,Price,Category
     * <p>Errors are isolated per line; invalid rows signal a skip using
     * {@link Pipeline.SkipRecordException} so the pipeline can count them.</p>
     */
    public static class Reader implements Iterable<Product> {
        private final Path inputPath;

        /** Binds to the source CSV path (no file I/O happens yet). */
        public Reader(Path inputPath) { this.inputPath = inputPath; }

        /**
         * Returns an iterator that:
         * <ul>
         *   <li>Opens the file and consumes the header on first use.</li>
         *   <li>Reads one line at a time to keep memory low.</li>
         *   <li>On each call to next(): validates, parses, and builds a Product.</li>
         *   <li>Throws SkipRecordException for blank/bad rows to let the pipeline continue cleanly.</li>
         * </ul>
         */
        @Override
        public java.util.Iterator<Product> iterator() {
            try {
                BufferedReader br = new BufferedReader(new FileReader(inputPath.toFile()));
                br.readLine(); // discard header; if null, iterator will appear empty

                return new java.util.Iterator<Product>() {
                    String nextLine = null;
                    boolean closed = false;

                    /** Pulls the next physical line from disk only when needed. */
                    private void ensureNext() {
                        if (nextLine != null || closed) return;
                        try {
                            nextLine = br.readLine();
                            if (nextLine == null) { br.close(); closed = true; }
                        } catch (Exception e) {
                            // Any I/O failure ends the iterator gracefully.
                            try { br.close(); } catch (Exception ignore) {}
                            closed = true;
                            nextLine = null;
                        }
                    }

                    @Override public boolean hasNext() { ensureNext(); return nextLine != null; }

                    @Override
                    public Product next() {
                        ensureNext();
                        if (nextLine == null) throw new java.util.NoSuchElementException();
                        String line = nextLine; nextLine = null;

                        // 1) Skip completely blank lines
                        if (line.trim().isEmpty()) {
                            throw new Pipeline.SkipRecordException("Blank line");
                        }

                        // 2) Split by commas (no quoted fields needed for this assignment)
                        String[] parts = Util.csvSplit(line);
                        if (parts.length != 4) {
                            throw new Pipeline.SkipRecordException("Wrong column count");
                        }

                        // 3) Parse and validate columns; any failure yields a skip
                        int id; double price;
                        try { id = Integer.parseInt(parts[0].trim()); }
                        catch (Exception e) { throw new Pipeline.SkipRecordException("Bad ProductID"); }

                        try { price = Double.parseDouble(parts[2].trim()); }
                        catch (Exception e) { throw new Pipeline.SkipRecordException("Bad Price"); }

                        String name = parts[1].trim();
                        String category = parts[3].trim();

                        // 4) Build domain object; keep original category for rule checks later
                        return new Product(id, name, price, category, category);
                    }
                };
            } catch (Exception openFailed) {
                System.out.println("Error processing file due to the missing input file");
                throw new RuntimeException(openFailed);
            }
        }
    }

    /**
     * Writes a header and subsequent product rows to a target CSV file.
     * Ensures the parent directory exists to avoid FileNotFoundException.
     */
    public static class Writer {
        private BufferedWriter bw;

        /** Opens the output file and prepares a buffered writer. */
        public Writer(Path outputPath) {
            try {
                File parent = outputPath.toFile().getParentFile();
                if (parent != null && !parent.exists()) parent.mkdirs();
                bw = new BufferedWriter(new FileWriter(outputPath.toFile()));
            } catch (Exception e) {
                // If opening fails, bw remains null; subsequent writes are no-ops by design.
                bw = null;
            }
        }

        /** Emits the exact header required by the assignment. */
        public void writeHeader() {
            if (bw == null) return;
            try {
                bw.write("ProductID,Name,Price,Category,PriceRange");
                bw.newLine();
            } catch (Exception ignore) {}
        }

        /**
         * Formats and writes a single product row.
         * Uses Util.moneyFormat to match Assignment 2's exact formatting behavior.
         */
        public void write(Product p) {
            if (bw == null) return;
            try {
                String line = p.getId() + "," +
                              p.getName() + "," +
                              Util.moneyFormat(p.getPrice()) + "," +
                              p.getCategory() + "," +
                              (p.getPriceRange() == null ? "" : p.getPriceRange());
                bw.write(line);
                bw.newLine();
            } catch (Exception ignore) {}
        }

        /** Closes the writer quietly; safe to call multiple times. */
        public void close() {
            try { if (bw != null) bw.close(); } catch (Exception ignore) {}
        }
    }
}