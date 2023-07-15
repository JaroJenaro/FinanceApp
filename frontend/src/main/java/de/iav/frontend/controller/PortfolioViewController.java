

package de.iav.frontend.controller;

import de.iav.frontend.model.TransactionWithoutUser;
import de.iav.frontend.service.PortfolioViewService;
import de.iav.frontend.service.UserService;
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
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class PortfolioViewController {
    @FXML
    public TableView<TransactionWithoutUser> portfolioTable;
    @FXML
    public TableColumn <TransactionWithoutUser, String> symbolColumn;
    @FXML
    public TableColumn <TransactionWithoutUser, String>nameColumn;
    @FXML
    public TableColumn <TransactionWithoutUser, Integer>quantityColumn;
    @FXML
    public TableColumn <TransactionWithoutUser, Double>priceColumn;
    @FXML
    public TableColumn  <TransactionWithoutUser, Double>totalColumn;
    @FXML
    public Button buyButton;
    @FXML
    public Button sellButton;
    @FXML
    public Text portfolioValue;

    private final PortfolioViewService portfolioViewService= PortfolioViewService.getInstance();
    public void initialize(String userId) {

        // Retrieve portfolio transactions for the user
        List<TransactionWithoutUser> portfolio = portfolioViewService.getAllTransactionsOfUser(userId);

        // Create an ObservableList to store the portfolio transactions
        ObservableList<TransactionWithoutUser> portfolioList = FXCollections.observableArrayList(portfolio);


        portfolioTable.setItems(portfolioList);
        portfolioValue.setText(portfolioViewService.getPortfolioValue(userId).toString());


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


