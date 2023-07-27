package de.iav.frontend.controller;

import de.iav.frontend.model.User;
import de.iav.frontend.model.UserPortfolio;
import de.iav.frontend.service.PortfolioViewService;
import de.iav.frontend.service.SceneSwitchService;
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
    @FXML
    public ListView<UserPortfolio> listViewTransactions;
    private final PortfolioViewService portfolioViewService = PortfolioViewService.getInstance();


    public void initialize() {
    }
    private void initializeUser(){

        List<UserPortfolio> portfolio = portfolioViewService.getPortfolioByUserID(user.id());

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
            double price = data.getValue().currentPrice();
            return Bindings.createObjectBinding(() -> price);
        });
        performance.setCellValueFactory(data -> {
            double performance = data.getValue().performance();
            return Bindings.createObjectBinding(() -> performance);
        });
        if (portfolio.size() > 0)
            portfolioValue.setText(String.valueOf(portfolio.get(portfolio.size() - 1).portfolioValue()));

        portfolioTable.getItems().addAll(portfolio);

        portfolioTable.getSelectionModel()
                .selectedItemProperty()
                // Listener der etwas macht
                .addListener(
                        (observableValue, s, t1) -> {
                            if (portfolioTable.getSelectionModel().getSelectedItem() != null) {
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
    @FXML
    public void openSellView(ActionEvent event) throws IOException {
        sceneSwitchService.switchToSellViewController(event, user);
    }
    @FXML
    public void setUserForPortfolio(User user) {
        this.user = user;
        initializeUser();
    }
}


