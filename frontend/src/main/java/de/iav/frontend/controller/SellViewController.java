package de.iav.frontend.controller;

import de.iav.frontend.model.*;
import de.iav.frontend.service.SceneSwitchService;
import de.iav.frontend.service.StockService;
import de.iav.frontend.service.TransactionService;
import de.iav.frontend.service.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.time.LocalDateTime;

public class SellViewController {

    private final StockService stockService = StockService.getInstance();
    private final UserService userService = UserService.getInstance();
    private final TransactionService transactionService = TransactionService.getInstance();
    private final SceneSwitchService sceneSwitchService = SceneSwitchService.getInstance();
    @FXML
    public ListView<Stock> lv_stocks;

    //@FXML
    //public ComboBox<User> cb_users;

    private User user;
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


        System.out.println("showAllStocks");
    }


    public void calculateSum(ActionEvent event) {

        System.out.println("calculateSum");
        quantity = Integer.parseInt(tf_quantity.getText());
        price = Double.parseDouble(tf_price.getText());
        double sum = quantity * price;
        tf_sum.setText(Double.toString(sum));
    }

    public void doSceneChange(ActionEvent event) throws IOException {
        sceneSwitchService.switchToBuyViewController(event, user);
    }

    public void doSellStockTransaction(ActionEvent event) {

        if (lv_stocks.getSelectionModel().getSelectedItem() != null &&
                user != null &&
                quantity > 0 &&
                price > 0
        ) {
            System.out.println("Bereit zum Verkaufen User: " + user);
            System.out.println("verkauft " + quantity + " Aktien von " + lv_stocks.getSelectionModel().getSelectedItem() + " zum Preis von " + price);
            System.out.println(" für insgesamt " + tf_sum.getText());
            TransactionWithoutIdDTO transactionWithoutIdDto = new TransactionWithoutIdDTO(TransactionType.SELL,
                    LocalDateTime.now().toString(), user, lv_stocks.getSelectionModel().getSelectedItem(), quantity, price);
            Transaction sellTransaction = transactionService.addTransaction(transactionWithoutIdDto);
            System.out.println("sellTransaction: " + sellTransaction + "ausgeführt");


        } else {

            System.out.println("Transaktion nicht möglich da kein Stock is selected.");
        }
    }

    public void setUserForSelling(User user) {
        this.user = user;
        lv_stocks.getItems().clear();
        lv_stocks.getItems().addAll(transactionService.getAllStocksByUserID(user.id()));
    }
}
