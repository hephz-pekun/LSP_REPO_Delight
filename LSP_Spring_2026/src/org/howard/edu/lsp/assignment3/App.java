package org.howard.edu.lsp.assignment3;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 * Entry point: wires input/output and transformation steps, then runs the pipeline.
 * The goal is clarity: the main method describes the "what", not the "how".
 */
public class App {
    /** Starts the ETL: read → transform → write, then print summary. */
    public static void main(String[] args) {
        String inputPath = "data/products.csv";
        String outputPath = "data/transformed_products.csv";

        // Source reads Product objects lazily from CSV.
        CsvProductIO.Reader source = new CsvProductIO.Reader(Paths.get(inputPath));

        // Sink writes header once and then each transformed Product line by line.
        CsvProductIO.Writer sink = new CsvProductIO.Writer(Paths.get(outputPath));

        // A polymorphic pipeline: a list of Transformation<Product> applied in order.
        List<Transformations.Transformation<Product>> steps = Arrays.asList(
            new Transformations.UppercaseName(),
            new Transformations.ElectronicsDiscountRule(0.10), // 10% off Electronics
            new Transformations.CategoryUpgradeRule(),         // Premium Electronics if price > 500
            new Transformations.PriceRangeDeriver()            // Low/Medium/High/Premium
        );

        Pipeline<Product> pipeline = new Pipeline<>(source, sink, steps);
        Pipeline.Metrics metrics = pipeline.run();

        // End-of-run report (kept simple and readable).
        System.out.println("Run Summary");
        System.out.println("-----------");
        System.out.println("Rows read: " + metrics.getRead());
        System.out.println("Rows transformed: " + metrics.getTransformed());
        System.out.println("Rows skipped: " + metrics.getSkipped());
        System.out.println("Output file: " + outputPath);
    }
}