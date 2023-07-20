package de.iav.frontend.controller;
//import de.iav.frontend.model.TransactionWithoutUser;

import de.iav.frontend.model.User;
import de.iav.frontend.service.UserService;
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
    private final UserService userService = new UserService();

    @FXML
    public void onBuyStockButtonClick(ActionEvent event) {
        System.out.println("Buy stock clicked");
    }

    @FXML
    public void onSignUpButtonClick(ActionEvent event) {
        User user = new User("12345", firstName.getText(), lastName.getText(), email.getText(), password.getText());
        System.out.println(userService.addUser(user));
    }

    public void backButtonPressed(ActionEvent actionEvent) {
    }
}














