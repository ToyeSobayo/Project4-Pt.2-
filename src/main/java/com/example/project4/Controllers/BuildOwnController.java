package com.example.project4.Controllers;

import com.example.project4.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.invoke.CallSite;
import java.util.ArrayList;

/**
 * Controller class for building custom pizzas in a JavaFX application.
 */
public class BuildOwnController {
    private MainMenuController mainMenuController;

    @FXML
    private ToggleGroup SizesGroup;

    @FXML
    private ToggleGroup SaucesGroup;

    @FXML
    private CheckBox extraCheeseCheckBox;

    @FXML
    private CheckBox extraSauceCheckBox;

    @FXML
    private ListView toppingsListView;

    @FXML
    private ListView selectedToppingsListView;

    @FXML
    private TextField pizzaPriceTextField;

    private int toppingCount;

    Pizza pizzaToBeAdded;

    DataSingleton dataSingleton = DataSingleton.getInstance();

    public void initialize(){
        pizzaPriceTextField.setEditable(false);
        toppingCount = 0;
        PizzaMaker pizzaMaker = new PizzaMaker();
        pizzaToBeAdded = pizzaMaker.createPizza("Build Your Own");
        ObservableList<Topping> toppingsObservableList = FXCollections.observableArrayList(Topping.BEEF,Topping.HAM
                ,Topping.BLACKOLIVE,Topping.CHICKEN,
                Topping.CRABMEATS,Topping.GREENPEPPER,Topping.HOTSAUCE,Topping.MUSHROOM,Topping.ONION,
                Topping.PEPPERONI, Topping.SAUSAGE,Topping.SHRIMP,Topping.SQUID);
        toppingsListView.setItems(toppingsObservableList);
    }

    @FXML
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

    public void setMainController (MainMenuController controller){
        mainMenuController = controller;
    }

    @FXML
    public void addToppingButtonAction(ActionEvent actionEvent) {
        RadioButton selectedSizeButton = (RadioButton) SizesGroup.getSelectedToggle();
        RadioButton selectedSauceButton = (RadioButton) SaucesGroup.getSelectedToggle();
        if(selectedSizeButton == null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Select Size");
            alert.setHeaderText("Please select Size");
            alert.setContentText("Size before toppings...");
            alert.showAndWait();
        }
        else if(selectedSauceButton == null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Select Sauce");
            alert.setHeaderText("Please select Sauce");
            alert.setContentText("Sauce before toppings");
            alert.showAndWait();
        }
        else{
            Topping selectedTopping = (Topping)toppingsListView.getSelectionModel().getSelectedItem();
            if(selectedTopping == null){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Select Topping");
                alert.setHeaderText("Please select Topping");
                alert.setContentText("Pick a topping");
                alert.showAndWait();
            }
            else{
                addButtonHelper(selectedTopping);
            }
        }
    }

    public void addButtonHelper(Topping selectedTopping){
        ObservableList <Topping> toppingsList = selectedToppingsListView.getItems();
        if(toppingsList == null){
            ArrayList <Topping> newToppingList = new ArrayList<>();
            newToppingList.add(selectedTopping);
            ObservableList<Topping> toppingArrayListToBeSet = FXCollections.observableList(newToppingList);
            selectedToppingsListView.setItems(toppingArrayListToBeSet);
        }
        else{
            if(toppingsList.contains(selectedTopping)){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Topping Already Added");
                alert.setHeaderText("Topping has already been added");
                alert.setContentText("Pick something different");
                alert.showAndWait();
            }
            else{
                if(toppingCount >= 7){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Topping Limit Reached");
                    alert.setHeaderText("Topping Limit has been Reached!");
                    alert.setContentText("Calm down Fatty Jesus Christ");
                    alert.showAndWait();
                }
                else {
                    toppingsList.add(selectedTopping);
                    pizzaToBeAdded.incrementToppingsAmount();
                    selectedToppingsListView.setItems(toppingsList);
                    pizzaPriceTextField.setText(String.valueOf(pizzaToBeAdded.price()));
                    toppingCount ++;
                }
            }
        }
    }

    @FXML
    public void removeToppingButtonAction(ActionEvent event) {
        Topping toppingToRemove = getSelectedTopping();

        if (toppingToRemove == null) {
            displayRemovalAlert();
        } else {
            removeSelectedTopping(toppingToRemove);
        }
    }

    private Topping getSelectedTopping() {
        return (Topping) selectedToppingsListView.getSelectionModel().getSelectedItem();
    }

    private void displayRemovalAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Select Topping to Remove");
        alert.setHeaderText("Please Select a Topping to Remove");
        alert.setContentText("Remove Something...");
        alert.showAndWait();
    }

    private void removeSelectedTopping(Topping topping) {
        ObservableList<Topping> toppings = selectedToppingsListView.getItems();
        toppings.remove(topping);
        toppingCount--;
        pizzaToBeAdded.decrementToppingsAmount();
        updateDisplay(toppings);
    }

    private void updateDisplay(ObservableList<Topping> toppings) {
        pizzaPriceTextField.setText(String.valueOf(pizzaToBeAdded.price()));
        selectedToppingsListView.setItems(toppings);
    }

    @FXML
    public void smallRadioButtonAction(ActionEvent actionEvent) {
        pizzaToBeAdded.setSize(Size.SMALL);
        pizzaPriceTextField.setText(String.valueOf(pizzaToBeAdded.price()));
    }

    @FXML
    public void mediumRadioButtonAction(ActionEvent actionEvent) {
        pizzaToBeAdded.setSize(Size.MEDIUM);
        pizzaPriceTextField.setText(String.valueOf(pizzaToBeAdded.price()));
    }

    @FXML
    public void largeRadioButtonAction(ActionEvent actionEvent) {
        pizzaToBeAdded.setSize(Size.LARGE);
        pizzaPriceTextField.setText(String.valueOf(pizzaToBeAdded.price()));
    }

    @FXML
    public void tomatoSauceRadioButtonAction(ActionEvent actionEvent) {
        pizzaToBeAdded.setSauce(Sauce.TOMATO);
    }

    @FXML
    public void alfredoSauceRadioButtonAction(ActionEvent actionEvent) {
        pizzaToBeAdded.setSauce(Sauce.ALFREDO);
    }

    @FXML
    public void addToOrderButtonAction(ActionEvent actionEvent) {
        RadioButton sizeButton = (RadioButton) SizesGroup.getSelectedToggle();
        RadioButton sauceButton = (RadioButton) SaucesGroup.getSelectedToggle();

        if (isSelectionMissing(sizeButton)) {
            showOrderAlert("Select Size", "You forgot to select a size", "Size then toppings");
        } else if (isSelectionMissing(sauceButton)) {
            showOrderAlert("Select Sauce", "You forgot to select a sauce", "Sauce then toppings");
        } else {
            processAddToOrder();
        }
    }

    private boolean isSelectionMissing(RadioButton button) {
        return button == null;
    }

    private void showOrderAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void processAddToOrder() {
        addToOrderButtonHelper();
    }

    public void addToOrderButtonHelper(){
        ObservableList<Topping> toppings = selectedToppingsListView.getItems();

        if (!isValidToppingsSelection(toppings)) {
            showToppingsAlert();
        } else {
            addPizzaOrder(toppings);
        }
    }

    private boolean isValidToppingsSelection(ObservableList<Topping> toppings) {
        return toppings != null && toppings.size() >= 3;
    }

    private void showToppingsAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Select Toppings");
        alert.setHeaderText("You forgot to select toppings");
        alert.setContentText("3 toppings minimum");
        alert.showAndWait();
    }

    private void addPizzaOrder(ObservableList<Topping> toppings) {
        if (pizzaToBeAdded != null) {
            Order currentOrder = dataSingleton.getOrder();
            ArrayList<Topping> toppingsToSet = new ArrayList<>(toppings);
            pizzaToBeAdded.setToppings(toppingsToSet);

            if (currentOrder == null) {
                createNewOrder();
            } else {
                updateExistingOrder(currentOrder);
            }
            createAddedAlert();
        }
    }

    private void createNewOrder() {
        ArrayList<Pizza> pizzaList = new ArrayList<>();
        pizzaList.add(pizzaToBeAdded);
        Order newOrder = new Order(pizzaList);
        dataSingleton.setOrder(newOrder);
    }

    private void updateExistingOrder(Order order) {
        order.add(pizzaToBeAdded);
        dataSingleton.setOrder(order);
    }

    public void createAddedAlert(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Pizza Added Message");
        alert.setHeaderText("Pizza has been added!");
        alert.setContentText("Food's on the way tubby");
        alert.showAndWait();
    }

    @FXML
    public void handleExtraSauce(ActionEvent actionEvent) {
        RadioButton sizeButton = (RadioButton) SizesGroup.getSelectedToggle();
        RadioButton sauceButton = (RadioButton) SaucesGroup.getSelectedToggle();

        if (!isSelectionValid(sizeButton)) {
            displayAlert("Select Size", "Please select Size", "Size then toppings");
        } else if (!isSelectionValid(sauceButton)) {
            displayAlert("Select Sauce", "Please select Sauce", "Sauce then toppings");
        } else {
            applySauceChanges();
        }
    }

    private boolean isSelectionValid(RadioButton button) {
        return button != null;
    }

    private void displayAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void applySauceChanges() {
        if (extraSauceCheckBox.isSelected()) {
            pizzaToBeAdded.addExtraSauce();
        } else {
            pizzaToBeAdded.removeExtraSauce();
        }
        updatePizzaPrice();
    }

    private void updatePizzaPrice() {
        pizzaPriceTextField.setText(String.valueOf(pizzaToBeAdded.price()));
    }

    @FXML
    public void handleExtraCheese(ActionEvent actionEvent) {
        RadioButton sizeButton = (RadioButton) SizesGroup.getSelectedToggle();
        RadioButton sauceButton = (RadioButton) SaucesGroup.getSelectedToggle();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        boolean isSizeSelected = sizeButton != null;
        boolean isSauceSelected = sauceButton != null;

        if (!isSizeSelected) {
            showAlert(alert, "Select Size", "Please select Size", "Size then toppings");
        } else if (!isSauceSelected) {
            showAlert(alert, "Select Sauce", "Please select Sauce", "Sauce then toppings");
        } else {
            updatePizzaAndPrice();
        }
    }

    private void showAlert(Alert alert, String title, String header, String content) {
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void updatePizzaAndPrice() {
        boolean isExtraCheeseSelected = extraCheeseCheckBox.isSelected();
        if (isExtraCheeseSelected) {
            pizzaToBeAdded.addExtraCheese();
        } else {
            pizzaToBeAdded.removeExtraCheese();
        }
        pizzaPriceTextField.setText(String.valueOf(pizzaToBeAdded.price()));
    }
}
