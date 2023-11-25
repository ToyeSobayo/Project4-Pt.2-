package com.example.project4;

/**
 * Enum representing different types of pizza toppings.
 */
public enum Topping {
    // Meat toppings
    SAUSAGE("Meat"),
    PEPPERONI("Meat"),
    HAM("Meat"),
    BEEF("Meat"),
    SHRIMP("Seafood"),
    SQUID("Seafood"),
    CHICKEN("Meat"),

    // Vegetable toppings
    GREENPEPPER("Vegetable"),
    ONION("Vegetable"),
    MUSHROOM("Vegetable"),
    BLACKOLIVE("Vegetable"),

    // Other toppings
    CRABMEATS("Seafood"),
    HOTSAUCE("Condiment");

    private final String category;

    /**
     * Constructs a Topping enum with the specified category.
     *
     * @param category The category of the topping.
     */
    Topping(String category) {
        this.category = category;
    }

    /**
     * Returns a user-friendly name of the topping.
     *
     * @return The name of the topping.
     */
    @Override
    public String toString() {
        return this.name().charAt(0) + this.name().substring(1).toLowerCase().replace('_', ' ');
    }

    /**
     * Gets the category of the topping.
     *
     * @return The category of the topping.
     */
    public String getCategory() {
        return category;
    }
}
