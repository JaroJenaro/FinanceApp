package de.iav.frontend.controller;

import de.iav.frontend.model.Stock;
import de.iav.frontend.service.SceneSwitchService;
import de.iav.frontend.service.StockService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;

public class BuyViewController {

    private final StockService stockService = StockService.getInstance();
    private final SceneSwitchService sceneSwitchService = SceneSwitchService.getInstance();
    @FXML
    public ListView<Stock> lv_stocks;

    @FXML
    public ComboBox<Stock> cb_stocks;
    @FXML
    public TextField tf_quantity;
    @FXML
    public TextField tf_price;
    @FXML
    public TextField tf_sum;

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
        cb_stocks.getItems().addAll(stockService.getAllStocks());
        System.out.println("showAllStocks");
    }


    public void doStockTransaction(ActionEvent event) throws IOException {

        if (lv_stocks.getSelectionModel().getSelectedItem() != null) {
            System.out.println("Selected to stock to transaction : " + lv_stocks.getSelectionModel().getSelectedItem());
            //sceneSwitchService.switchToTransactionController(event, lv_stocks.getSelectionModel().getSelectedItem());
        } else {

            System.out.println("Transaktion nicht möglich da kein Stock is selected.");
        }
    }


    public void doStockTransaction_CB(ActionEvent event) throws IOException {

        if (lv_stocks.getSelectionModel().getSelectedItem() != null) {
            System.out.println("CB Selected to stock to transaction : " + cb_stocks.getSelectionModel().getSelectedItem());
            //sceneSwitchService.switchToTransactionController(event, cb_stocks.getSelectionModel().getSelectedItem());
        } else {

            System.out.println("Transaktion nicht möglich da kein Stock is selected.");
        }
    }

    public void calculateSum(ActionEvent event) {

        System.out.println("calculateSum");
        int quantity = Integer.parseInt(tf_quantity.getText());
        double price = Double.parseDouble(tf_price.getText());
        double sum = quantity * price;
        tf_sum.setText(Double.toString(sum));
    }
}
