package org.howard.edu.lsp.midterm.strategy;

/**
 * Price calculator that uses a discount strategy to determine final price.
 */
public class PriceCalculator {

    private DiscountStrategy strategy;

    /**
     * Set the desired discount strategy.
     *
     * @param strategy the discount strategy to use
     */
    public void setStrategy(DiscountStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Calculate the final price using the selected strategy.
     *
     * @param price the original price
     * @return the final price after applying the strategy
     */
    public double calculatePrice(double price) {
        return strategy.applyDiscount(price);
    }
}