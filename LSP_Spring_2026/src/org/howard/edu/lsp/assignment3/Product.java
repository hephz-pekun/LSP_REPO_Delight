package org.howard.edu.lsp.assignment3;

/**
 * Domain model encapsulating a product row as it flows through the pipeline.
 * Fields are private with narrow setters to control mutation during transformations.
 */
public class Product {
    private final int id;
    private String name;
    private double price;
    private String category;
    private String priceRange;       // set later by a transformation
    private final String originalCategory; // preserved to apply rules based on original meaning

    /**
     * Constructs a product directly from parsed CSV fields.
     * @param id numeric product ID
     * @param name raw name from input (transform will uppercase)
     * @param price numeric price before any discounts
     * @param category current category
     * @param originalCategory category as read from input (used by rules)
     */
    public Product(int id, String name, double price, String category, String originalCategory) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.originalCategory = originalCategory;
    }

    /** @return immutable product ID */
    public int getId() { return id; }
    /** @return current (possibly transformed) name */
    public String getName() { return name; }
    /** Sets the name (used by Uppercase transform). */
    public void setName(String name) { this.name = name; }
    /** @return current (possibly discounted) price */
    public double getPrice() { return price; }
    /** Sets the price (used by price rules). */
    public void setPrice(double price) { this.price = price; }
    /** @return current category (may be upgraded) */
    public String getCategory() { return category; }
    /** Sets the category (used by upgrade rule). */
    public void setCategory(String category) { this.category = category; }
    /** @return derived price-range label */
    public String getPriceRange() { return priceRange; }
    /** Sets the derived price-range label. */
    public void setPriceRange(String priceRange) { this.priceRange = priceRange; }
    /** @return the original category from input */
    public String getOriginalCategory() { return originalCategory; }
}