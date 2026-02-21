package org.howard.edu.lsp.assignment3;

/**
 * Holds the transformation interface and concrete steps in one place to reduce files.
 * Demonstrates polymorphism (Transformation<T>) and inheritance (PriceRule base class).
 */
public class Transformations {

    /** A single stateless step that transforms a record or signals it should be skipped. */
    public interface Transformation<T> {
        /**
         * Transforms the input object in-place or returns the same reference after updates.
         * Implementations MAY throw Pipeline.SkipRecordException to drop the record.
         */
        T apply(T input);
    }

    /**
     * Abstract base for price-changing rules: applies transformPrice() and rounds to 2 decimals.
     * Centralizing rounding here guarantees consistent money behavior across all price rules.
     */
    public static abstract class PriceRule implements Transformation<Product> {
        @Override
        public Product apply(Product input) {
            // 1) Let subclass compute a new raw price using current product state.
            double newPrice = transformPrice(input);

            // 2) Enforce two-decimal rounding policy (same logic as Assignment 2).
            input.setPrice(Util.round2(newPrice));
            return input;
        }

        /** Calculates the new price BEFORE rounding; subclasses implement rule-specific math. */
        protected abstract double transformPrice(Product product);
    }

    /** Uppercases the product name; pure string transform, no branching. */
    public static class UppercaseName implements Transformation<Product> {
        @Override
        public Product apply(Product input) {
            input.setName(input.getName().toUpperCase());
            return input;
        }
    }

    /**
     * If the ORIGINAL category was "Electronics", apply a percentage discount (e.g., 0.10 for 10%).
     * Extends PriceRule to inherit the rounding behavior automatically.
     */
    public static class ElectronicsDiscountRule extends PriceRule {
        private final double discountRate;

        /** @param discountRate value in [0,1]; 0.10 means 10% discount */
        public ElectronicsDiscountRule(double discountRate) {
            this.discountRate = discountRate;
        }

        @Override
        protected double transformPrice(Product product) {
            return "Electronics".equals(product.getOriginalCategory())
                 ? product.getPrice() * (1.0 - discountRate)
                 : product.getPrice();
        }
    }

    /**
     * Upgrades category to "Premium Electronics" if original was Electronics
     * and the (already discounted & rounded) price now exceeds 500.00.
     */
    public static class CategoryUpgradeRule implements Transformation<Product> {
        @Override
        public Product apply(Product input) {
            if ("Electronics".equals(input.getOriginalCategory()) && input.getPrice() > 500.00) {
                input.setCategory("Premium Electronics");
            }
            return input;
        }
    }

    /**
     * Derives price-range buckets: Low (<=10), Medium (<=100), High (<=500), Premium (>500).
     * The thresholds match the Assignment 2 logic exactly.
     */
    public static class PriceRangeDeriver implements Transformation<Product> {
        @Override
        public Product apply(Product input) {
            double price = input.getPrice();
            String range = (price <= 10.0) ? "Low"
                         : (price <= 100.0) ? "Medium"
                         : (price <= 500.0) ? "High"
                         : "Premium";
            input.setPriceRange(range);
            return input;
        }
    }
}