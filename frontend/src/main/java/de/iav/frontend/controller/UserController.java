package de.iav.frontend.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class UserController {
    @FXML
    public TextField firstName;
    @FXML
    public TextField lastName;
    @FXML
    public TextField email;
    @FXML
    public PasswordField password;
    @FXML
    public Button signUp;
    @FXML
    public Button buyStocks;
    @FXML
    public void onBuyStockButtonClick(ActionEvent event) {
        System.out.println("Buy stock clicked");
    }
    @FXML
    public void onSignUpButtonClick(ActionEvent event) {
        System.out.println("sign up button clicked");
    }
}
