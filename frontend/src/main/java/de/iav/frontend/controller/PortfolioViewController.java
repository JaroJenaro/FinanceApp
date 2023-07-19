

package de.iav.frontend.controller;

import de.iav.frontend.model.Transaction;
import de.iav.frontend.model.TransactionType;
import de.iav.frontend.service.PortfolioViewService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class PortfolioViewController {
    @FXML
    public TableView<Transaction> portfolioTable;
    @FXML
    public TableColumn <Transaction, String> symbolColumn;
    @FXML
    public TableColumn <Transaction, String>nameColumn;
    @FXML
    public TableColumn <Transaction, Integer>quantityColumn;
    @FXML
    public TableColumn <Transaction, Double>priceColumn;
    @FXML
    public TableColumn  <Transaction, Double>totalColumn;
    @FXML
    public Button buyButton;
    @FXML
    public Button sellButton;
    @FXML
    public Text portfolioValue;

    private final PortfolioViewService portfolioViewService= PortfolioViewService.getInstance();
    public void initialize() {
        String userId="12345";

        // Retrieve portfolio transactions for the user
        List<Transaction> portfolio = portfolioViewService.getAllTransactionsOfUser(userId);
        System.out.println(portfolio.toString());
        List<Transaction> allTransactions = portfolioViewService.getAllTransactions();
        System.out.println(allTransactions.toString());

        // Create an ObservableList to store the portfolio transactions
        ObservableList<Transaction> portfolioList = FXCollections.observableArrayList(portfolio);


        portfolioTable.setItems(portfolioList);
        //portfolioValue.setText(portfolioViewService.getPortfolioValue(userId).toString());


        portfolioTable.getSelectionModel()
                .selectedItemProperty()
                // Listener der etwas macht
                .addListener(
                        (observableValue, s, t1) -> {if (portfolioTable.getSelectionModel().getSelectedItem()!= null){
                            sellButton.setDisable(false);


                        }
                        }
                );
    }


    @FXML
    public void openBuyView(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/de/iav/frontend/controller/BuyView.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        BuyViewController buyViewController = loader.getController();
        //buyViewController.initialize();

        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.show();
    }

    public void openSellView(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/de/iav/frontend/controller/SellView.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.show();
    }
}


