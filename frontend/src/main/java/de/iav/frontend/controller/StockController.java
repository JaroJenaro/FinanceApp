package de.iav.frontend.controller;

import de.iav.frontend.model.Stock;
import de.iav.frontend.service.SceneSwitchService;
import de.iav.frontend.service.StockService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.util.Collection;

public class StockController {

    private final StockService stockService = StockService.getInstance();
    private final SceneSwitchService sceneSwitchService = SceneSwitchService.getInstance();
    @FXML
    public ListView<Stock> lv_stocks;


    public void initialize() {
        showAllStocks();
    }




    public void showAllStocks() {
        lv_stocks.getItems().clear();
        lv_stocks.getItems().addAll((Collection<? extends Stock>) stockService.getAllStocks());
        System.out.println("showAllStocks");
    }


    public void doStockTransaction(ActionEvent event) throws IOException {

        if(lv_stocks.getSelectionModel().getSelectedItem() != null){
            System.out.println("Selected to stock to transaction : " + lv_stocks.getSelectionModel().getSelectedItem());
            sceneSwitchService.switchToTransactionController(event, lv_stocks.getSelectionModel().getSelectedItem());
        }
        else{

            System.out.println("Transaktion nicht möglich da kein Stock is selected.");
        }
    }


}
