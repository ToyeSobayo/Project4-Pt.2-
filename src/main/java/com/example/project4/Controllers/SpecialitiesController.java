package com.example.project4.Controllers;

import com.example.project4.*;
import com.example.project4.Controllers.MainMenuController;
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
        String selectedPizzaName = specialtyPizzasComboBox.getSelectionModel().getSelectedItem();
        PizzaMaker pizzaMaker = new PizzaMaker();
        Pizza selectedPizza = pizzaMaker.createPizza(selectedPizzaName);
        pizzaToBeAdded = pizzaMaker.createPizza(selectedPizzaName);
        ObservableList <Topping> toppingsObservableList = FXCollections.observableList(selectedPizza.getToppings());
        toppingsListView.setItems(toppingsObservableList);
        setPizzaImageViewHelper(selectedPizzaName);
        RadioButton selectedSizeButton = (RadioButton) SizesGroup.getSelectedToggle();
        if(selectedPizzaName.equals("Deluxe")){
            setSauceTextField.setText(pizzaToBeAdded.getSauceAsString());
            setPizzaGivenSizeUpperLayerHelper(selectedSizeButton);
        }
        else if(selectedPizzaName.equals("Supreme")){
            setSauceTextField.setText(pizzaToBeAdded.getSauceAsString());
            setPizzaGivenSizeUpperLayerHelper(selectedSizeButton);
        }
        else if(selectedPizzaName.equals("Meatzza")){
            setSauceTextField.setText(pizzaToBeAdded.getSauceAsString());
            setPizzaGivenSizeUpperLayerHelper(selectedSizeButton);
        }
        else if(selectedPizzaName.equals("Seafood")){
            setSauceTextField.setText(pizzaToBeAdded.getSauceAsString());
            setPizzaGivenSizeUpperLayerHelper(selectedSizeButton);
        }
        else if(selectedPizzaName.equals("Pepperoni")){
            setSauceTextField.setText(pizzaToBeAdded.getSauceAsString());
            setPizzaGivenSizeUpperLayerHelper(selectedSizeButton);
        }
        extraCheeseAndSauceHelper();
    }

    private void extraCheeseAndSauceHelper(){
        if(pizzaToBeAdded != null) {
            if (extraSauceCheckBox.isSelected()) {
                pizzaToBeAdded.addExtraSauce();
            }
            if (extraCheeseCheckBox.isSelected()) {
                pizzaToBeAdded.addExtraCheese();
            }
        }
    }

    private void setPizzaGivenSizeUpperLayerHelper(RadioButton selectedSizeButton){
        if(pizzaToBeAdded.getSize() == null){
            if(selectedSizeButton != null){
                setPizzaPriceGivenSizeHelper(selectedSizeButton);
            }
        }
    }

    private void setPizzaPriceGivenSizeHelper(RadioButton selectedSizeButton){
        String selectedSizeStr = selectedSizeButton.getText();
        if(selectedSizeStr.equals("Small")){
            pizzaToBeAdded.setSize(Size.SMALL);
            if(extraCheeseCheckBox.isSelected()) {
                pizzaToBeAdded.addExtraCheese();
            }
            if(extraSauceCheckBox.isSelected()){
                pizzaToBeAdded.addExtraSauce();
            }
            pizzaPriceTextField.setText(String.valueOf(pizzaToBeAdded.price()));
        }
        else if(selectedSizeStr.equals("Medium")){
            pizzaToBeAdded.setSize(Size.MEDIUM);
            if(extraCheeseCheckBox.isSelected()) {
                pizzaToBeAdded.addExtraCheese();
            }
            if(extraSauceCheckBox.isSelected()){
                pizzaToBeAdded.addExtraSauce();
            }
            pizzaPriceTextField.setText(String.valueOf(pizzaToBeAdded.price()));
        }
        else if(selectedSizeStr.equals("Large")){
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
    public void smallButtonAction(ActionEvent actionEvent) {
        if(pizzaToBeAdded != null){
            pizzaToBeAdded.setSize(Size.SMALL);
            pizzaPriceTextField.setText(String.valueOf(pizzaToBeAdded.price()));
        }
    }

    @FXML
    public void mediumButtonAction(ActionEvent actionEvent) {
        if(pizzaToBeAdded != null){
            pizzaToBeAdded.setSize(Size.MEDIUM);
            pizzaPriceTextField.setText(String.valueOf(pizzaToBeAdded.price()));
        }
    }

    @FXML
    public void largeButtonAction(ActionEvent actionEvent) {
        if(pizzaToBeAdded != null){
            pizzaToBeAdded.setSize(Size.LARGE);
            pizzaPriceTextField.setText(String.valueOf(pizzaToBeAdded.price()));
        }
    }

    @FXML
    public void extraCheeseBoxAction(ActionEvent actionEvent) {
        RadioButton selectedSizeButton = (RadioButton) SizesGroup.getSelectedToggle();
        if(pizzaToBeAdded != null && selectedSizeButton != null) {
            if (extraCheeseCheckBox.isSelected()) {
                pizzaToBeAdded.addExtraCheese();
                pizzaPriceTextField.setText(String.valueOf(pizzaToBeAdded.price()));
            }
            else if (!extraCheeseCheckBox.isSelected()) {
                pizzaToBeAdded.removeExtraCheese();
                pizzaPriceTextField.setText(String.valueOf(pizzaToBeAdded.price()));
            }
        }
    }

    @FXML
    public void extraSauceBoxAction(ActionEvent actionEvent) {
        RadioButton selectedSizeButton = (RadioButton) SizesGroup.getSelectedToggle();
        if(pizzaToBeAdded != null && selectedSizeButton != null) {
            if (extraSauceCheckBox.isSelected()) {
                pizzaToBeAdded.addExtraSauce();
                pizzaPriceTextField.setText(String.valueOf(pizzaToBeAdded.price()));
            }
            else if (!extraSauceCheckBox.isSelected()) {
                pizzaToBeAdded.removeExtraSauce();
                pizzaPriceTextField.setText(String.valueOf(pizzaToBeAdded.price()));
            }
        }
    }

    @FXML
    public void addToOrderButtonAction(ActionEvent actionEvent) {
        RadioButton selectedSizeButton = (RadioButton) SizesGroup.getSelectedToggle();

        if(selectedSizeButton == null || specialtyPizzasComboBox.getSelectionModel().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Incomplete Fields");
            alert.setHeaderText("Incomplete Fields!");
            alert.setContentText("Required fields missing, enter all");
            alert.showAndWait();
        }
        else{
            if(pizzaToBeAdded != null){
                Order order = dataSingleton.getOrder();
                if(order == null){
                    ArrayList <Pizza> pizzaList = new ArrayList<>();
                    pizzaList.add(pizzaToBeAdded);
                    Order newOrder = new Order(pizzaList);
                    dataSingleton.setOrder(newOrder);
                }
                else{
                    order.add(pizzaToBeAdded);
                    dataSingleton.setOrder(order);
                }
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Added");
                alert.setHeaderText("Added to cart!");
                alert.setContentText("Coming right up fatty...");
                alert.showAndWait();
            }
        }
    }
}
