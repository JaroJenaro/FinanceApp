package de.iav.frontend.service;

import de.iav.frontend.controller.TransactionController;
import de.iav.frontend.model.Stock;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneSwitchService {

    private static SceneSwitchService instance;

    public static synchronized SceneSwitchService getInstance() {
        if (instance == null) {
            instance = new SceneSwitchService();
        }
        return instance;
    }

    public void switchToStockController(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/de/iav/frontend/controller/StockList.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void switchToTransactionController(ActionEvent event, Stock stock) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/de/iav/frontend/controller/transaction.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        TransactionController transactionsController = loader.getController();
        transactionsController.setStockDataInFields(stock);

        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.show();
    }

    public void switchToBuyViewController(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/de/iav/frontend/controller/BuyView.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        //TransactionController transactionsController = loader.getController();
        //transactionsController.setStockDataInFields(stock);

        Scene scene = new Scene(root);
        stage.setTitle("Finance App! [Kaufen]");
        stage.setScene(scene);

        stage.show();
    }

    public void switchToSellViewController(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/de/iav/frontend/controller/SellView.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        //TransactionController transactionsController = loader.getController();
        //transactionsController.setStockDataInFields(stock);

        Scene scene = new Scene(root);
        stage.setTitle("Finance App! [Verkaufen]");
        stage.setScene(scene);

        stage.show();
    }

}
