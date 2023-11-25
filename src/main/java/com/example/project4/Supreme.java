package com.example.project4;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringJoiner;

public class Supreme extends Pizza {
    private static final DecimalFormat decimalFormat = new DecimalFormat("0.00");

    public Supreme() {
        this.toppings = new ArrayList<>(Arrays.asList(Topping.SAUSAGE, Topping.PEPPERONI, Topping.HAM, Topping.GREENPEPPER, Topping.ONION, Topping.BLACKOLIVE, Topping.MUSHROOM));
        this.sauce = Sauce.TOMATO;
    }

    @Override
    public double getSizePrice() {
        switch (this.size) {
            case SMALL: return 15.99;
            case MEDIUM: return 17.99;
            default: return 19.99;
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
        return "[Supreme]" + "[" + getSizeAsString() + "]" + "[" + getSauceAsString() + "]: " + getToppingsAsString() + extraCheeseString() + extraSauceString() + ": " + "$" + price();
    }
}