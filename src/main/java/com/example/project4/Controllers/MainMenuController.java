package com.example.project4.Controllers;

import com.example.project4.MainApplication;
import com.example.project4.Order;
import com.example.project4.Pizza;
import com.example.project4.StoreOrders;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class MainMenuController {
    static ArrayList<Pizza> currentPizzaList;

    static Order currentOrder = new Order(currentPizzaList);

    static StoreOrders storeOrders = new StoreOrders();

    public void initialize(){
    }

    public static Order getCurrentOrder(){
        return currentOrder;
    }

    public static void setCurrentOrder(Order newOrder){
        currentOrder = newOrder;
    }

    @FXML
    public void specialtyButtonAction(ActionEvent actionEvent) {
        Stage mainStage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        AnchorPane root;
        try {
            FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("specialities.fxml"));
            root = (AnchorPane) loader.load();
            Scene scene = new Scene(root, 600, 400);
            mainStage.setScene(scene);
            mainStage.setTitle("Specialties");
            mainStage.show();
            SpecialitiesController specialtiesController = loader.getController();
            specialtiesController.setMainController(this);
        }catch (IOException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Loading page");
            alert.setContentText("Couldn't load page");
            alert.showAndWait();
        }
    }

    @FXML
    public void buildYourOwnButtonAction(ActionEvent actionEvent) {
        Stage mainStage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        AnchorPane root;
        try {
            FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("BuildYourOwn.fxml"));
            root = (AnchorPane) loader.load();
            Scene scene = new Scene(root, 600, 400);
            mainStage.setScene(scene);
            mainStage.setTitle("Build Your Own");
            mainStage.show();
            BuildOwnController byoController = loader.getController();
            byoController.setMainController(this);
        }catch (IOException e){
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Loading page");
            alert.setContentText("Couldn't load page");
            alert.showAndWait();
        }
    }

    @FXML
    public void currentOrderButtonAction(ActionEvent actionEvent) {
        Stage mainStage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        AnchorPane root;
        try {
            FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("currentOrder.fxml"));
            root = (AnchorPane) loader.load();
            Scene scene = new Scene(root, 600, 400);
            mainStage.setScene(scene);
            mainStage.setTitle("Current Order");
            mainStage.show();
            CurrentOrderController currentOrderController = loader.getController();
            currentOrderController.setMainController(this);
        }catch (IOException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Loading page");
            alert.setContentText("Couldn't load page");
            alert.showAndWait();
        }
    }

    @FXML
    public void storeOrdersButtonAction(ActionEvent actionEvent) {
        Stage mainStage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        AnchorPane root;
        try {
            FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("storeOrders.fxml"));
            root = (AnchorPane) loader.load();
            Scene scene = new Scene(root, 600, 400);
            mainStage.setScene(scene);
            mainStage.setTitle("Store Orders");
            mainStage.show();
            StoreOrdersController storeOrdersController = loader.getController();
            storeOrdersController.setMainController(this);
        }catch (IOException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Loading page");
            alert.setContentText("Couldn't load page");
            alert.showAndWait();
        }
    }
}
