package org.howard.edu.lsp.midterm.strategy;

/**
 * No discount for regular customers.
 */
public class RegularCustomerDiscount implements DiscountStrategy {
    @Override
    public double applyDiscount(double price) {
        return price;
    }
}