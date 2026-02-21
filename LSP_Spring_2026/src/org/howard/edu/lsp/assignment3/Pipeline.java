package org.howard.edu.lsp.assignment3;

import java.util.Iterator;
import java.util.List;

/**
 * Coordinates the ETL flow: iterate source → apply polymorphic steps → write sink.
 * The class is intentionally small; control flow is explicit to aid understanding.
 *
 * @param <T> the record type processed by this pipeline (Product in our case)
 */
public class Pipeline<T> {
    private final Iterable<Product> source;
    private final CsvProductIO.Writer sink;
    private final List<Transformations.Transformation<T>> steps;

    /**
     * Binds a source of records, an output sink, and an ordered list of transformations.
     * @param source an Iterable of Products; its iterator may signal skips via exceptions
     * @param sink a writer that accepts transformed records
     * @param steps ordered transformations; demonstrate polymorphism
     */
    public Pipeline(Iterable<Product> source,
                    CsvProductIO.Writer sink,
                    List<Transformations.Transformation<T>> steps) {
        this.source = source;
        this.sink = sink;
        this.steps = steps;
    }

    /**
     * Executes the pipeline:
     * 1) Write header once.
     * 2) For each source record: count read.
     * 3) Apply each transformation; any step may throw SkipRecordException → count skipped.
     * 4) Write the final record; count transformed.
     * 5) Close the sink.
     * @return metrics snapshot at the end of the run
     */
    public Metrics run() {
        Metrics m = new Metrics();
        sink.writeHeader();

        Iterator<Product> it = source.iterator();
        while (it.hasNext()) {
            try {
                // Count this physical row as "read" BEFORE we attempt to parse/transform it.
                m.read++;

                // Iterator may throw SkipRecordException for blank/invalid rows.
                Product product = it.next();

                // Apply each step in sequence (polymorphism).
                @SuppressWarnings("unchecked")
                T current = (T) product;
                for (Transformations.Transformation<T> step : steps) {
                    current = step.apply(current);
                }

                // Successful pipeline for this record
                sink.write((Product) current);
                m.transformed++;

            } catch (SkipRecordException skipped) {
                // Any parse/validation/rule-driven skip ends up here.
                m.skipped++;
                // Continue to next input line
            }
        }

        sink.close();
        return m;
    }

    /** Minimal counters kept as a simple data carrier with getters. */
    public static class Metrics {
        private int read;
        private int transformed;
        private int skipped;

        /** @return how many input lines were consumed */
        public int getRead() { return read; }
        /** @return how many outputs were successfully written */
        public int getTransformed() { return transformed; }
        /** @return how many inputs were dropped (invalid/blank/bad columns/etc.) */
        public int getSkipped() { return skipped; }
    }

    /**
     * A lightweight unchecked signal used by the source iterator and transforms
     * to indicate a record should be dropped from further processing.
     * Using RuntimeException avoids cluttering method signatures with checked throws.
     */
    public static class SkipRecordException extends RuntimeException {
        /** Constructs with a short reason (useful during debugging). */
        public SkipRecordException(String message) { super(message); }
    }
}