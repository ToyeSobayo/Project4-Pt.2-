package com.example.project4;

import java.util.*;

public abstract class Pizza {
    protected ArrayList <Topping> toppings;
    protected Size size;
    protected Sauce sauce;
    protected double toppingsIncrement;
    protected boolean extraSauce;
    protected boolean extraCheese;

    public abstract double price();

    public abstract double getSizePrice();

    public abstract String getToppingsAsString();

    public abstract ArrayList<Topping> getToppings();

    public void setToppings(ArrayList <Topping> toppings){
        this.toppings = toppings;
    }

    public double extraCheeseAmount(){
        if(extraCheese){
            return 1.00;
        }
        else{
            return 0.0;
        }
    }

    public double extraSauceAmount(){
        if(extraSauce){
            return 1.00;
        }
        else{
            return 0.0;
        }
    }

    public String extraCheeseString(){
        if(extraCheese){
            return ", Extra cheese ";
        }
        else{
            return "";
        }
    }

    public String extraSauceString(){
        if(extraSauce){
            return ", Extra sauce";
        }
        else{
            return "";
        }
    }

    public void incrementToppingsAmount(){
        this.toppingsIncrement += 1.49;
    }

    public void decrementToppingsAmount(){
        this.toppingsIncrement -= 1.49;
    }

    public double getToppingsIncrement(){
        return this.toppingsIncrement;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public void setSauce(Sauce sauce) {
        this.sauce = sauce;
    }

    public void addExtraSauce(){
        extraSauce = true;
    }

    public void removeExtraSauce(){
        extraSauce = false;
    }

    public void addExtraCheese(){
        extraCheese = true;
    }

    public void removeExtraCheese(){
        extraCheese = false;
    }

    public Size getSize(){
        return this.size;
    }

    public Sauce getSauce(){
        return this.sauce;
    }

    public String getSizeAsString(){
        return this.size.toString();
    }

    public String getSauceAsString(){
        return this.sauce.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Pizza) {
            Pizza pizza = (Pizza) obj;
            return this.getToppings().equals(pizza.getToppings()) && this.price() == pizza.price();
        }
        return false;
    }
}
