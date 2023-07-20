package de.iav.frontend.service;

import de.iav.frontend.controller.BuyViewController;
import de.iav.frontend.controller.PortfolioViewController;
import de.iav.frontend.controller.SellViewController;
import de.iav.frontend.controller.TransactionController;
import de.iav.frontend.model.Stock;
import de.iav.frontend.model.User;
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

    public void switchToUserSignIn(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/de/iav/frontend/controller/user.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void switchToUserController(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/de/iav/frontend/controller/user.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
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

    public void switchToBuyViewController(ActionEvent event, User user) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/de/iav/frontend/controller/BuyView.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        BuyViewController buyViewController = loader.getController();
        buyViewController.setUserForBuying(user);

        Scene scene = new Scene(root);
        stage.setTitle("Finance App! [Kaufen]");
        stage.setScene(scene);

        stage.show();
    }

    public void switchToSellViewController(ActionEvent event, User user) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/de/iav/frontend/controller/SellView.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        SellViewController sellViewController = loader.getController();
        sellViewController.setUserForSelling(user);

        Scene scene = new Scene(root);
        stage.setTitle("Finance App! [Verkaufen]");
        stage.setScene(scene);

        stage.show();
    }

    public void switchToPortfolioScene(ActionEvent event, User user) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/de/iav/frontend/controller/portfolioViewTable.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        PortfolioViewController portfolioViewController = loader.getController();
        portfolioViewController.setUserForPortfolio(user);

        Scene scene = new Scene(root);
        //stage.setTitle("Finance App! [Kaufen]");
        stage.setScene(scene);

        stage.show();
    }
}