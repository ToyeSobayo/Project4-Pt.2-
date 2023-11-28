package com.example.project4;

import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class StoreOrders {
    private ArrayList<Order> orders;
    private int currentOrderNumber;
    private static int nextOrderNumber;

    public StoreOrders() {
        this.orders = new ArrayList<>();
        this.currentOrderNumber = 1;
    }

    public StoreOrders(ArrayList<Order> orders) {
        this();
        this.orders.addAll(orders);
    }

    public void add(Order order) {
        order.setOrderNumber(this.currentOrderNumber++);
        this.orders.add(order);
    }

    public void remove(Order order) {
        this.orders.removeIf(o -> o.getOrderNumber() == order.getOrderNumber());
    }

    public int getNextOrderNumber() {
        return this.orders.stream().mapToInt(Order::getOrderNumber).max().orElse(0) + 1;
    }

    public void export() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save File");
        File file = fileChooser.showSaveDialog(null);
        if (file != null) {
            try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
                writer.println(this);
            } catch (IOException ex) {
                ex.printStackTrace(); // Consider proper error handling
            }
        }
    }

    public ArrayList<Order> getOrders() {
        return new ArrayList<>(this.orders);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (Order order : this.orders) {
            res.append("Order number ").append(order.getOrderNumber()).append("\n")
                    .append(order).append("\n");
        }
        return res.toString();
    }

    public void setOrders(ArrayList<Order> newOrders) {
        this.orders = new ArrayList<>(newOrders);
    }
}
