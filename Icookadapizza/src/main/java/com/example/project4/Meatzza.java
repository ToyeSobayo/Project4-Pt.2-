package com.example.project4;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringJoiner;

public class Meatzza extends Pizza {
    private static final DecimalFormat decimalFormat = new DecimalFormat("0.00");

    public Meatzza() {
        this.toppings = new ArrayList<>(Arrays.asList(Topping.SAUSAGE, Topping.PEPPERONI, Topping.BEEF, Topping.HAM));
        this.sauce = Sauce.TOMATO;
    }

    @Override
    public double getSizePrice() {
        switch (this.size) {
            case SMALL: return 16.99;
            case MEDIUM: return 18.99;
            default: return 20.99;
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
        return "[Meatzza]" + "[" + getSizeAsString() + "]" + "[" + getSauceAsString() + "]: " + getToppingsAsString() + extraCheeseString() + extraSauceString() + ": " + "$" + price();
    }
}
