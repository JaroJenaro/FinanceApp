package de.iav.frontend.controller;

import de.iav.frontend.model.User;
import de.iav.frontend.service.SceneSwitchService;
import de.iav.frontend.service.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class StartPageController {
    private final UserService userService = UserService.getInstance();
    @FXML
    public PasswordField password;
    @FXML
    public TextField email;
    public Label informationForUser;
    private final SceneSwitchService sceneSwitchService = SceneSwitchService.getInstance();

    public void signInButtonPressed(ActionEvent actionEvent) throws IOException {
        //System.out.println(email.getText());
        if (email.getText().isEmpty() || email.getText().isBlank()) {
            informationForUser.setText("Please enter email");
        } else {
            if (userService.getUserByEmail(email.getText()) == null) {
                informationForUser.setText("User does not exist, please create new account");
            } else {
                if (password.getText().isEmpty() || password.getText().isBlank()) {
                    informationForUser.setText("Please enter password");
                } else {
                    User logInUser = userService.getUserByEmail(email.getText());
                    System.out.println(logInUser.password());
                    System.out.println(password.getText());
                    if (!(userService.getUserByEmail(email.getText()).password()).equals(password.getText())) {
                        informationForUser.setText("Wrong password");
                    } else {
                        if ((userService.getUserByEmail(email.getText()).password()).equals(password.getText())) {
                            System.out.println("correct password, switch to portfolio page");
                            System.out.println("logInUser: " + logInUser);
                            sceneSwitchService.switchToPortfolioScene(actionEvent, logInUser);
                        }
                    }
                }
            }
        }
    }

    public void signUpButtonPressed(ActionEvent actionEvent) throws IOException {
        UserService userService = new UserService();
        System.out.println(userService.getUserByEmail(email.getText()) != null);
        if (userService.getUserByEmail(email.getText()) != null) {
            informationForUser.setText("This email already exists, sign in instead");
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/de/iav/frontend/controller/user.fxml")); // Jeweiliges FXML laden
            Parent root = loader.load();
            UserController userController = loader.getController();
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene); // Neue Szenen setzen
            stage.show(); // ... und einblenden
            //System.out.println("dddddd"+email.getText());
            userController.email.setText(email.getText());
            userController.password.setText(password.getText());
        }
    }

    public void deleteUser(ActionEvent actionEvent) {
        //System.out.println(email.getText());
        if (email.getText().isEmpty() || email.getText().isBlank()) {
            informationForUser.setText("Please enter email");
        } else {
            if (userService.getUserByEmail(email.getText()) == null) {
                informationForUser.setText("User does not exist, please create new account");
            } else {
                if (password.getText().isEmpty() || password.getText().isBlank()) {
                    informationForUser.setText("Please enter password");
                } else {
                    System.out.println(userService.getUserByEmail(email.getText()).password());
                    System.out.println(password.getText());
                    if (!(userService.getUserByEmail(email.getText()).password()).equals(password.getText())) {
                        informationForUser.setText("Wrong password");
                    } else {
                        if ((userService.getUserByEmail(email.getText()).password()).equals(password.getText())) {
                            System.out.println("correct password, delete user");
                        }
                    }
                }
            }
        }
    }
}


