package org.howard.edu.lsp.midterm.strategy;

/**
 * Members receive a 10% discount.
 */
public class MemberCustomerDiscount implements DiscountStrategy {
    @Override
    public double applyDiscount(double price) {
        return price * 0.90;
    }
}