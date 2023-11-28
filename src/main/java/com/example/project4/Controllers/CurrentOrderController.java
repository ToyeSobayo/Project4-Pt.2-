package com.example.project4.Controllers;

import com.example.project4.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class CurrentOrderController {
    private MainMenuController mainMenuController;

    @FXML
    private TextField orderNumberTextField;

    @FXML
    private TextField subtotalTextField;

    @FXML
    private TextField salesTaxTextField;

    @FXML
    private TextField orderTotalTextField;

    @FXML
    private ListView orderContentsListView;

    DataSingleton dataSingleton = DataSingleton.getInstance();

    public void initialize(){
        orderNumberTextField.setEditable(false);
        subtotalTextField.setEditable(false);
        salesTaxTextField.setEditable(false);
        orderTotalTextField.setEditable(false);
        StoreOrders storeOrders = dataSingleton.getStoreOrders();
        Order currOrder = dataSingleton.getOrder();

        if(currOrder != null){
            if(storeOrders == null){
                ArrayList <Order> orderList = new ArrayList<>();
                StoreOrders newStoreOrder = new StoreOrders(orderList);
                newStoreOrder.add(currOrder);
                dataSingleton.setStoreOrders(newStoreOrder);
                orderNumberTextField.setText(String.valueOf(currOrder.getOrderNumber()));
                ObservableList<Pizza> orderPizzaObservableList = FXCollections.observableList(currOrder.getPizzaList());
                orderContentsListView.setItems(orderPizzaObservableList);
                subtotalTextField.setText(String.valueOf(currOrder.getTotalPriceWithoutTax()));
                salesTaxTextField.setText(String.valueOf(currOrder.getSalesTaxOfTotal()));
                orderTotalTextField.setText(String.valueOf(currOrder.getTotalPriceWithSalesTax()));
            }
            else{
                storeOrders.add(currOrder);
                dataSingleton.setOrderAdded(true);
                dataSingleton.setStoreOrders(storeOrders);
                orderNumberTextField.setText(String.valueOf(currOrder.getOrderNumber()));
                ObservableList<Pizza> orderPizzaObservableList = FXCollections.observableList(currOrder.getPizzaList());
                orderContentsListView.setItems(orderPizzaObservableList);
                subtotalTextField.setText(String.valueOf(currOrder.getTotalPriceWithoutTax()));
                salesTaxTextField.setText(String.valueOf(currOrder.getSalesTaxOfTotal()));
                orderTotalTextField.setText(String.valueOf(currOrder.getTotalPriceWithSalesTax()));
            }
        }
    }

    public void backToMainAction(ActionEvent actionEvent) {
        Order currOrder = dataSingleton.getOrder();
        if(currOrder != null){
            StoreOrders storeOrders = dataSingleton.getStoreOrders();
            storeOrders.remove(currOrder);
        }

        Stage mainStage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        AnchorPane root;
        try {
            FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("MainMenu.fxml"));
            root = (AnchorPane) loader.load();
            Scene scene = new Scene(root, 600, 400);
            mainStage.setScene(scene);
            mainStage.setTitle("Main Menu");
            mainStage.show();
        }catch (IOException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Loading page");
            alert.setContentText("Couldn't load page");
            alert.showAndWait();
        }
    }

    public void setMainController (MainMenuController controller) {
        mainMenuController = controller;
    }

    @FXML
    public void placeOrder(ActionEvent actionEvent) {
        Order currentOrder = dataSingleton.getOrder();

        if(currentOrder == null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Order Already Placed");
            alert.setHeaderText("Order already placed!");
            alert.setContentText("It's too late bub");
            alert.showAndWait();
            return;
        }

        ArrayList <Pizza> orderPizzaList = currentOrder.getPizzaList();

        if(orderPizzaList.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Empty Order");
            alert.setHeaderText("Your order is empty!");
            alert.setContentText("Hey bud, you just blow in from stupid town?");
            alert.showAndWait();
            return;
        }
        currentOrder = null;
        dataSingleton.setOrder(currentOrder);
        dataSingleton.setOrderAdded(false);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Order Placed");
        alert.setHeaderText("Order received");
        alert.setContentText("Enjoy your food big back");
        alert.showAndWait();
    }

    @FXML
    public void removeSelectedPizza(ActionEvent actionEvent) {
        Pizza pizzaToRemove = (Pizza) orderContentsListView.getSelectionModel().getSelectedItem();
        if (pizzaToRemove == null) {
            showAlert("Item Selection Needed", "No Pizza Selected", "Please select a pizza to remove.", Alert.AlertType.WARNING);
        } else {
            if (!removePizzaFromOrder(pizzaToRemove)) {
                showAlert("Order Processing Error", "Order Not Found", "The order has already been placed or does not exist.", Alert.AlertType.WARNING);
            } else {
                updateOrderViews();
            }
        }
    }

    private boolean removePizzaFromOrder(Pizza pizza) {
        Order currentOrder = dataSingleton.getOrder();
        if (currentOrder == null) {
            return false;
        }
        ArrayList<Pizza> pizzasInOrder = currentOrder.getPizzaList();
        pizzasInOrder.remove(pizza);
        currentOrder.setPizzasList(pizzasInOrder);
        dataSingleton.setOrder(currentOrder);
        return true;
    }

    private void updateOrderViews() {
        Order currentOrder = dataSingleton.getOrder();
        ObservableList<Pizza> updatedPizzaList = FXCollections.observableList(currentOrder.getPizzaList());
        orderContentsListView.setItems(updatedPizzaList);
        subtotalTextField.setText(String.valueOf(currentOrder.getTotalPriceWithoutTax()));
        salesTaxTextField.setText(String.valueOf(currentOrder.getSalesTaxOfTotal()));
        orderTotalTextField.setText(String.valueOf(currentOrder.getTotalPriceWithSalesTax()));
    }

    private void showAlert(String title, String header, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
