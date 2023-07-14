/*
package de.iav.frontend.controller;

import de.iav.frontend.service.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;

public class portfolioViewController {
    @FXML
    public TableView portfolioTable;
    @FXML
    public TableColumn symbolColumn;
    @FXML
    public TableColumn nameColumn;
    @FXML
    public TableColumn quantityColumn;
    @FXML
    public TableColumn priceColumn;
    @FXML
    public TableColumn totalColumn;
    @FXML
    public Button buyButton;
    @FXML
    public Button sellButton;
    @FXML
    public TextArea portfolioValue;

    private UserService userService= UserService.getInstance();
    public void initialize() {
        // Hier stellen wir die Daten der ListView ein
        //  listView.getItems() = Liste der Elemente aus listView holen
        // .addAll() = Elemente zur Liste hinzufügen
        portfolioTable.getItems().addAll(studentService.getAllStudents());

        listView.getSelectionModel()
                .selectedItemProperty()
                // Listener der etwas macht
                .addListener(
                        (observableValue, s, t1) -> {if (listView.getSelectionModel().getSelectedItem()!= null){
                            text.setText(listView.getSelectionModel().getSelectedItem().firstName() + " " + listView.getSelectionModel().getSelectedItem().lastName());
                            editStudentButton.setDisable(false);
                            deleteStudentButton.setDisable(false);


                        }
                        }
                );
    }


    public void openBuyView(ActionEvent event) {
    }

    public void openSellView(ActionEvent event) {
    }
}
*/
