package org.howard.edu.lsp.midterm.strategy;

/**
 * Holiday customers receive a 15% discount.
 */
public class HolidayCustomerDiscount implements DiscountStrategy {
    @Override
    public double applyDiscount(double price) {
        return price * 0.85;
    }
}