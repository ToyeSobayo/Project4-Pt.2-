package com.example.project4;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class PizzaMessAround {
    public static void main(String [] args){
        Pizza pizza = new Deluxe();
        pizza.setSauce(Sauce.TOMATO);
        pizza.setSize(Size.LARGE);

        Pizza pizza2 = new Supreme();
        pizza2.setSauce(Sauce.TOMATO);
        pizza2.setSize(Size.MEDIUM);

        ArrayList <Pizza> pizzas = new ArrayList<>();
        pizzas.add(pizza);

        Order order = new Order(pizzas);

        Pizza pizza3 = new Meatzza();
        pizza3.setSauce(Sauce.ALFREDO);
        pizza3.setSize(Size.SMALL);
        ArrayList <Pizza> pizzas1 = new ArrayList<>();
        pizzas1.add(pizza3);

        Order order1 = new Order(pizzas1);

        StoreOrders storeOrders = new StoreOrders(new ArrayList<Order>());
        storeOrders.add(order);
        storeOrders.add(order1);

        //System.out.println(storeOrders.toString());
        System.out.println(order1.toString());
    }
}
