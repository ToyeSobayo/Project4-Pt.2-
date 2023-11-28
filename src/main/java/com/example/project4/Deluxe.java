package com.example.project4;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.ArrayList;

/**
 * Deluxe pizza class, a subtype of Pizza with predefined toppings and sauce.
 */
public class Deluxe extends Pizza {

    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0.00");
    private static final double PRICE_SMALL = 14.99;
    private static final double PRICE_MEDIUM = 16.99;
    private static final double PRICE_LARGE = 18.99;

    /**
     * Constructor for Deluxe pizza, sets default toppings and sauce.
     */
    public Deluxe() {
        this.toppings = new ArrayList<>(Arrays.asList(Topping.SAUSAGE, Topping.PEPPERONI, Topping.GREENPEPPER, Topping.ONION, Topping.MUSHROOM));
        this.sauce = Sauce.TOMATO;
        this.extraSauce = false;
        this.extraCheese = false;
    }

    @Override
    public double getSizePrice() {
        switch (this.size) {
            case SMALL:
                return PRICE_SMALL;
            case MEDIUM:
                return PRICE_MEDIUM;
            default:
                return PRICE_LARGE;
        }
    }

    @Override
    public double price() {
        double total = getSizePrice() + extraCheeseAmount() + extraSauceAmount();
        return Double.parseDouble(DECIMAL_FORMAT.format(total));
    }

    @Override
    public String getToppingsAsString() {
        StringBuilder toppingsBuilder = new StringBuilder();
        for (Topping topping : this.toppings) {
            if (toppingsBuilder.length() > 0) {
                toppingsBuilder.append(", ");
            }
            toppingsBuilder.append(topping.toString());
        }
        return toppingsBuilder.toString();
    }

    @Override
    public ArrayList<Topping> getToppings() {
        return this.toppings;
    }

    @Override
    public String toString() {
        return String.format("[Deluxe] [%s] [%s]: %s%s%s: $%.2f",
                getSizeAsString(), getSauceAsString(), getToppingsAsString(),
                extraCheeseString(), extraSauceString(), price());
    }
}
