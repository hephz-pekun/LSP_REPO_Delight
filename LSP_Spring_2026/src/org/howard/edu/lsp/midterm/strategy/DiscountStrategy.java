package org.howard.edu.lsp.midterm.strategy;

/**
 * Strategy interface for applying price discounts.
 */
public interface DiscountStrategy {
    /**
     * Apply a discount to the given price.
     *
     * @param price the original price
     * @return the discounted price
     */
    double applyDiscount(double price);
}