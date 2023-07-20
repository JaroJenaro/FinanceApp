package de.iav.frontend.controller;

import de.iav.frontend.model.*;
import de.iav.frontend.service.SceneSwitchService;
import de.iav.frontend.service.StockService;
import de.iav.frontend.service.TransactionService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.logging.Logger;

public class BuyViewController {

    private final StockService stockService = StockService.getInstance();

    private final TransactionService transactionService = TransactionService.getInstance();
    private final SceneSwitchService sceneSwitchService = SceneSwitchService.getInstance();
    @FXML
    public ListView<Stock> lv_stocks;

    //@FXML
    //public ComboBox<User> cb_users;
    @FXML
    public Label l_user;

    private User user;
    @FXML
    public TextField tf_quantity;
    @FXML
    public TextField tf_price;
    @FXML
    public TextField tf_sum;
    @FXML
    public Button buyButton;
    @FXML
    public Button sellButton;

    int quantity;
    double price;
    Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);


    public void initialize() {
        logger.info("---->BuyViewController public void initialize");
        showAllStocks();
    }


    public void showAllStocks() {
        lv_stocks.getItems().clear();
        logger.info("showAllStocks");

        lv_stocks.getItems().addAll(stockService.getAllStocks());
        logger.info("showAllUsers {}");
        logger.info(stockService.getAllStocks().toString());
        logger.info(this.user.toString());
        logger.info(user.toString());
    }



    @FXML
    public void calculateSum() {
        logger.fine("calculateSum");
        quantity = Integer.parseInt(tf_quantity.getText());
        price = Double.parseDouble(tf_price.getText());
        double sum = quantity * price;
        tf_sum.setText(Double.toString(sum));
    }

    @FXML
    public void doBuyStockTransaction() {

        quantity = Integer.parseInt(tf_quantity.getText());
        price = Double.parseDouble(tf_price.getText());

        if (lv_stocks.getSelectionModel().getSelectedItem() != null &&
                user != null &&
                quantity > 0 &&
                price > 0
        ) {
            logger.fine("Bereit zum Kaufen User: " + user);
            logger.fine("kauft " + quantity + " Aktien von " + lv_stocks.getSelectionModel().getSelectedItem() + " zum Preis von " + price);
            logger.fine(" für insgesamt " + tf_sum.getText());
            TransactionWithoutIdDTO transactionDTO = new TransactionWithoutIdDTO(TransactionType.BUY,
                    LocalDateTime.now().toString(), user, lv_stocks.getSelectionModel().getSelectedItem(), quantity, price);
            Transaction buyTransaction = transactionService.addTransaction(transactionDTO);

            logger.fine("buyTransaction: " + buyTransaction + "ausgeführt");
        } else {
            logger.info("Transaktion nicht möglich da kein Stock is selected.");
        }
    }
    @FXML
    public void doSceneChange(ActionEvent event) throws IOException {
        sceneSwitchService.switchToSellViewController(event, user);

    }

    public void backToPortfolioScene(ActionEvent event) throws IOException {
        sceneSwitchService.switchToPortfolioScene(event, user);
    }

    public void setUserForBuying(User user) {
        logger.fine("setUserForBuying(User user) { user: " + user);
        this.user = user;
        logger.fine("setUserForBuying(User user) { user: " + this.user);
        logger.fine("showAllStocks: " + user.toString());
        l_user.setText(user.toString());
    }

    public void stockChanged() {
        logger.fine(String.valueOf(lv_stocks.getSelectionModel().getSelectedItem()));
        price = stockService.getStockPrice(lv_stocks.getSelectionModel().getSelectedItem().stockTicker());
        logger.fine("Price: " + price);
        tf_price.setText(String.valueOf(price));

    }

    public void stockChangedKey(KeyEvent keyEvent) {
        logger.fine("test");
        if (keyEvent.getCode() == KeyCode.getKeyCode("s"))
            logger.fine(String.valueOf(lv_stocks.getSelectionModel().getSelectedItem()));
    }

    public void neuAnmelden(ActionEvent event) throws IOException {
        sceneSwitchService.switchToUserSignIn(event);
    }
}