package org.howard.edu.lsp.midterm.strategy;

/**
 * Driver class to demonstrate the Strategy Pattern implementation.
 */
public class Driver {
    public static void main(String[] args) {
        PriceCalculator calculator = new PriceCalculator();
        double price = 100.0;

        // REGULAR
        calculator.setStrategy(new RegularCustomerDiscount());
        System.out.println("REGULAR: " + calculator.calculatePrice(price));

        // MEMBER
        calculator.setStrategy(new MemberCustomerDiscount());
        System.out.println("MEMBER: " + calculator.calculatePrice(price));

        // VIP
        calculator.setStrategy(new VipCustomerDiscount());
        System.out.println("VIP: " + calculator.calculatePrice(price));

        // HOLIDAY
        calculator.setStrategy(new HolidayCustomerDiscount());
        System.out.println("HOLIDAY: " + calculator.calculatePrice(price));
    }
}