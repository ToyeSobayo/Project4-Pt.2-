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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

/**
 * Controller class for handling pizza specialities.
 */
public class SpecialitiesController {
    private ObservableList<String> specialtiesList;
    private DataSingleton dataSingleton = DataSingleton.getInstance();
    private Pizza pizzaToBeAdded;

    @FXML private MainMenuController mainMenuController;
    @FXML private ComboBox<String> specialtyPizzasComboBox;
    @FXML private ListView<Topping> toppingsListView;
    @FXML private ToggleGroup SizesGroup;
    @FXML private ImageView specialtyPizzaimageView;
    @FXML private TextField pizzaPriceTextField;
    @FXML private TextField setSauceTextField;
    @FXML private CheckBox extraCheeseCheckBox;
    @FXML private CheckBox extraSauceCheckBox;

    /**
     * Initializes the controller with default settings.
     */
    public void initialize(){
        pizzaPriceTextField.setEditable(false);
        setSauceTextField.setEditable(false);
        specialtiesList = FXCollections.observableArrayList("Supreme","Meatzza","Deluxe","Pepperoni","Seafood");
        specialtyPizzasComboBox.setItems(specialtiesList);
    }

    /**
     * Handles the action to return to the main menu.
     */
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
            alert.setTitle("Error 404");
            alert.setHeaderText("Loading page");
            alert.setContentText("Couldn't load page");
            alert.showAndWait();
        }
    }

    @FXML
    /**
     * Handles selection in specialties combo box.
     */
    public void specialtiesComboBoxAction(ActionEvent actionEvent) {
        String PizzaName = specialtyPizzasComboBox.getSelectionModel().getSelectedItem();
        PizzaMaker pizzaMaker = new PizzaMaker();
        Pizza selectedPizza = pizzaMaker.createPizza(PizzaName);
        pizzaToBeAdded = pizzaMaker.createPizza(PizzaName);
        ObservableList <Topping> toppingsObservableList = FXCollections.observableList(selectedPizza.getToppings());
        toppingsListView.setItems(toppingsObservableList);
        setPizzaImageViewHelper(PizzaName);
        RadioButton selectedSizeButton = (RadioButton) SizesGroup.getSelectedToggle();
        if(PizzaName.equals("Supreme")){
            setSauceTextField.setText(pizzaToBeAdded.getSauceAsString());
            setPizzaSizeLayer(selectedSizeButton);
        }
        else if(PizzaName.equals("Deluxe")){
            setSauceTextField.setText(pizzaToBeAdded.getSauceAsString());
            setPizzaSizeLayer(selectedSizeButton);
        }
        else if(PizzaName.equals("Seafood")){
            setSauceTextField.setText(pizzaToBeAdded.getSauceAsString());
            setPizzaSizeLayer(selectedSizeButton);
        }
        else if(PizzaName.equals("Meatzza")){
            setSauceTextField.setText(pizzaToBeAdded.getSauceAsString());
            setPizzaSizeLayer(selectedSizeButton);
        }
        else if(PizzaName.equals("Pepperoni")){
            setSauceTextField.setText(pizzaToBeAdded.getSauceAsString());
            setPizzaSizeLayer(selectedSizeButton);
        }
        extraCheeseAndSauce();
    }

    private void extraCheeseAndSauce(){
        if(pizzaToBeAdded != null) {
            if (extraSauceCheckBox.isSelected()) {
                pizzaToBeAdded.addExtraSauce();
            }
            if (extraCheeseCheckBox.isSelected()) {
                pizzaToBeAdded.addExtraCheese();
            }
        }
    }

    private void setPizzaSizeLayer(RadioButton selectedSizeButton){
        if(pizzaToBeAdded.getSize() == null){
            if(selectedSizeButton != null){
                setPizzaPriceGivenSizeHelper(selectedSizeButton);
            }
        }
    }

    private void setPizzaPriceGivenSizeHelper(RadioButton selectedSizeButton){
        String selectedSize = selectedSizeButton.getText();
        if(selectedSize.equals("Small")){
            pizzaToBeAdded.setSize(Size.SMALL);
            if(extraCheeseCheckBox.isSelected()) {
                pizzaToBeAdded.addExtraCheese();
            }
            if(extraSauceCheckBox.isSelected()){
                pizzaToBeAdded.addExtraSauce();
            }
            pizzaPriceTextField.setText(String.valueOf(pizzaToBeAdded.price()));
        }
        else if(selectedSize.equals("Medium")){
            pizzaToBeAdded.setSize(Size.MEDIUM);
            if(extraCheeseCheckBox.isSelected()) {
                pizzaToBeAdded.addExtraCheese();
            }
            if(extraSauceCheckBox.isSelected()){
                pizzaToBeAdded.addExtraSauce();
            }
            pizzaPriceTextField.setText(String.valueOf(pizzaToBeAdded.price()));
        }
        else if(selectedSize.equals("Large")){
            pizzaToBeAdded.setSize(Size.LARGE);
            if(extraCheeseCheckBox.isSelected()) {
                pizzaToBeAdded.addExtraCheese();
            }
            if(extraSauceCheckBox.isSelected()){
                pizzaToBeAdded.addExtraSauce();
            }
            pizzaPriceTextField.setText(String.valueOf(pizzaToBeAdded.price()));
        }
    }

    private void setPizzaImageViewHelper(String selectedPizzaName){
        if(selectedPizzaName.equals("Deluxe")){
            try {
                InputStream stream = new FileInputStream("src/main/resources/com/example/project4/Images/deluxee.jpg");
                Image image = new Image(stream);
                specialtyPizzaimageView.setImage(image);
            }catch(FileNotFoundException e){
                e.printStackTrace();
            }
        }
        else if(selectedPizzaName.equals("Supreme")){
            try {
                InputStream stream = new FileInputStream("src/main/resources/com/example/project4/Images/supremee.jpg");
                Image image = new Image(stream);
                specialtyPizzaimageView.setImage(image);
            }catch(FileNotFoundException e){
                e.printStackTrace();
            }
        }
        else{
            setPizzaimageViewHelperTwo(selectedPizzaName);
        }
    }

    private void setPizzaimageViewHelperTwo(String selectedPizzaName){
        if(selectedPizzaName.equals("Meatzza")){
            try {
                InputStream stream = new FileInputStream("src/main/resources/com/example/project4/Images/meatzaaa.jpg");
                Image image = new Image(stream);
                specialtyPizzaimageView.setImage(image);
            }catch(FileNotFoundException e){
                e.printStackTrace();
            }
        }
        else if(selectedPizzaName.equals("Seafood")){
            try {
                InputStream stream = new FileInputStream("src/main/resources/com/example/project4/Images/seafood.jpg");
                Image image = new Image(stream);
                specialtyPizzaimageView.setImage(image);
            }catch(FileNotFoundException e){
                e.printStackTrace();
            }
        }
        else{
            try {
                InputStream stream = new FileInputStream("src/main/resources/com/example/project4/Images/pepperoni.jpg");
                Image image = new Image(stream);
                specialtyPizzaimageView.setImage(image);
            }catch(FileNotFoundException e){
                e.printStackTrace();
            }
        }
    }

    public void setMainController (MainMenuController controller){
        mainMenuController = controller;
    }

    @FXML
    public void smallButton(ActionEvent actionEvent) {
        if(pizzaToBeAdded != null){
            pizzaToBeAdded.setSize(Size.SMALL);
            pizzaPriceTextField.setText(String.valueOf(pizzaToBeAdded.price()));
        }
    }

    @FXML
    public void mediumButton(ActionEvent actionEvent) {
        if(pizzaToBeAdded != null){
            pizzaToBeAdded.setSize(Size.MEDIUM);
            pizzaPriceTextField.setText(String.valueOf(pizzaToBeAdded.price()));
        }
    }

    @FXML
    public void largeButton(ActionEvent actionEvent) {
        if(pizzaToBeAdded != null){
            pizzaToBeAdded.setSize(Size.LARGE);
            pizzaPriceTextField.setText(String.valueOf(pizzaToBeAdded.price()));
        }
    }

    @FXML
    public void handleExtraCheese(ActionEvent actionEvent) {
        if (pizzaToBeAdded != null && SizesGroup.getSelectedToggle() != null) {
            updatePizzaForExtraCheese();
            updatePriceDisplay();
        }
    }

    private void updatePizzaForExtraCheese() {
        if (extraCheeseCheckBox.isSelected()) {
            pizzaToBeAdded.addExtraCheese();
        } else {
            pizzaToBeAdded.removeExtraCheese();
        }
    }

    private void updatePriceDisplay() {
        pizzaPriceTextField.setText(String.valueOf(pizzaToBeAdded.price()));
    }

    @FXML
    public void handleExtraSauce(ActionEvent actionEvent) {
        if (pizzaToBeAdded != null) {
            updatePizzaForExtraSauce();
            updatePizzaPriceDisplay();
        }
    }

    private void updatePizzaForExtraSauce() {
        if (extraSauceCheckBox.isSelected()) {
            pizzaToBeAdded.addExtraSauce();
        } else {
            pizzaToBeAdded.removeExtraSauce();
        }
    }

    private void updatePizzaPriceDisplay() {
        pizzaPriceTextField.setText(String.valueOf(pizzaToBeAdded.price()));
    }

    @FXML
    public void addToOrder(ActionEvent actionEvent) {
        RadioButton selectedSizeButton = (RadioButton) SizesGroup.getSelectedToggle();

        if (selectedSizeButton == null || specialtyPizzasComboBox.getSelectionModel().isEmpty()) {
            showAlert("Incomplete Fields", "Incomplete Fields!", "Required fields missing, enter all", Alert.AlertType.WARNING);
        } else {
            if (pizzaToBeAdded != null) {
                addPizzaToOrder();
            }
        }
    }

    private void addPizzaToOrder() {
        Order order = dataSingleton.getOrder();
        if (order == null) {
            order = new Order(new ArrayList<>());
            dataSingleton.setOrder(order);
        }
        order.add(pizzaToBeAdded);
        dataSingleton.setOrder(order);
        showAlert("Added", "Added to cart!", "Coming right up fatty...", Alert.AlertType.CONFIRMATION);
    }

    private void showAlert(String title, String header, String content, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
