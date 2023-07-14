package de.iav.frontend.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class StartPageController {
    @FXML
    public PasswordField password;
    @FXML
    public TextField email;
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void signInButtonPressed(ActionEvent actionEvent) {
    }

    public void SignUpButtonPressed(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/de/iav/frontend/controller/user.fxml")); // Jeweiliges FXML laden
        root = loader.load();



        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);

        stage.setScene(scene); // Neue Szenen setzen
        stage.show(); // ... und einblenden


    }
}
