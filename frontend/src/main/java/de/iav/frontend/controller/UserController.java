package de.iav.frontend.controller;
//import de.iav.frontend.model.TransactionWithoutUser;

import de.iav.frontend.model.UserWithoutIdDto;
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

    private UserWithoutIdDto userWithoutIdDto;

    private final UserService userService = new UserService();


    @FXML
    public void onSignUpButtonClick(ActionEvent event) {
        userWithoutIdDto = new UserWithoutIdDto(firstName.getText(), lastName.getText(), email.getText(), password.getText());

        System.out.println(userService.addUser(userWithoutIdDto));
    }

    public void backButtonPressed(ActionEvent actionEvent) {
    }

    public void setUserWithoutIdDtoForSignIn(UserWithoutIdDto userWithoutIdDto) {
        this.userWithoutIdDto = userWithoutIdDto;
        email.setText(this.userWithoutIdDto.email());
        password.setText(this.userWithoutIdDto.password());
        firstName.setText(this.userWithoutIdDto.firstName());
        lastName.setText(this.userWithoutIdDto.lastName());
    }
}














