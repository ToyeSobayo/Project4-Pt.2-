package com.example.project4;

import java.util.*;

/**
 * Abstract class representing a pizza with various attributes and functionalities.
 */
public abstract class Pizza {
    protected ArrayList<Topping> toppings;
    protected Size size;
    protected Sauce sauce;
    protected double incrementToppings;
    protected boolean extraSauce;
    protected boolean extraCheese;

    // Abstract methods to be implemented by subclasses
    public abstract double price();
    public abstract double getSizePrice();
    public abstract String getToppingsAsString();
    public abstract ArrayList<Topping> getToppings();

    // Setters for pizza attributes
    public void setToppings(ArrayList<Topping> toppings) {
        this.toppings = toppings;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public void setSauce(Sauce sauce) {
        this.sauce = sauce;
    }

    // Methods for calculating extra charges
    public double extraCheeseAmount() {
        return extraCheese ? 1.00 : 0.0;
    }

    public double extraSauceAmount() {
        return extraSauce ? 1.00 : 0.0;
    }

    // Methods for string representation of extra options
    public String extraCheeseString() {
        return extraCheese ? ", Extra cheese " : "";
    }

    public String extraSauceString() {
        return extraSauce ? ", Extra sauce" : "";
    }

    // Methods for managing toppings amount
    public void incrementToppingsAmount() {
        this.incrementToppings += 1.49;
    }

    public void decrementToppingsAmount() {
        this.incrementToppings -= 1.49;
    }

    // Getters for pizza attributes and toppings amount
    public double getIncrementToppings() {
        return this.incrementToppings;
    }

    public Size getSize() {
        return this.size;
    }

    public Sauce getSauce() {
        return this.sauce;
    }

    // Methods for adding and removing extra options
    public void addExtraSauce() {
        extraSauce = true;
    }

    public void removeExtraSauce() {
        extraSauce = false;
    }

    public void addExtraCheese() {
        extraCheese = true;
    }

    public void removeExtraCheese() {
        extraCheese = false;
    }

    // Utility methods for string representation of size and sauce
    public String getSizeAsString() {
        return this.size.toString();
    }

    public String getSauceAsString() {
        return this.sauce.toString();
    }

    // Overridden equals method for comparing pizza objects
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Pizza) {
            Pizza pizza = (Pizza) obj;
            return this.getToppings().equals(pizza.getToppings()) && this.price() == pizza.price();
        }
        return false;
    }
}
