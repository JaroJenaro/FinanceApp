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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.time.LocalDateTime;

public class BuyViewController {

    private final StockService stockService = StockService.getInstance();

    private final TransactionService transactionService = TransactionService.getInstance();
    private final SceneSwitchService sceneSwitchService = SceneSwitchService.getInstance();
    @FXML
    public ListView<Stock> lv_stocks;

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
    private static final Logger LOG = LogManager.getLogger();


    public void initialize() {
        LOG.info("---->BuyViewController public void initialize");
        showAllStocks();
    }


    public void showAllStocks() {
        lv_stocks.getItems().clear();
        LOG.info("showAllStocks");

        lv_stocks.getItems().addAll(stockService.getAllStocks());
        LOG.info("showAllUsers");
    }



    @FXML
    public void calculateSum() {
        LOG.info("calculateSum");
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
            LOG.info("Bereit zum Kaufen User: {}}", user);
            LOG.info("kauft {} Aktien von {} zum Preis von {}", quantity, lv_stocks.getSelectionModel().getSelectedItem(), price);
            LOG.info("für insgesamt {}", tf_sum.getText());
            TransactionWithoutIdDTO transactionDTO = new TransactionWithoutIdDTO(TransactionType.BUY,
                    LocalDateTime.now().toString(), user, lv_stocks.getSelectionModel().getSelectedItem(), quantity, price);
            Transaction buyTransaction = transactionService.addTransaction(transactionDTO);

            LOG.info("buyTransaction: {} ausgeführt", buyTransaction);
            //sceneSwitchService.switchToPortfolioScene(new ActionEvent(), user);
        } else {
            LOG.info("Transaktion nicht möglich da kein Stock is selected.");
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
        LOG.info("setUserForBuying(User user)  user: {}", user);
        this.user = user;
        LOG.info("setUserForBuying(User user) { user: {}", this.user);
        l_user.setText(user.toString());
    }

    public void stockChanged() {
        LOG.info(lv_stocks.getSelectionModel().getSelectedItem());
        price = stockService.getStockPrice(lv_stocks.getSelectionModel().getSelectedItem().stockTicker()).price();
        LOG.info("Price: {}", price);
        tf_price.setText(String.valueOf(price));

    }

    public void stockChangedKey(KeyEvent keyEvent) {
        LOG.info("stockChangedKey");
        if (keyEvent.getCode() == KeyCode.getKeyCode("s"))
            LOG.info(lv_stocks.getSelectionModel().getSelectedItem());
    }

    public void neuAnmelden(ActionEvent event) throws IOException {
        sceneSwitchService.switchToUserSignIn(event);
    }
}