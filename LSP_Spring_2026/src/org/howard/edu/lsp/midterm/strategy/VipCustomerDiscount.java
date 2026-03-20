package org.howard.edu.lsp.midterm.strategy;

/**
 * VIP customers receive a 20% discount.
 */
public class VipCustomerDiscount implements DiscountStrategy {
    @Override
    public double applyDiscount(double price) {
        return price * 0.80;
    }
}