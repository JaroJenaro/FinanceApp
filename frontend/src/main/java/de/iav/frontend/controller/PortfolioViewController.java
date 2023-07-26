

package de.iav.frontend.controller;

import de.iav.frontend.model.CurrentStockPrice;
import de.iav.frontend.model.Transaction;
import de.iav.frontend.model.User;
import de.iav.frontend.model.UserPortfolio;
import de.iav.frontend.service.PortfolioViewService;
import de.iav.frontend.service.SceneSwitchService;
import de.iav.frontend.service.StockService;
import de.iav.frontend.service.TransactionService;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
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
    public TableView<UserPortfolio> portfolioTable;
    @FXML
    public TableColumn<UserPortfolio, String> companyName;
    @FXML
    public TableColumn<UserPortfolio, Integer> quantity;
    @FXML
    public TableColumn<UserPortfolio, Double> buyPrice;
    @FXML
    public Button buyButton;
    @FXML
    public TableColumn <UserPortfolio, Double> currentPrice;
    @FXML
    public TableColumn <UserPortfolio, Double>  performance;
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
    public ListView<UserPortfolio> listViewTransactions;




    public void initialize() {
    }
        //String userId="12345";


    private void initializeUser(){

        // Retrieve portfolio transactions for the user
        List<UserPortfolio> portfolio = portfolioViewService.getPortfolioByUserID(user.id());
        System.out.println(portfolio.toString());

        //stock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        quantity.setCellValueFactory(data -> {
            int quantity = data.getValue().quantity();
            return Bindings.createObjectBinding(() -> quantity);
        });
        companyName.setCellValueFactory(data -> {
            String companyName = data.getValue().stock().companyName();
            return Bindings.createObjectBinding(() -> companyName);
        });
        buyPrice.setCellValueFactory(data -> {
            double price = data.getValue().averageBuyPrice();
            return Bindings.createObjectBinding(() -> price);
        });
        currentPrice.setCellValueFactory(data -> {
            double price= data.getValue().currentPrice();
            return  Bindings.createObjectBinding(()->price);
        });
        performance.setCellValueFactory(data -> {
            double performance= data.getValue().performance();
            return  Bindings.createObjectBinding(()->performance);
        });
        portfolioValue.setText(String.valueOf(portfolio.get(portfolio.size()-1).portfolioValue()));




// Set the cell value factory for the third column to use the totalPriceBinding


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


