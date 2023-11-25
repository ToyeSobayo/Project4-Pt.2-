package com.example.project4;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringJoiner;

public class Pepperoni extends Pizza {
    private static final DecimalFormat decimalFormat = new DecimalFormat("0.00");

    public Pepperoni() {
        this.toppings = new ArrayList<>(Arrays.asList(Topping.PEPPERONI));
        this.sauce = Sauce.TOMATO;
    }

    @Override
    public double getSizePrice() {
        switch (this.size) {
            case SMALL: return 10.99;
            case MEDIUM: return 12.99;
            default: return 14.99;
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
        return "[Pepperoni]" + "[" + getSizeAsString() + "]" + "[" + getSauceAsString() + "]: " + getToppingsAsString() + extraCheeseString() + extraSauceString() + ": " + "$" + price();
    }
}
