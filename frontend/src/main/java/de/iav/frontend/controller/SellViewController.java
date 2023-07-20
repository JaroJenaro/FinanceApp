package de.iav.frontend.controller;

import de.iav.frontend.model.*;
import de.iav.frontend.service.SceneSwitchService;
import de.iav.frontend.service.TransactionService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.time.LocalDateTime;

public class SellViewController {

    private final TransactionService transactionService = TransactionService.getInstance();
    private final SceneSwitchService sceneSwitchService = SceneSwitchService.getInstance();
    @FXML
    public ListView<Stock> lv_stocks;


    private User user;
    @FXML
    public TextField tf_quantity;
    @FXML
    public TextField tf_price;
    @FXML
    public TextField tf_sum;

    int quantity;
    double price;

    private static final Logger LOG = LogManager.getLogger();


    public void initialize() {
        showAllStocks();
    }


    public void showAllStocks() {


        LOG.info("showAllStocks");
    }


    public void calculateSum() {

        LOG.info("calculateSum");
        quantity = Integer.parseInt(tf_quantity.getText());
        price = Double.parseDouble(tf_price.getText());
        double sum = quantity * price;
        tf_sum.setText(Double.toString(sum));
    }

    public void doSceneChange(ActionEvent event) throws IOException {
        sceneSwitchService.switchToBuyViewController(event, user);
    }

    public void doSellStockTransaction() {

        if (lv_stocks.getSelectionModel().getSelectedItem() != null &&
                user != null &&
                quantity > 0 &&
                price > 0
        ) {
            LOG.info("Bereit zum Verkaufen User: {}", user);
            LOG.info("verkauft {} Aktien von {} zum Preis von {}", quantity, lv_stocks.getSelectionModel().getSelectedItem(), price);
            LOG.info(" für insgesamt {} ", tf_sum.getText());
            TransactionWithoutIdDTO transactionWithoutIdDto = new TransactionWithoutIdDTO(TransactionType.SELL,
                    LocalDateTime.now().toString(), user, lv_stocks.getSelectionModel().getSelectedItem(), quantity, price);
            Transaction sellTransaction = transactionService.addTransaction(transactionWithoutIdDto);
            LOG.info("sellTransaction: {} ausgeführt", sellTransaction);


        } else {

            LOG.info("Transaktion nicht möglich da kein Stock is selected.");
        }
    }

    public void setUserForSelling(User user) {
        this.user = user;
        lv_stocks.getItems().clear();
        lv_stocks.getItems().addAll(transactionService.getAllStocksByUserID(user.id()));
    }
}
