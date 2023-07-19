package de.iav.frontend.controller;


import de.iav.frontend.model.*;
import de.iav.frontend.service.SceneSwitchService;
import de.iav.frontend.service.TransactionService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class TransactionController {

    private final TransactionService transactionService = TransactionService.getInstance();
    private final SceneSwitchService sceneSwitchService = SceneSwitchService.getInstance();

    private Stock transactionStock;
    private final User user = new User("1234", "firstName", "LastName", "firstName@lastName.com", "1234");

    @FXML
    public TextField tfCompany;
    @FXML
    public TextField tfStockTicker;
    @FXML
    public TextField tfISIN;

    @FXML
    public TextField tfWKN;
    @FXML
    public TextField tfPrice;
    @FXML
    public TextField tfQuantity;
    @FXML
    public TextField tfSum;


    public void buyStocks() {
        int quantity = Integer.parseInt(tfQuantity.getText());
        double price = Double.parseDouble(tfPrice.getText());
        double sum = quantity * price;
        tfSum.setText(Double.toString(sum));

        System.out.println(tfQuantity.getText() + " Aktien zum Preis von " + tfPrice.getText() + " für insgesamt " + tfSum.getText() + " kaufen. ");

        TransactionDTO transactionWithoutIdDto = new TransactionDTO(TransactionType.BUY,
                LocalDateTime.now().toString(), user, transactionStock, quantity, price);
        Transaction buyTransaction = transactionService.addTransaction(transactionWithoutIdDto);
        System.out.println("buyTransaction: " + buyTransaction);

    }

    public void sellStocks() {

        int quantity = Integer.parseInt(tfQuantity.getText());
        double price = Double.parseDouble(tfPrice.getText());
        double sum = quantity * price;
        tfSum.setText(Double.toString(sum));

        System.out.println(tfQuantity.getText() + " Aktien zum Preis von " + tfPrice.getText() + " für insgesamt " + tfSum.getText() + " verkaufen. ");
    }

    public void cancelTransaction(ActionEvent event) throws IOException {
        System.out.println("cancelTransaction");
        sceneSwitchService.switchToStockController(event);
    }


    public void setStockDataInFields(Stock stock) {
        tfCompany.setText(stock.companyName());
        tfStockTicker.setText(stock.stockTicker());
        tfISIN.setText(stock.ISIN());
        tfWKN.setText(stock.WKN());
        transactionStock = stock;
    }
}
