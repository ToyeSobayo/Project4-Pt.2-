package com.example.project4.Controllers;

import com.example.project4.Order;
import com.example.project4.Pizza;
import com.example.project4.StoreOrders;

import java.util.ArrayList;

public class DataSingleton {
    private static final DataSingleton instance = new DataSingleton();

    private Order order;
    private StoreOrders storeOrders;

    private boolean orderAdded = false;

    public static DataSingleton getInstance(){
        return instance;
    }

    public void initializeOrder(ArrayList<Pizza> pizzaList){
        order = new Order(pizzaList);
    }

    public Order getOrder() {
        return order;
    }

    public boolean getOrderAdded(){
        return orderAdded;
    }

    public void setOrderAdded(boolean bool){
        orderAdded = bool;
    }

    public void setOrder(Order newOrder){
        order = newOrder;
    }

    public void initializeStoreOrders(ArrayList <Order> ordersList){
        storeOrders = new StoreOrders(ordersList);
    }

    public StoreOrders getStoreOrders(){
        return storeOrders;
    }

    public void setStoreOrders(StoreOrders newStoreOrder){
        storeOrders = newStoreOrder;
    }

}
