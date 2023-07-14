package de.iav.frontend.controller;

import de.iav.frontend.model.*;
import de.iav.frontend.service.SceneSwitchService;
import de.iav.frontend.service.StockService;
import de.iav.frontend.service.TransactionService;
import de.iav.frontend.service.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.time.LocalDateTime;

public class BuyViewController {

    private final StockService stockService = StockService.getInstance();
    private final UserService userService = UserService.getInstance();
    private final TransactionService transactionService = TransactionService.getInstance();
    private final SceneSwitchService sceneSwitchService = SceneSwitchService.getInstance();
    @FXML
    public ListView<Stock> lv_stocks;

    @FXML
    public ComboBox<User> cb_users;
    @FXML
    public TextField tf_quantity;
    @FXML
    public TextField tf_price;
    @FXML
    public TextField tf_sum;

    int quantity;
    double price;

    //public FXCollections observableArrayList;


    public void initialize() {
        showAllStocks();
    }


    public void showAllStocks() {
        lv_stocks.getItems().clear();
        lv_stocks.getItems().addAll(stockService.getAllStocks());
/*
        ObservableList<Stock> allStocks =
                FXCollections.observableArrayList(stockService.getAllStocks()
                );
        cb_stocks.setItems(allStocks);
        //observableArrayList = FXCollections..getAllStocks().

 */
        cb_users.getItems().addAll(userService.getAllUsers());
        System.out.println("showAllStocks");
    }




    public void doStockTransaction_CB(ActionEvent event) throws IOException {

        if (lv_stocks.getSelectionModel().getSelectedItem() != null &&
                cb_users.getSelectionModel().getSelectedItem() != null &&
                quantity > 0 &&
                price > 0
        ) {
            System.out.println("Bereit zum Kaufen User: " + cb_users.getSelectionModel().getSelectedItem());
            System.out.println("kauft " + quantity + " Aktien von " + lv_stocks.getSelectionModel().getSelectedItem() + " zum Preis von " + price);
            System.out.println(" für insgesamt " + tf_sum.getText());
            TransactionWithoutIdDto transactionWithoutIdDto = new TransactionWithoutIdDto(TransactionType.BUY,
                    LocalDateTime.now().toString(), cb_users.getSelectionModel().getSelectedItem(), lv_stocks.getSelectionModel().getSelectedItem(), quantity, price);
            Transaction buyTransaction = transactionService.addTransaction(transactionWithoutIdDto);
            System.out.println("buyTransaction: " + buyTransaction + "ausgeführt");

            //sceneSwitchService.switchToTransactionController(event, cb_stocks.getSelectionModel().getSelectedItem());
        } else {

            System.out.println("Transaktion nicht möglich da kein Stock is selected.");
        }
    }

    public void calculateSum(ActionEvent event) {

        System.out.println("calculateSum");
        quantity = Integer.parseInt(tf_quantity.getText());
        price = Double.parseDouble(tf_price.getText());
        double sum = quantity * price;
        tf_sum.setText(Double.toString(sum));
    }
}