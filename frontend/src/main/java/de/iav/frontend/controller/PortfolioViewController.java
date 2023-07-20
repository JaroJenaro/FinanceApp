

package de.iav.frontend.controller;

import de.iav.frontend.model.Stock;
import de.iav.frontend.model.Transaction;
import de.iav.frontend.model.TransactionType;
import de.iav.frontend.model.User;
import de.iav.frontend.service.PortfolioViewService;
import de.iav.frontend.service.StockService;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class PortfolioViewController {
    @FXML
    public TableView<Transaction> portfolioTable;
    @FXML
    public TableColumn <Transaction, String> companyName;
    @FXML
    public TableColumn <Transaction, Integer> quantity;
    public TableColumn <Transaction, Double> price;
    @FXML
    public Button buyButton;
    @FXML
    public Button sellButton;
    @FXML
    public Text portfolioValue;

    private final PortfolioViewService portfolioViewService= PortfolioViewService.getInstance();
    private final StockService stockService=StockService.getInstance();
    @FXML
    public ListView<Transaction> listViewTransactions;



    public void initialize() {
        //String userId="12345";

        // Retrieve portfolio transactions for the user
        List<Transaction> portfolio = portfolioViewService.getAllTransactions();
        System.out.println(portfolio.toString());

        //stock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        quantity.setCellValueFactory(data -> {
            Integer quantity = data.getValue().quantity();
            return Bindings.createObjectBinding(() -> quantity);
        });
        companyName.setCellValueFactory(data -> {
            String companyName = data.getValue().stock().companyName();
            return Bindings.createObjectBinding(() -> companyName);
        });
        System.out.println(stockService.getStockPrice(portfolio.get(0).stock().stockTicker()));

/*        price.setCellValueFactory(data -> {
            Double price = stockService.getStockPrice(data.getValue().stock().stockTicker());
            return Bindings.createObjectBinding(() -> price);
        });*/

        // Fetch the data from the backend (replace this with your actual data retrieval)


        // Add the data to the table
        portfolioTable.getItems().addAll(portfolio);


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


