package com.example.project4;

import java.text.DecimalFormat;
import java.util.*;

public class Order {
    private ArrayList<Pizza> pizzas;
    private int orderNumber;
    public static final double SALES_TAX = 0.06625;
    private static final DecimalFormat decimalFormat = new DecimalFormat("0.00");

    public Order(ArrayList<Pizza> pizzas){
        this.pizzas = new ArrayList<>(pizzas);
    }

    public void add(Pizza pizza){
        this.pizzas.add(pizza);
    }

    public void remove(Pizza pizza){
        this.pizzas.remove(pizza);
    }

    public int getOrderNumber(){
        return this.orderNumber;
    }

    public void setOrderNumber(int orderNumber){
        this.orderNumber = orderNumber;
    }

    @Override
    public String toString(){
        StringJoiner joiner = new StringJoiner("\n");
        pizzas.forEach(pizza -> joiner.add(pizza.toString()));
        joiner.add("\nOrder total: $" + getTotalPriceWithSalesTax());
        return joiner.toString();
    }

    public double getTotalPriceWithoutTax(){
        double total = 0.0;
        for(Pizza pizza : pizzas){
            total += pizza.price();
        }
        return Double.parseDouble(decimalFormat.format(total));
    }

    public double getTotalPriceWithSalesTax(){
        double totalPriceWithoutTax = getTotalPriceWithoutTax();
        return Double.parseDouble(decimalFormat.format(totalPriceWithoutTax + (totalPriceWithoutTax * SALES_TAX)));
    }

    public double getSalesTaxOfTotal(){
        return Double.parseDouble(decimalFormat.format(getTotalPriceWithoutTax() * SALES_TAX));
    }

    public ArrayList<Pizza> getPizzaList(){
        return new ArrayList<>(this.pizzas);
    }

    public void setPizzasList(ArrayList<Pizza> newList){
        this.pizzas = new ArrayList<>(newList);
    }
}