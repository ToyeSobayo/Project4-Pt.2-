package com.example.project4.Controllers;

import com.example.project4.Controllers.MainMenuController;
import com.example.project4.MainApplication;
import com.example.project4.Order;
import com.example.project4.Pizza;
import com.example.project4.StoreOrders;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class StoreOrdersController {
    private MainMenuController mainMenuController;

    private ObservableList<Integer> orderNumberList;

    @FXML
    private TextField orderTotalTextField;

    @FXML
    private ComboBox orderNumbersComboBox;

    @FXML
    private ListView orderInformationListView;

    DataSingleton dataSingleton = DataSingleton.getInstance();

    public void initialize(){
        orderTotalTextField.setEditable(false);
        StoreOrders storeOrders = dataSingleton.getStoreOrders();
        ArrayList <Integer> orderNumbersToBeAdded = new ArrayList<>();
        if(storeOrders != null) {
            ArrayList<Order> orderList = storeOrders.getOrders();
            for(int i = 0; i < orderList.size(); i ++){
                orderNumbersToBeAdded.add(orderList.get(i).getOrderNumber());
            }
            orderNumberList = FXCollections.observableArrayList(orderNumbersToBeAdded);
            orderNumbersComboBox.setItems(orderNumberList);
        }
    }

    public void backToMainAction(ActionEvent actionEvent) {
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
    public void orderNumbersComboBoxAction(ActionEvent actionEvent) {
        Object selectedOrder = orderNumbersComboBox.getSelectionModel().getSelectedItem();
        if(selectedOrder == null){
            orderTotalTextField.clear();
            return;
        }
        int selectedOrderNumber = (int)orderNumbersComboBox.getSelectionModel().getSelectedItem();
        StoreOrders storeOrders = dataSingleton.getStoreOrders();
        ArrayList <Order> orders = storeOrders.getOrders();
        for(int i = 0; i < orders.size(); i ++){
            if(orders.get(i).getOrderNumber() == selectedOrderNumber){
                ObservableList<Pizza> orderPizzaObservableList = FXCollections.observableList(orders.get(i).getPizzaList());
                orderInformationListView.setItems(orderPizzaObservableList);
                orderTotalTextField.setText(String.valueOf(orders.get(i).getTotalPriceWithSalesTax()));
            }
        }
    }

    @FXML
    public void cancelOrder(ActionEvent actionEvent) {
        Integer orderToCancel = (Integer) orderNumbersComboBox.getSelectionModel().getSelectedItem();
        if (orderToCancel == null) {
            showWarningAlert("Order Selection Required", "No order selected for cancellation", "Please select an order number to cancel.");
        } else {
            removeOrder(orderToCancel);
            refreshOrderViews();
        }
    }

    private void showWarningAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void removeOrder(int orderNumber) {
        StoreOrders storeOrders = dataSingleton.getStoreOrders();
        ArrayList<Order> orders = storeOrders.getOrders();
        orders.removeIf(order -> order.getOrderNumber() == orderNumber);
        storeOrders.setOrders(orders);
        dataSingleton.setStoreOrders(storeOrders);
    }

    private void refreshOrderViews() {
        orderInformationListView.getItems().clear();
        Integer selectedOrderNumber = (Integer) orderNumbersComboBox.getSelectionModel().getSelectedItem();
        orderNumberList.remove(selectedOrderNumber);
        orderNumbersComboBox.setItems(orderNumberList);
    }

    public void exportStoreOrders(ActionEvent actionEvent) {
        if (!hasStoreOrders()) {
            displayAlert("Create Order Message", "Please create an order to export", "You need to print before you create", Alert.AlertType.WARNING);
        } else {
            exportOrders();
        }
    }

    private boolean hasStoreOrders() {
        StoreOrders storeOrders = dataSingleton.getStoreOrders();
        return storeOrders != null && hasValidOrders(storeOrders);
    }

    private boolean hasValidOrders(StoreOrders storeOrders) {
        ArrayList<Order> ordersList = storeOrders.getOrders();
        return ordersList != null && !ordersList.isEmpty();
    }

    private void displayAlert(String title, String header, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void exportOrders() {
        StoreOrders storeOrders = dataSingleton.getStoreOrders();
        storeOrders.export();
        displayAlert("Order Exported Message", "Order Has Been Exported", "Now go away", Alert.AlertType.CONFIRMATION);
    }
}
