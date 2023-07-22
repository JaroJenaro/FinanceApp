

package de.iav.frontend.controller;

import de.iav.frontend.model.Transaction;
import de.iav.frontend.model.User;
import de.iav.frontend.service.PortfolioViewService;
import de.iav.frontend.service.SceneSwitchService;
import de.iav.frontend.service.StockService;
import de.iav.frontend.service.TransactionService;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.List;

public class PortfolioViewController {
    private final SceneSwitchService sceneSwitchService = SceneSwitchService.getInstance();
    @FXML
    public TableView<Transaction> portfolioTable;
    @FXML
    public TableColumn<Transaction, String> companyName;
    @FXML
    public TableColumn<Transaction, Integer> quantity;
    @FXML
    public TableColumn<Transaction, Double> buyPrice;
    @FXML
    public Button buyButton;
    @FXML
    public TableColumn <Transaction, Double> currentPrice;
    @FXML
    private User user;
    @FXML
    public Button sellButton;
    @FXML
    public Text portfolioValue;

    private final PortfolioViewService portfolioViewService = PortfolioViewService.getInstance();
    private final TransactionService transactionService = TransactionService.getInstance();
    private final StockService stockService = StockService.getInstance();

    @FXML
    public ListView<Transaction> listViewTransactions;




    public void initialize() {
    }
        //String userId="12345";


    private void initializeUser(){

        // Retrieve portfolio transactions for the user
        List<Transaction> portfolio = portfolioViewService.getPortfolioByUserID(user.id());
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
        buyPrice.setCellValueFactory(data -> {
            Double price = data.getValue().price();
            return Bindings.createObjectBinding(() -> price);
        });
        double totalValue = 0.0;

        for (Transaction transaction : portfolioTable
                .getItems()) {
            // Get the quantity and price from the Transaction object
            int quantity = transaction.quantity();
            System.out.println(quantity);
            double price = transaction.price();
            System.out.println(price);

            // Calculate the value for this row and add it to the totalValue
            double rowValue = quantity * price;
            totalValue += rowValue;

        }
        String totalValueString= String.valueOf(totalValue);
        portfolioValue.setText(totalValueString);

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
        System.out.println("public void openBuyView(ActionEvent event) throws IOException { user: " + user);
        sceneSwitchService.switchToBuyViewController(event, user);

    }

    public void openSellView(ActionEvent event) throws IOException {
        sceneSwitchService.switchToSellViewController(event, user);
    }

    public void setUserForPortfolio(User user) {

        this.user = user;
        initializeUser();
    }



}


