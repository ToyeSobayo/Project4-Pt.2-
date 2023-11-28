package com.example.project4;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.StringJoiner;

public class BuildYourOwn extends Pizza {

    private static final DecimalFormat decimalFormat = new DecimalFormat("0.00");

    public BuildYourOwn() {
        this.incrementToppings = 0.0;
        this.extraSauce = false;
        this.extraCheese = false;
    }

    @Override
    public double getSizePrice() {
        return switch (this.size) {
            case SMALL -> 8.99;
            case MEDIUM -> 10.99;
            default -> 12.99;
        };
    }

    @Override
    public double price() {
        return Double.parseDouble(decimalFormat.format(getSizePrice() + extraCheeseAmount() + extraSauceAmount() + getIncrementToppings()));
    }

    @Override
    public String getToppingsAsString() {
        if (this.toppings == null || this.toppings.isEmpty()) {
            return "";
        }
        StringJoiner joiner = new StringJoiner(", ");
        this.toppings.forEach(topping -> joiner.add(topping.toString()));
        return joiner.toString();
    }

    @Override
    public ArrayList<Topping> getToppings() {
        return this.toppings;
    }

    @Override
    public String toString() {
        String base = "[Build Your Own][" + getSizeAsString() + "][" + getSauceAsString() + "]: ";
        String toppings = getToppingsAsString();
        String extras = extraCheeseString() + extraSauceString();
        String priceString = ": $" + price();
        return base + toppings + (this.extraCheese || this.extraSauce ? extras : extras.replace(",", "")) + priceString;
    }
}
