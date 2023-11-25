package com.example.project4;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringJoiner;

public class Seafood extends Pizza {
    private static final DecimalFormat decimalFormat = new DecimalFormat("0.00");

    public Seafood() {
        this.toppings = new ArrayList<>(Arrays.asList(Topping.SHRIMP, Topping.SQUID, Topping.CRABMEATS));
        this.sauce = Sauce.ALFREDO;
    }

    @Override
    public double getSizePrice() {
        switch (this.size) {
            case SMALL: return 17.99;
            case MEDIUM: return 19.99;
            default: return 21.99;
        }
    }

    @Override
    public double price() {
        return Double.parseDouble(decimalFormat.format(getSizePrice() + extraCheeseAmount() + extraSauceAmount()));
    }

    @Override
    public String getToppingsAsString() {
        StringJoiner joiner = new StringJoiner(", ");
        for (Topping topping : this.toppings) {
            joiner.add(topping.toString());
        }
        return joiner.toString();
    }

    @Override
    public ArrayList<Topping> getToppings() {
        return this.toppings;
    }

    @Override
    public String toString() {
        return "[Seafood]" + "[" + getSizeAsString() + "]" + "[" + getSauceAsString() + "]: " + getToppingsAsString() + extraCheeseString() + extraSauceString() + ": " + "$" + price();
    }
}
