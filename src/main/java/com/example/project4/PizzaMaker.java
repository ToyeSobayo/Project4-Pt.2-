package com.example.project4;

/**
 * Factory class for creating different types of Pizza objects.
 */
public class PizzaMaker {

    /**
     * Creates a pizza object of the specified type.
     *
     * @param pizzaType The type of pizza to create.
     * @return A Pizza object of the specified type.
     * @throws IllegalArgumentException If an unknown pizza type is provided.
     */
    public static Pizza createPizza(String pizzaType) {
        return switch (pizzaType) {
            case "Deluxe" -> new Deluxe();
            case "Supreme" -> new Supreme();
            case "Meatzza" -> new Meatzza();
            case "Seafood" -> new Seafood();
            case "Pepperoni" -> new Pepperoni();
            // Handle unknown pizza types
            default -> throw new IllegalArgumentException("Unknown pizza type: " + pizzaType);
        };
    }
}
